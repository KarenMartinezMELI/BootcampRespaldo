package com.meli.tucasita.unit.controller;

import com.meli.tucasita.controller.PropertyController;
import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.dto.environment.EnvironmentResponseSquareFeetDTO;
import com.meli.tucasita.dto.property.*;
import com.meli.tucasita.exception.TuCasitaApiListException;
import com.meli.tucasita.model.District;
import com.meli.tucasita.service.IPropertyService;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class PropertyControllerTest {

    private final IPropertyService propertyService =
            Mockito.mock(IPropertyService.class);

    private PropertyController propertyController;


    @BeforeEach
    void setUp(){
        this.propertyController = new PropertyController(propertyService);
    }

    @Test
    public void calculateTotalSquareFeetSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        PropertyResponseSquareFeetDTO expected= new PropertyResponseSquareFeetDTO(property.getName(),
                property.getLength()*property.getWidth());

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(expected,HttpStatus.OK);
        when( propertyService.getTotalSquareFeet(property)).thenReturn( expected );

        //Act
        ResponseEntity<?> responseEntity= propertyController.calculateTotalSquareFeet(property);

        //Assert
        verify(propertyService, atLeast(1)).getTotalSquareFeet(property);
        Assertions.assertEquals(expectedResponse,responseEntity);
    }

    @Test
    public void calculateTotalSquareFeetFailInvalidLandOrDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        when( propertyService.getTotalSquareFeet(property)).thenThrow(TuCasitaApiListException.class);

        //Act

        //Assert
        Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyController.calculateTotalSquareFeet(property));
        verify(propertyService, atLeast(1)).getTotalSquareFeet(property);
    }


    @Test
    public void calculateTotalSquareFeetUnbuiltSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        double sizes= (property.getEnvironments().get(0).getLength()*property.getEnvironments().get(0).getWidth())+
                (property.getEnvironments().get(1).getLength()*property.getEnvironments().get(1).getWidth());
        PropertyResponseSquareFeetUnbuiltDTO expected= new PropertyResponseSquareFeetUnbuiltDTO(property.getName(),
                (property.getLength()*property.getWidth())-sizes);

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(expected,HttpStatus.OK);
        when( propertyService.getTotalSquareFeetUnbuilt(property)).thenReturn( expected );

        //Act
        ResponseEntity<?> responseEntity= propertyController.calculateTotalSquareFeetUnbuilt(property);

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(propertyService, atLeast(1)).getTotalSquareFeetUnbuilt(property);
    }

    @Test
    public void calculateTotalSquareFeetUnbuiltFailInvalidLandOrDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        when( propertyService.getTotalSquareFeetUnbuilt(property)).thenThrow(TuCasitaApiListException.class);

        //Act

        //Assert
        Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyController.calculateTotalSquareFeetUnbuilt(property));
        verify(propertyService, atLeast(1)).getTotalSquareFeetUnbuilt(property);

    }

    @Test
    public void calculateTotalValueSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        District aDistrict = TestUtilsGenerator.getValidsDistricts().get(0);
        property.setDistrictName(aDistrict.getName());
        PropertyResponseTotalValueDTO expected=
                new PropertyResponseTotalValueDTO(property.getName(),
                        property.getWidth(),
                        property.getWidth(),
                        400.0,
                        300.0,
                        700.0,
                        new DistrictDTO(aDistrict.getName(),
                                aDistrict.getBuiltPrice(),
                                aDistrict.getBuiltPrice()));

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(expected,HttpStatus.OK);
        when( propertyService.getTotalValue(property)).thenReturn( expected );

        //Act
        ResponseEntity<?> responseEntity= propertyController.calculateTotalValue(property);

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(propertyService, atLeast(1)).getTotalValue(property);
    }

    @Test
    public void calculateTotalValueFailInvalidLandOrDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        when( propertyService.getTotalValue(property)).thenThrow(TuCasitaApiListException.class);

        //Act

        //Assert
        Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyController.calculateTotalValue(property));
        verify(propertyService, atLeast(1)).getTotalValue(property);
    }

    @Test
    public void calculateBiggestEnvironmentSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        property.getEnvironments().get(0).setLength(5.0);

        EnvironmentDTO expected = property.getEnvironments().get(1);

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(expected,HttpStatus.OK);
        when( propertyService.getBiggestEnvironment(property)).thenReturn( expected );

        //Act
        ResponseEntity<?> responseEntity= propertyController.calculateBiggestEnvironment(property);

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(propertyService, atLeast(1)).getBiggestEnvironment(property);
    }

    @Test
    public void calculateBiggestEnvironmentFailInvalidLandOrDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        when( propertyService.getBiggestEnvironment(property)).thenThrow(TuCasitaApiListException.class);

        //Act

        //Assert
        Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyController.calculateBiggestEnvironment(property));
        verify(propertyService, atLeast(1)).getBiggestEnvironment(property);
    }

    @Test
    public void calculateEnvironmentsTotalSquareFeetSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        property.getEnvironments().get(0).setLength(5.0);

        PropertyResponseEnvironmentsSquareFeetDTO expected = new PropertyResponseEnvironmentsSquareFeetDTO(
                property.getName(),
                new ArrayList<>(
                        Arrays.asList(
                                new EnvironmentResponseSquareFeetDTO(
                                        property.getEnvironments().get(0).getName(),
                                        property.getEnvironments().get(0).getLength()*property.getEnvironments().get(0).getWidth()),
                                new EnvironmentResponseSquareFeetDTO(
                                        property.getEnvironments().get(1).getName(),
                                        property.getEnvironments().get(1).getLength()*property.getEnvironments().get(1).getWidth())
                        ))
        );

        ResponseEntity<?> expectedResponse= new ResponseEntity<>(expected,HttpStatus.OK);
        when( propertyService.getEnvironmentsTotalSquareFeet(property)).thenReturn( expected );

        //Act
        ResponseEntity<?> responseEntity= propertyController.calculateEnvironmentsTotalSquareFeet(property);

        //Assert
        Assertions.assertEquals(expectedResponse,responseEntity);
        verify(propertyService, atLeast(1)).getEnvironmentsTotalSquareFeet(property);
    }

    @Test
    public void calculateEnvironmentTotalSquareFeetFailInvalidLandOrDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        when( propertyService.getEnvironmentsTotalSquareFeet(property)).thenThrow(TuCasitaApiListException.class);

        //Act

        //Assert
        Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyController.calculateEnvironmentsTotalSquareFeet(property));
        verify(propertyService, atLeast(1)).getEnvironmentsTotalSquareFeet(property);
    }


}
