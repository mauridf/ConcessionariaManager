package com.concessionariamanager.presentation.veiculo;

import com.concessionariamanager.application.veiculo.dto.VeiculoDTO;
import com.concessionariamanager.application.mapper.VeiculoMapper;
import com.concessionariamanager.domain.veiculo.*;
import io.jsonwebtoken.io.IOException;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.concessionariamanager.application.upload.UploadService;
import com.concessionariamanager.application.veiculo.VeiculoService;
import com.concessionariamanager.application.veiculo.dto.VeiculoFiltroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final UploadService uploadService;

    public VeiculoController(VeiculoService veiculoService, UploadService uploadService) {
        this.veiculoService = veiculoService;
        this.uploadService = uploadService;
    }

    // Criar veículo (Gerente ou Vendedor)
    @PostMapping
    @PreAuthorize("hasAnyRole('GERENTE', 'VENDEDOR')")
    public ResponseEntity<VeiculoDTO> criar(@RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculo = VeiculoMapper.toEntity(veiculoDTO);
        return ResponseEntity.ok(VeiculoMapper.toDTO(veiculoService.salvar(veiculo)));
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoDTO>> listar(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) TipoVeiculo tipoVeiculo,
            @RequestParam(required = false) Categoria categoria,
            @RequestParam(required = false) Combustivel combustivel,
            @RequestParam(required = false) Cambio cambio,
            @PageableDefault(size = 10, sort = "marca") Pageable pageable) {

        VeiculoFiltroDTO filtro = new VeiculoFiltroDTO();
        filtro.setMarca(marca);
        filtro.setModelo(modelo);
        filtro.setTipoVeiculo(tipoVeiculo);
        filtro.setCategoria(categoria);
        filtro.setCombustivel(combustivel);
        filtro.setCambio(cambio);

        return ResponseEntity.ok(veiculoService.listar(filtro, pageable));
    }

    // Buscar por ID (todos autenticados)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(VeiculoMapper.toDTO(veiculoService.buscarPorId(id)));
    }

    // Atualizar veículo (somente Gerente)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<VeiculoDTO> atualizar(@PathVariable UUID id, @RequestBody VeiculoDTO veiculoDTO) {
        Veiculo veiculo = VeiculoMapper.toEntity(veiculoDTO);
        return ResponseEntity.ok(VeiculoMapper.toDTO(veiculoService.atualizar(id, veiculo)));
    }

    // Deletar veículo (somente Gerente)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/foto")
    public ResponseEntity<String> uploadFoto(@PathVariable UUID id,
                                             @RequestParam("arquivo") MultipartFile arquivo) {
        try {
            Veiculo veiculo = veiculoService.buscarPorId(id);

            String nomeArquivo = uploadService.salvarArquivo(arquivo);
            veiculo.setFoto(nomeArquivo);
            veiculoService.salvar(veiculo);

            return ResponseEntity.ok("Foto enviada com sucesso: " + nomeArquivo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar foto: " + e.getMessage());
        }
    }

    @GetMapping("/capas/{nomeArquivo}")
    public ResponseEntity<byte[]> getImagem(@PathVariable String nomeArquivo) throws IOException, java.io.IOException {
        Path caminho = Paths.get("uploads").resolve(nomeArquivo);
        if (!Files.exists(caminho)) {
            return ResponseEntity.notFound().build();
        }
        byte[] imagem = Files.readAllBytes(caminho);
        return ResponseEntity.ok()
                .header("Content-Type", Files.probeContentType(caminho))
                .body(imagem);
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<String>> getMarcas() {
        return ResponseEntity.ok(veiculoService.listarMarcas());
    }

    @GetMapping("/modelos")
    public ResponseEntity<List<String>> getModelos() {
        return ResponseEntity.ok(veiculoService.listarModelos());
    }
}
