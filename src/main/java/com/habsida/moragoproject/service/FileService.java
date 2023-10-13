package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.FileSaveException;
import com.habsida.moragoproject.exception.NotFoundById;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.input.FileInput;
import com.habsida.moragoproject.repository.FileRepository;
import com.habsida.moragoproject.repository.UserRepository;
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
    static int idFileDirectory = 1;
    public FileService(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }

    public List<File> findAll(){
        return fileRepository.findAll();
    }

    public File findById(Long id){
        return fileRepository.findById(id)
                .orElseThrow(()->new NotFoundById("File -> File doesn't find by Id " +id));
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

    public String deleteFileById(Long id){
        try {
            fileRepository.deleteById(id);
        }catch (Exception e){
            throw new NotFoundById(e.getMessage());
        }
        return "File with Id "+id+" deleted";
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

    public File saveFile(String fileName, MultipartFile multipartFile) {
        Path uploadPath = Paths.get("C:\\Files-Upload");
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new FileSaveException("Could not create directory");
            }
        }
        String fileCode = UUID.randomUUID().toString();
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode+"."+extension);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new FileSaveException("Could not save file: " + fileName);
        }
        File response = new File();
        response.setType(multipartFile.getContentType());
        response.setOriginalTitle(fileName);
        response.setPath(StringUtils.cleanPath(uploadPath.toString()+"\\"+fileCode+"."+extension));
        fileRepository.save(response);
        return response;
    }

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("C:\\Files-Upload");
        AtomicReference<Path> foundFile = new AtomicReference<>();
        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile.set(file);
                return;
            }
        });

        if (foundFile.get() != null) {
            return new UrlResource(foundFile.get().toUri());
        }
        return null;
    }
}
