package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.FileRepository;
import org.springframework.stereotype.Component;

@Component
public class FileDao {

    FileRepository fileRepository;

    public FileDao(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
}
