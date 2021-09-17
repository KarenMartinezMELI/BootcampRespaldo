package com.example.CalculadoraMetrosCuadrados.service;

import com.example.CalculadoraMetrosCuadrados.dto.*;
import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceDummyImpl implements ICalculateService{
    @Override
    public HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(HouseDTO house) {
        return null;
    }

    @Override
    public HouseResponseValueDTO calculatePrice(HouseDTO house) throws NoSuchBarrioException {
        throw new NoSuchBarrioException("DummyBarrio");
    }

    @Override
    public RoomDTO getBiggestRoom(HouseDTO house) {
        return null;
    }

    @Override
    public HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(HouseDTO house) {
        return null;
    }
}
