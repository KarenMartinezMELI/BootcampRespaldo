package com.example.CalculadoraMetrosCuadrados.service;

import com.example.CalculadoraMetrosCuadrados.dto.*;
import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;

public interface ICalculateService {

    HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(HouseDTO house);

    HouseResponseValueDTO calculatePrice(HouseDTO house) throws NoSuchBarrioException;

    RoomDTO getBiggestRoom(HouseDTO house);

    HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(HouseDTO house);
}
