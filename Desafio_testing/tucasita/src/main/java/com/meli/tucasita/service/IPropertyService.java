package com.meli.tucasita.service;

import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.dto.property.*;

public interface IPropertyService {

    PropertyResponseSquareFeetDTO getTotalSquareFeet(PropertyDTO property);
    PropertyResponseSquareFeetUnbuiltDTO getTotalSquareFeetUnbuilt(PropertyDTO property);
    EnvironmentDTO getBiggestEnvironment(PropertyDTO property);
    PropertyResponseTotalValueDTO getTotalValue(PropertyDTO property);
    PropertyResponseEnvironmentsSquareFeetDTO getEnvironmentsTotalSquareFeet(PropertyDTO property);
}
