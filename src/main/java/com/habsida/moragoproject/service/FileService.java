package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileInput;
import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class FileService {

    FileRepository fileRepository;
    UserRepository userRepository;

    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public List<File> findAll(){
        return fileRepository.findAll();
    }

    public File findById(Long id){
        return fileRepository.findById(id)
                .orElseThrow(()->new RuntimeException("File -> File doesn't find by Id"));
    }

    public File createFile(FileInput fileInput){
        File file = new File();
        if(!isNull(fileInput.getOriginalTitle()) && !fileInput.getOriginalTitle().isEmpty()){
            file.setOriginalTitle(fileInput.getOriginalTitle());
        }else{
            file.setOriginalTitle("EMPTY");
        }
        if(!isNull(fileInput.getPath()) && !fileInput.getPath().isEmpty()){
            file.setPath(fileInput.getPath());
        }else{
            file.setPath("EMPTY");
        }
        if(!isNull(fileInput.getType()) && !fileInput.getType().isEmpty()){
            file.setType(fileInput.getType());
        }else{
            file.setType("EMPTY");
        }
        return fileRepository.save(file);
    }

    public void deleteFileById(Long id){
        fileRepository.deleteById(id);
    }

    public File updateFile(Long id ,FileInput fileInput){
        File file = fileRepository.findById(id).get();
        if(!isNull(fileInput.getOriginalTitle()) && !fileInput.getOriginalTitle().isEmpty()){
            file.setOriginalTitle(fileInput.getOriginalTitle());
        }else{
            file.setOriginalTitle("EMPTY");
        }
        if(!isNull(fileInput.getPath()) && !fileInput.getPath().isEmpty()){
            file.setPath(fileInput.getPath());
        }else{
            file.setPath("EMPTY");
        }
        if(!isNull(fileInput.getType()) && !fileInput.getType().isEmpty()){
            file.setType(fileInput.getType());
        }else{
            file.setType("EMPTY");
        }
        return fileRepository.save(file);
    }
}
