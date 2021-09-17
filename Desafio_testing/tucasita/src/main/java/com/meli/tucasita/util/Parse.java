package com.meli.tucasita.util;

import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.model.District;

public class Parse {

    public static DistrictDTO parseDistrictToDTO(District district){
        return new DistrictDTO(district.getName(),district.getBuiltPrice(),district.getUnbuiltPrice());
    }

    public static District parseDistrictDTOToDistrict(DistrictDTO district){
        return new District(district.getName(),district.getBuiltPrice(),district.getUnbuiltPrice());
    }

}
