package com.example.CalculadoraMetrosCuadrados.integrations;
import com.example.CalculadoraMetrosCuadrados.utils.TestUtils;
import com.example.CalculadoraMetrosCuadrados.dto.HouseDTO;
import com.example.CalculadoraMetrosCuadrados.dto.RoomDTO;
import com.example.CalculadoraMetrosCuadrados.model.Neighborhood;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.example.CalculadoraMetrosCuadrados.utils.TestUtils.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculateRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper;
    private static List<RoomDTO> rooms;

    @BeforeAll
    static void setUp() {
        TestUtils.inicBuilders();

        mapper = new ObjectMapper();
        rooms = List.of(
                room1Builder.build(),
                room2Builder.build()
        );
    }

    @Test
    void testValidSquareMeters() throws Exception {
        // Build rooms
        RoomDTO room1 = room1Builder.build();
        RoomDTO room2 = room2Builder.build();

        // Build house adding rooms
        HouseDTO house = houseBuilder
                .rooms(
                        Arrays.asList(
                                room1,
                                room2 ) )
                .build();

        // Build neighborhood;
        Neighborhood neighborhood = neighborhoodBuilder.build();

        var square = 	room1.getLength() * room1.getWidth() +
                        room2.getLength() * room2.getWidth() ;

        //Make correct houseValuation
        Double houseValuation = square * neighborhoodPrice;

        String jsonPayload = mapper.writeValueAsString(house);

        this.mockMvc
                .perform(
                        post("/calculatePrice")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.value")
                        .value(houseValuation))
                .andExpect(jsonPath("$.pricePeerSquadMeter")
                        .value(neighborhoodPrice))
                .andExpect(jsonPath("$.name")
                        .value(houseName));

    }

    @Test
    void testInvalidMailPayload() throws Exception {
        // Build rooms
        RoomDTO room1 = room1Builder.build();
        RoomDTO room2 = room2Builder.build();

        // Build house adding rooms
        HouseDTO house = houseBuilder
                .rooms(
                        Arrays.asList(
                                room1,
                                room2 ) )
                .build();

        house.setOwnerEmail("");
        house.setName("");

        // Build neighborhood;
        Neighborhood neighborhood = neighborhoodBuilder.build();

        var square = 	room1.getLength() * room1.getWidth() +
                room2.getLength() * room2.getWidth() ;

        //Make correct houseValuation
        Double houseValuation = square * neighborhoodPrice;

        String jsonPayload = mapper.writeValueAsString(house);

        this.mockMvc
                .perform(
                        post("/calculatePrice")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.code")
                    .value("MethodArgumentNotValidException"));
    }
    @Test
    void testThrowException() throws Exception {
        // Build rooms
        RoomDTO room1 = room1Builder.build();
        RoomDTO room2 = room2Builder.build();

        // Build house adding rooms
        HouseDTO house = houseBuilder
                .rooms(
                        Arrays.asList(
                                room1,
                                room2 ) )
                .build();

        house.setBarrio("Serena");

        // Build neighborhood;
        Neighborhood neighborhood = neighborhoodBuilder.build();

        var square = 	room1.getLength() * room1.getWidth() +
                room2.getLength() * room2.getWidth() ;

        //Make correct houseValuation
        Double houseValuation = square * neighborhoodPrice;

        String jsonPayload = mapper.writeValueAsString(house);

        this.mockMvc
                .perform(
                        post("/calculatePrice")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }

}