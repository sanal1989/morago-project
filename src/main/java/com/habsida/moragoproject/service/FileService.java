package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.FileDao;
import com.habsida.moragoproject.entity.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    FileDao fileDao;

    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public List<File> findAll(){
        return fileDao.findAll();
    }

    public File findById(Long id){
        return fileDao.findById(id);
    }

    public File addFile(File file){
        return fileDao.addFile(file);
    }

    public void deleteFile(Long id){
        fileDao.deleteFile(id);
    }

    public File editFile(File file){
        return fileDao.editFile(file);
    }
}
