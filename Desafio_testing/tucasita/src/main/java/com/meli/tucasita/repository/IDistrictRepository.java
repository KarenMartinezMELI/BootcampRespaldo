package com.meli.tucasita.repository;

import com.meli.tucasita.model.District;

import java.util.List;
import java.util.Optional;

public interface IDistrictRepository {

    Optional<District> findByName(String name);
    List<District> getAll();
    void reloadData();
    void deleteByName(String name);
    void add(District district);
}
