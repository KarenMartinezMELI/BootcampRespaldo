package com.example.CalculadoraMetrosCuadrados;

import com.example.CalculadoraMetrosCuadrados.utils.TestUtils;
import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Neighborhood;
import com.example.CalculadoraMetrosCuadrados.dto.HouseDTO;
import com.example.CalculadoraMetrosCuadrados.dto.HouseResponseValueDTO;
import com.example.CalculadoraMetrosCuadrados.dto.RoomDTO;
import com.example.CalculadoraMetrosCuadrados.repository.IBarrioRepository;
import com.example.CalculadoraMetrosCuadrados.service.CalculateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static com.example.CalculadoraMetrosCuadrados.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//!FIXME [1] Performance unit test
@ExtendWith(MockitoExtension.class)
class CalculadoraMetrosCuadradosUnitTests {

	@Mock
	private IBarrioRepository barrioRepo;// = Mockito.mock(IBarrioRepository.class);

	@InjectMocks
	private CalculateServiceImpl calculateService;// = new CalculateServiceImpl(barrioRepo);

	@BeforeAll
	static void initialize() {
		TestUtils.inicBuilders();
	}

	@Test
	void testCalculatePricePositiveOk() throws Exception {

		//Arrange

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

		var expectedResponse = HouseResponseValueDTO.builder()
				.value(houseValuation)
				.pricePeerSquadMeter(neighborhoodPrice)
				.name(houseName)
				.build();

		when( barrioRepo.getBarrioPorNombre( house.getBarrio() ) )
				.thenReturn(neighborhood);


		//Act
		HouseResponseValueDTO response = calculateService.calculatePrice(house);

		//Assert

		verify(barrioRepo, atLeast(1)).getBarrioPorNombre(house.getBarrio());

		assertEquals(expectedResponse, response);

	}

	@Test
	void testCalculatePriceNoSuchBarrioThrowsException() throws Exception {
		//Arrange

		// Build default house with neighborhoodName
		var house = houseBuilder.build();

		when( barrioRepo.getBarrioPorNombre( neighborhoodName ) )
				.thenThrow(NoSuchBarrioException.class);

		//Act
		//Assert
		assertThrows(NoSuchBarrioException.class,
				()-> calculateService.calculatePrice(house) );

	}

}
