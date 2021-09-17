package com.example.CalculadoraMetrosCuadrados.repository;


import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Barrio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class BarrioRepository implements IBarrioRepository {

    List<Barrio> barrios;

    public BarrioRepository() {
        this.barrios = loadFromDB();
    }

    private List<Barrio> loadFromDB() {
        List<Barrio> ret = null;

        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:m2PorBarrio.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();
        //TypeReference<List<BarrioDTO>> typeRef = new TypeReference<>() {};

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<Barrio>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public Barrio getBarrioPorNombre(String name) throws NoSuchBarrioException {

        return this.barrios.stream()
                .filter( barrio -> barrio.getName().equals(name) )
                .findFirst().orElseThrow( () -> new NoSuchBarrioException(name) );
    }

}
