package com.meli.tucasita.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.tucasita.exception.FailedToSaveDataException;
import com.meli.tucasita.model.District;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Primary
public class DistrictRepositoryImp implements IDistrictRepository {

    List<District> districts;
    private String SCOPE;
    private String fileName;

    public DistrictRepositoryImp() {
        Properties properties =  new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.SCOPE = properties.getProperty("api.scope");
            this.fileName= properties.getProperty("repository.json");
            this.loadData();
        } catch (IOException e) {
            System.out.println("Error al inicializar la DB del archivo de properties.");
        }
    }


    @Override
    public Optional<District> findByName(String name) {
        return districts.stream()
                .filter(stu -> stu.getName().equals(name))
                .findFirst();
    }

    @Override
    public void add(District district) {
        this.districts.add(district);
        saveData();
    }

    @Override
    public void deleteByName(String name) {
        Optional<District> element=findByName(name);
        this.districts.remove(element.get());
        saveData();
    }

    @Override
    public List<District> getAll() {
        return districts;
    }

    private void loadData() {
        Set<District> loadedData = new HashSet<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/"+fileName);
            loadedData = objectMapper.readValue(file, new TypeReference<Set<District>>(){});
        } catch (FileNotFoundException e) {
            System.out.println("Error al inicializar la DB, revise los archivos en resources.");
        } catch (IOException e) {
            System.out.println("Error al inicializar la DB, revise el formateo del JSON.");
        }

        this.districts = loadedData.stream().collect(Collectors.toList());
    }

    private void saveData(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = ResourceUtils.getFile("./src/" + SCOPE + "/resources/" + fileName);
            objectMapper.writeValue(file, this.districts);
        }catch(IOException ex){
            throw new FailedToSaveDataException("");
        }
    }

    public void reloadData(){
        loadData();
    }


}
