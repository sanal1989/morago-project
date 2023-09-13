package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.entity.File;
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
    public File addFile(@Argument String originalTitle, @Argument String path, @Argument String fileType){
        File file = new File();
        file.setOriginalTitle(originalTitle);
        file.setPath(path);
        file.setType(fileType);
        return fileService.addFile(file);
    }

    @MutationMapping
    public void deleteFile(@Argument Long id){
        fileService.deleteFile(id);
    }

    @MutationMapping
    public File editFile(@Argument Long id, @Argument String originalTitle, @Argument String path, @Argument String fileType){
        File file = new File();
        file.setId(id);
        file.setOriginalTitle(originalTitle);
        file.setPath(path);
        file.setType(fileType);
        return fileService.editFile(file);
    }
}
