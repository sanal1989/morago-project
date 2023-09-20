package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileInput;
import com.habsida.moragoproject.service.FileService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
    public File findFileById(Long id){
        return fileService.findById(id);
    }

    @MutationMapping
    public File createFile(@Argument FileInput fileInput){
        return fileService.createFile(fileInput);
    }

    @MutationMapping
    public void deleteFileById(@Argument Long id){
        fileService.deleteFileById(id);
    }

    @MutationMapping
    public File updateFile(@Argument Long id, @Argument FileInput fileInput){
        return fileService.updateFile(id, fileInput);
    }
}
