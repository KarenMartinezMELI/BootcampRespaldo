package com.example.CalculadoraMetrosCuadrados;

import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Barrio;
import com.example.CalculadoraMetrosCuadrados.dto.HouseDTO;
import com.example.CalculadoraMetrosCuadrados.dto.HouseResponseValueDTO;
import com.example.CalculadoraMetrosCuadrados.dto.RoomDTO;
import com.example.CalculadoraMetrosCuadrados.repository.IBarrioRepository;
import com.example.CalculadoraMetrosCuadrados.service.CalculateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class CalculadoraMetrosCuadradosApplicationTests {

	static int width1;
	static int length1;
	static int width2;
	static int length2;
	static double barrioPrice;
	static String barrioName;

	static RoomDTO room1;
	static RoomDTO room2;

	//@MockBean
	private IBarrioRepository barrioRepo =
			Mockito.mock(IBarrioRepository.class);

	//@InjectMocks
	private CalculateServiceImpl calculateService =
			new CalculateServiceImpl(barrioRepo);


	/*CalculadoraMetrosCuadradosApplicationTests() {
		this.calculateService = new CalculateServiceImpl(this.barrioRepo);
	}*/

	@BeforeAll
	static void initialize() {
		width1 = 3;
		length1 = 3;
		width2 = 2;
		length2 = 1;
		barrioPrice = 10.0;
		barrioName = "Belgrano";

		room1 = RoomDTO.builder()
				.name("patio")
				.width(width1)
				.length(length1)
				.build();

		room2 = RoomDTO.builder()
				.name("living")
				.width(width2)
				.length(length2)
				.build();
	}

	@Test
	void testCalculatePricePositiveOk() throws Exception {

		//Arrange

		HouseDTO house = HouseDTO.builder()
				.name("Mi Casa")
				.address("Constitucion 230")
				.rooms(
						Arrays.asList(
								room1,
								room2
						)
				)
				.barrio(barrioName)
				.build();

		Barrio barrio = Barrio.builder()
				.name(barrioName)
				.price(barrioPrice)
				.build();

		Double square = (double) (
				room1.getLength() * room1.getWidth() +
				room2.getLength() * room2.getWidth() );

		Double value = square * barrioPrice;

		HouseResponseValueDTO expectedResponse = HouseResponseValueDTO.builder()
				.value(value)
				.pricePeerSquadMeter(barrioPrice)
				.name(house.getName())
				.build();

		when( barrioRepo.getBarrioPorNombre( house.getBarrio() ) ).thenReturn( barrio );


		//Act
		HouseResponseValueDTO response = calculateService.calculatePrice(house);

		//Assert

		verify(barrioRepo, atLeast(1)).getBarrioPorNombre(house.getBarrio());

		Assertions.assertEquals(expectedResponse, response);

	}

	@Test
	void testCalculatePriceNoSuchBarrioThrowsException() throws Exception {

		var rooms = Arrays.asList(
				new RoomDTO(
						"patio",
						3, // a negative value
						3
				),
				new RoomDTO(
						"living",
						2,
						1
				)
		);
		//Arrange
		HouseDTO house = new HouseDTO(
				"Casa",
				"micasita@owner.com",
				"Constitucion 230",
				rooms,
				"Tunitas");

		Barrio barrio = new Barrio(
				"Tunitas",
				8.0
		);

		//when( barrioRepo.getBarrioPorNombre( house.getBarrio() ) ).thenReturn( barrio );
		when( barrioRepo.getBarrioPorNombre( "Tunitas" ) ).thenThrow(NoSuchBarrioException.class);


		//Act
		//Assert

		Assertions.assertThrows(NoSuchBarrioException.class, ()-> calculateService.calculatePrice(house) );

	}

}
