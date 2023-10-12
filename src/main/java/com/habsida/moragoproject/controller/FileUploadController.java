package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.scalar.FileUpload;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@PreAuthorize("isAnonymous()")
public class FileUploadController {

    @MutationMapping
    public Boolean singleUpload(@Argument FileUpload fileUpload) throws IOException {
        byte[] fileContent = new byte[0];
        fileContent = fileUpload.getContent();

        // Do something in order to persist the file :)
        Path uploadPath = Paths.get("C:\\Files-Upload");
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//        String fileCode = Integer.toString(idFileDirectory);
        String fileCode = UUID.randomUUID().toString().split("-")[0];

        try (InputStream inputStream = new ByteArrayInputStream(fileContent)) {
            Path filePath = uploadPath.resolve(fileCode);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            try {
                throw new IOException("Could not save file: ", ioe);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    @QueryMapping
    public String test(){
        return "test";
    }


}
