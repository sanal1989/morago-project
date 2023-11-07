package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.entity.FrequentlyAskedQuestion;
import com.habsida.moragoproject.model.input.FileInput;
import com.habsida.moragoproject.service.FileService;
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
import java.util.List;
import java.util.UUID;

@Controller
@PreAuthorize("isAuthenticated()")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @QueryMapping
    public List<File> findAllFile(){
        return fileService.findAll();
    }

    @QueryMapping
    public List<File> findAllFilePagination(@Argument int offset, @Argument int limit){
        return fileService.findAll(offset, limit);
    }

    @QueryMapping
    public File findFileById(Long id){
        return fileService.findById(id);
    }

    @MutationMapping
    public File createFile(@Argument MultipartFile file){
        return fileService.createFile(file);
    }

    @MutationMapping
    public String deleteFileById(@Argument Long id){
        return fileService.deleteFileById(id);
    }

    @MutationMapping
    public File updateFile(@Argument Long id, @Argument MultipartFile file){
        return fileService.updateFile(id, file);
    }


}
