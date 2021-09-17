package com.example.CalculadoraMetrosCuadrados.utils;

import com.example.CalculadoraMetrosCuadrados.dto.HouseDTO;
import com.example.CalculadoraMetrosCuadrados.dto.RoomDTO;
import com.example.CalculadoraMetrosCuadrados.model.Neighborhood;

public class TestUtils {
    final static public String room1Name		= "living";
    final static public int room1Width 		= 3;
    final static public int room1Length 	= 3;

    final static public String room2Name		= "kitchen";
    final static public int room2Width 		= 2;
    final static public int room2Length 	= 1;

    final static public String neighborhoodName = "Belgrano";
    final static public double neighborhoodPrice = 10.0;

    final static public String houseName 		= "Mi casita";
    final static public String houseAddress 	= "Chacabuco 2334";
    final static public String houseOwnerEmail = "ownerIm@ILoveMiCasita.com";


    static public RoomDTO.RoomDTOBuilder room1Builder;
    static public RoomDTO.RoomDTOBuilder room2Builder;

    //static public RoomDTO room1;
    //static public RoomDTO room2;

    static public HouseDTO.HouseDTOBuilder houseBuilder;

    //static public HouseDTO house;

    static public Neighborhood.NeighborhoodBuilder neighborhoodBuilder;
    //static public Neighborhood neighborhood;

    static public void inicBuilders() {

        room1Builder = RoomDTO.builder()
                .name(room1Name)
                .width(room1Width)
                .length(room1Length);

        room2Builder = RoomDTO.builder()
                .name(room2Name)
                .width(room2Width)
                .length(room2Length);

        houseBuilder = HouseDTO.builder()
                .name(houseName)
                .address(houseAddress)
                .barrio(neighborhoodName)
                .ownerEmail(houseOwnerEmail);

        neighborhoodBuilder = Neighborhood.builder()
                .name(neighborhoodName)
                .price(neighborhoodPrice);

    }

}
