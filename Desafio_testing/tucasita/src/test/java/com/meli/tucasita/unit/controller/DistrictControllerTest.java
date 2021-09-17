package com.meli.tucasita.unit.controller;

import com.meli.tucasita.controller.DistrictController;
import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.exception.district.DistrictWNameAlreadyExistsException;
import com.meli.tucasita.exception.district.NoSuchDistrictNameException;
import com.meli.tucasita.service.IDistrictService;
import com.meli.tucasita.util.Parse;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;


public class DistrictControllerTest {

    private final IDistrictService districtService =
            Mockito.mock(IDistrictService.class);

    private DistrictController districtController;


    @BeforeEach
    void setUp(){
        this.districtController = new DistrictController(districtService);
    }

    @Test
    public void addDistrictSuccess(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        districtDTO.setName("BarrioNuevo");

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(districtDTO, HttpStatus.OK);
        when( districtService.addDistrict(districtDTO)).thenReturn( districtDTO );

        //Act
        ResponseEntity<?> responseEntity= districtController.addDistrict(districtDTO);

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(districtService, atLeast(1)).addDistrict(districtDTO);
    }

    @Test
    public void addDistrictFailNameAlreadyExist(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        when( districtService.addDistrict(districtDTO)).thenThrow(DistrictWNameAlreadyExistsException.class);

        //Act

        //Assert
        Assertions.assertThrows(DistrictWNameAlreadyExistsException.class,
                ()-> districtService.addDistrict(districtDTO));
        verify(districtService, atLeast(1)).addDistrict(districtDTO);

    }

    @Test
    public void deleteDistrictByNameSuccess(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));

        ResponseEntity<?> expectedResponse= new ResponseEntity<>("OK", HttpStatus.OK);

        //Act
        ResponseEntity<?> responseEntity= districtController.deleteDistrictByName(districtDTO.getName());

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(districtService, atLeast(1)).removeDistrictByName(districtDTO.getName());
    }

    @Test
    public void deleteDistrictByNameFailNameDoesNotExist(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        districtDTO.setName("BarrioInexistente");
        doThrow(NoSuchDistrictNameException.class)
                .when(districtService)
                .removeDistrictByName(districtDTO.getName());
        //Act

        //Assert
        Assertions.assertThrows(NoSuchDistrictNameException.class,
                ()-> districtService.removeDistrictByName(districtDTO.getName()));
        verify(districtService, atLeast(1)).removeDistrictByName(districtDTO.getName());

    }


    @Test
    public void getDistrictByNameSuccess(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(districtDTO, HttpStatus.OK);
        when( districtService.getDistrictByName(districtDTO.getName())).thenReturn( districtDTO);

        //Act
        ResponseEntity<?> responseEntity= districtController.getDistrictByName(districtDTO.getName());

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(districtService, atLeast(1)).getDistrictByName(districtDTO.getName());
    }

    @Test
    public void getDistrictByNameFailNameDoesNotExist(){
        //Arrange
        DistrictDTO districtDTO = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        districtDTO.setName("BarrioInexistente");
        when( districtService.getDistrictByName(districtDTO.getName())).thenThrow(NoSuchDistrictNameException.class);

        //Act

        //Assert
        Assertions.assertThrows(NoSuchDistrictNameException.class,
                ()-> districtService.getDistrictByName(districtDTO.getName()));
        verify(districtService, atLeast(1)).getDistrictByName(districtDTO.getName());

    }

    @Test
    public void getAllDistrictsSuccess(){
        //Arrange
        List<DistrictDTO> districtsDTO = TestUtilsGenerator.getValidsDistricts().stream().map(d->Parse.parseDistrictToDTO(d)).collect(Collectors.toList());

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(districtsDTO, HttpStatus.OK);
        when( districtService.getDistricts()).thenReturn( districtsDTO );

        //Act
        ResponseEntity<?> responseEntity= districtController.getAllDistricts();

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(districtService, atLeast(1)).getDistricts();
    }

}
