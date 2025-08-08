package com.concessionariamanager.application.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadService {

    private final Path pastaUploads = Paths.get("uploads");

    public String salvarArquivo(MultipartFile arquivo) throws IOException {
        if (!Files.exists(pastaUploads)) {
            Files.createDirectories(pastaUploads);
        }

        String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path destino = pastaUploads.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), destino);

        return nomeArquivo;
    }
}


