package com.example.CalculadoraMetrosCuadrados.repository;


import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Neighborhood;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class BarrioRepository implements IBarrioRepository {

    List<Neighborhood> neighborhoods;

    @Value("${repository.json}")
    private String jsonFile;

    private List<Neighborhood> loadFromDB() {
        List<Neighborhood> ret = null;

        File file = null;

        try {
            String path = "classpath:" + this.jsonFile;
            file = ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();
        //TypeReference<List<BarrioDTO>> typeRef = new TypeReference<>() {};

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<Neighborhood>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public Neighborhood getBarrioPorNombre(String name) throws NoSuchBarrioException {
        if(neighborhoods == null)
            this.neighborhoods = loadFromDB();
        return this.neighborhoods.stream()
                .filter(neighborhood -> neighborhood.getName().equals(name) )
                .findFirst().orElseThrow( () -> new NoSuchBarrioException(name) );
    }

}
