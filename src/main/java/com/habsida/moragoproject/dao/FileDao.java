package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.model.entity.File;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileDao {

    FileRepository fileRepository;

    public FileDao(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> findAll(){
        return fileRepository.findAll();
    }

    public File findById(Long id){
        return fileRepository.findById(id).get();
    }

    public File addFile(File file){
        return fileRepository.save(file);
    }

    public void deleteFile(Long id){
        fileRepository.deleteById(id);
    }

    public File editFile(File file){
        File fileFromDB = fileRepository.findById(file.getId()).get();
        if(!file.getOriginalTitle().equals(fileFromDB.getOriginalTitle())){
            fileFromDB.setOriginalTitle(file.getOriginalTitle());
        }
        if(!file.getPath().equals(fileFromDB.getPath())){
            fileFromDB.setPath(file.getPath());
        }
        if(!file.getType().equals(fileFromDB.getType())){
            fileFromDB.setType(file.getType());
        }
        return fileRepository.save(fileFromDB);
    }
}
