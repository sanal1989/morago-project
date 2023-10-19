package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.FileSaveException;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileInput;
import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.isNull;

@Service
public class FileService {

    FileRepository fileRepository;
    UserRepository userRepository;

    @Value("${path.folder}")
    private String pathFolder;

    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public List<File> findAll(){
        return fileRepository.findAll();
    }

    public File findById(Long id){
        return fileRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("File -> File doesn't find by Id " +id));
    }

    public File createFile(MultipartFile multipartFile){
        File response = new File();
        response.setType(multipartFile.getContentType());
        response.setOriginalTitle(multipartFile.getOriginalFilename());
        response.setPath(StringUtils.cleanPath(pathFolder));
        return response;
    }

    public String deleteFileById(Long id){
        try {
            File file = fileRepository.findById(id).get();
            Files.delete(Paths.get(file.getPath()));
            fileRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundByIdException(e.getMessage());
        }
        return "File with Id "+id+" deleted";
    }

    public File updateFile(Long id ,MultipartFile multipartFile){
        File file = fileRepository.findById(id).get();
        if(!isNull(multipartFile.getOriginalFilename()) && !multipartFile.getOriginalFilename().isEmpty()){
            file.setOriginalTitle(multipartFile.getOriginalFilename());
        }
        if(!isNull(multipartFile.getContentType()) && !multipartFile.getContentType().isEmpty()){
            file.setType(multipartFile.getContentType());
        }
        return fileRepository.save(file);
    }

}
