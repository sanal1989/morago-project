package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.exception.FileSaveException;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.service.FileService;
import com.habsida.moragoproject.service.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Controller

public class FileUploadController {

    private FileUtil fileUtil;

    public FileUploadController(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @MutationMapping(name = "fileUpload")
    public File uploadFile(@Argument MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File response;
        try {
            response = fileUtil.saveFile(fileName, file);
        } catch (Exception e) {
            throw new FileSaveException(e.getMessage());
        }
        return response;
    }

    @MutationMapping(name = "multiFileUpload")
    public Collection<File> uploadMultiFiles(@Argument Collection<MultipartFile> files) {
        List<File> list = new ArrayList<>();
        for( MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File response;
            try {
                response = fileUtil.saveFile(fileName, file);
            } catch (Exception e) {
                throw new FileSaveException(e.getMessage());
            }
            list.add(response);
        }
        return list;
    }
}


