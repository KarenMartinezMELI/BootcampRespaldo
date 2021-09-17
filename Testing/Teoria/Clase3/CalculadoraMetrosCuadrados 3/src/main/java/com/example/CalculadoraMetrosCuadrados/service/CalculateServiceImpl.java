package com.example.CalculadoraMetrosCuadrados.service;

import com.example.CalculadoraMetrosCuadrados.dto.*;
import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.repository.BarrioRepository;
import com.example.CalculadoraMetrosCuadrados.repository.IBarrioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary
public class CalculateServiceImpl implements ICalculateService {

  IBarrioRepository barrioRepository;

  //@Autowired
  public CalculateServiceImpl(IBarrioRepository barrioRepo) {
    this.barrioRepository = barrioRepo;
  }

  @Override
  public HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(HouseDTO house) {
    System.out.println(this);
    var response = new HouseResponseTotalSquareFeetDTO( calculateRoomSquareFeet(house) );

    return response;
  }

  @Override
  public HouseResponseValueDTO calculatePrice(HouseDTO house) throws NoSuchBarrioException {
    var price = priceByBarrio( house.getBarrio() );
    var square = calculateRoomSquareFeet(house) * price ;
    var response = new HouseResponseValueDTO( square, price, house.getName() );
    return response;
  }

  @Override
  public RoomDTO getBiggestRoom(HouseDTO house) {
    return calculateBiggestRoom(house);
  }

  @Override
  public HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(HouseDTO house) {
    var rooms = new ArrayList<RoomSquareFeetDTO>();

    for (RoomDTO room : house.getRooms()) {
      Integer squareFeet = getSquareFeet(room);
      rooms.add(new RoomSquareFeetDTO( room.getName(), squareFeet ));
    }

    var response = new HouseResponseSquareFeetPerRoomDTO( rooms );
    return response;
  }

  private Integer getSquareFeet(RoomDTO room) {
    Integer result = 0;
    if(room.getWidth() != null && room.getLength() != null)
      result = room.getWidth() * room.getLength();
    return result;
  }

  private Integer calculateRoomSquareFeet(HouseDTO house) {
    Integer totalSquareFeet = 0;
    for (RoomDTO room : house.getRooms()) {
      totalSquareFeet += getSquareFeet(room);
    }

    return totalSquareFeet;
  }

  private RoomDTO calculateBiggestRoom(HouseDTO house) {
    RoomDTO biggest = null;
    Integer maxRoom = 0;
    for (RoomDTO room : house.getRooms()) {
      Integer squareFeet = getSquareFeet(room);
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }

    return biggest;
  }

  private Double priceByBarrio(String barrioName) throws NoSuchBarrioException{
      return barrioRepository.getBarrioPorNombre(barrioName).getPrice();
  }

}
