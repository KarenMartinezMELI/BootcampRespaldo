package com.meli.tucasita.service;

import com.meli.tucasita.dto.district.DistrictDTO;

import java.util.List;

public interface IDistrictService {
    DistrictDTO getDistrictByName(String name);
    List<DistrictDTO> getDistricts();
    DistrictDTO addDistrict(DistrictDTO districtDTO);
    void removeDistrictByName(String name);
}
