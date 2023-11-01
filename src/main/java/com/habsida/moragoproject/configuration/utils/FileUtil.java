package com.habsida.moragoproject.configuration.utils;

import com.habsida.moragoproject.exception.FileSaveException;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FileUtil {

    FileRepository fileRepository;

    @Value("${path.folder}")
    private String pathFolder;

    public FileUtil(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get(pathFolder);
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new FileSaveException("Could not create directory");
            }
        }
        String fileCode = UUID.randomUUID().toString();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode+"."+extension);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new FileSaveException("Could not save file: " + fileName);
        }
        File response = new File();
        response.setType(multipartFile.getContentType());
        response.setOriginalTitle(fileName);
        response.setPath(StringUtils.cleanPath(uploadPath.toString()+"\\"+fileCode+"."+extension));
        fileRepository.save(response);
        return response;
    }

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("pathFolder");
        AtomicReference<Path> foundFile = new AtomicReference<>();
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile.set(file);
                return;
            }
        });

        if (foundFile.get() != null) {
            return new UrlResource(foundFile.get().toUri());
        }
        return null;
    }
}
