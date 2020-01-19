package com.files.service;

import com.files.model.Files;
import com.files.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService {

    @Autowired
    private final FilesRepository filesRepository;

    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public void save(Files files){
        filesRepository.save(files);
    }

    public Files findById(int id){
        return filesRepository.findById(id).orElse(null);
    }

    public List<Files> findAll(){
        return filesRepository.findAll();
    }

    public void update(Files files){
        filesRepository.save(files);
    }

    public void delete(int id){
        filesRepository.deleteById(id);
    }
}
