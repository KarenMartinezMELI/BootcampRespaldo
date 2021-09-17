package com.meli.tucasita.unit.service;

import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.dto.environment.EnvironmentResponseSquareFeetDTO;
import com.meli.tucasita.dto.property.*;
import com.meli.tucasita.exception.TuCasitaApiException;
import com.meli.tucasita.exception.TuCasitaApiListException;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.service.PropertyServiceImp;
import com.meli.tucasita.service.IPropertyService;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PropertyServiceTest {

    private final IDistrictRepository districtRepository =
            Mockito.mock(IDistrictRepository.class);

    private IPropertyService propertyService;

    @BeforeEach
    void setUp(){
        this.propertyService = new PropertyServiceImp(districtRepository);
        TestUtilsGenerator.loadDefaultDataInJson();
        districtRepository.reloadData();
    }

    @Test
    void getTotalSquareFeetSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        double propertySize= property.getLength()*property.getWidth();
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());
        PropertyResponseSquareFeetDTO expectedResponse=new PropertyResponseSquareFeetDTO(property.getName(),propertySize);
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act
        PropertyResponseSquareFeetDTO responseProperty=  propertyService.getTotalSquareFeet(property);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
        Assertions.assertEquals(expectedResponse,responseProperty);
    }
    @Test
    void getTotalSquareFeetFailInvalidDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWInvalidDistrictSettings();
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( Optional.empty() );

        //Assert
        TuCasitaApiListException exceptions =Assertions.assertThrows(TuCasitaApiListException.class, ()-> propertyService.getTotalSquareFeet(property));

        Optional<TuCasitaApiException> exception=exceptions.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.district.NoSuchDistrictNameException")
                        &&e.getMessage().equals(property.getDistrictName() +" no ha sido encontrado.")
        ).findFirst();
        Assertions.assertNotEquals(Optional.empty(),exception);
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getTotalSquareFeetFailInvalidLand(){
        //Arrange
        double diff=-475.0;
        PropertyDTO property = TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Assert
        TuCasitaApiListException exception = Assertions.assertThrows(TuCasitaApiListException.class, ()-> propertyService.getTotalSquareFeet(property));
        Assertions.assertNotEquals(Optional.empty(),exception.getExceptionList().stream().filter(
          e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException")
          &&e.getMessage().equals("La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0.")
        ).findFirst()
        );
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }

    @Test
    void getTotalSquareFeetUnbuiltSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        double propertySize= property.getLength()*property.getWidth();
        double builtSize= property.getEnvironments().get(0).getLength()*property.getEnvironments().get(0).getWidth();
        builtSize+=property.getEnvironments().get(1).getLength()*property.getEnvironments().get(1).getWidth();
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));

        property.setDistrictName(district.get().getName());
        PropertyResponseSquareFeetUnbuiltDTO expectedResponse=
                new PropertyResponseSquareFeetUnbuiltDTO(property.getName(),propertySize-builtSize);
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act
        PropertyResponseSquareFeetUnbuiltDTO responseProperty=  propertyService.getTotalSquareFeetUnbuilt(property);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
        Assertions.assertEquals(expectedResponse,responseProperty);
    }
    @Test
    void getTotalSquareFeetUnbuiltFailInvalidDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWInvalidDistrictSettings();
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( Optional.empty() );

        //Act

        //Assert
        TuCasitaApiListException exceptions =Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getTotalSquareFeetUnbuilt(property));

        Optional<TuCasitaApiException> exception=exceptions.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.district.NoSuchDistrictNameException")
                        &&e.getMessage().equals(property.getDistrictName() +" no ha sido encontrado.")
        ).findFirst();
        Assertions.assertNotEquals(Optional.empty(),exception);
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }

    @Test
    void getTotalSquareFeetUnbuiltFailInvalidLand(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        double diff=-475.0;
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act

        //Assert
        TuCasitaApiListException exception = Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getTotalSquareFeetUnbuilt(property));
        Assertions.assertNotEquals(Optional.empty(),exception.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException")
                        &&e.getMessage().equals("La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0.")
                ).findFirst()
        );
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }

    @Test
    void getBiggestEnvironmentSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        property.getEnvironments().get(0).setLength(5.0);

        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));

        property.setDistrictName(district.get().getName());
        EnvironmentDTO expectedResponse=
                new EnvironmentDTO(property.getEnvironments().get(1).getName(),
                        property.getEnvironments().get(1).getWidth(),
                        property.getEnvironments().get(1).getLength());

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act
        EnvironmentDTO responseProperty=  propertyService.getBiggestEnvironment(property);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
        Assertions.assertEquals(expectedResponse,responseProperty);
    }
    @Test
    void getBiggestEnvironmentFailInvalidDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWInvalidDistrictSettings();
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( Optional.empty() );

        //Assert
        TuCasitaApiListException exceptions =Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getBiggestEnvironment(property));

        Optional<TuCasitaApiException> exception=exceptions.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.district.NoSuchDistrictNameException")
                        &&e.getMessage().equals(property.getDistrictName() +" no ha sido encontrado.")
        ).findFirst();
        Assertions.assertNotEquals(Optional.empty(),exception);
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getBiggestEnvironmentFailInvalidLand(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        double diff=-475.0;
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Assert
        TuCasitaApiListException exception = Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getBiggestEnvironment(property));
        Assertions.assertNotEquals(Optional.empty(),exception.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException")
                        &&e.getMessage().equals("La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0.")
                ).findFirst()
        );
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getTotalValueSuccess(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        double propertySize= property.getLength()*property.getWidth();
        double builtSize= property.getEnvironments().get(0).getLength()*property.getEnvironments().get(0).getWidth();
        builtSize+=property.getEnvironments().get(1).getLength()*property.getEnvironments().get(1).getWidth();
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        double builtValue=builtSize*district.get().getBuiltPrice();
        double unbuiltValue=(propertySize-builtSize)*district.get().getUnbuiltPrice();
        double totalValue=builtValue+unbuiltValue;


        PropertyResponseTotalValueDTO expectedResponse=
                new PropertyResponseTotalValueDTO(property.getName(),
                        property.getWidth(),
                        property.getLength(),
                        builtValue,
                        unbuiltValue,
                        totalValue,
                        new DistrictDTO(district.get().getName(),
                                district.get().getBuiltPrice(),
                                district.get().getUnbuiltPrice()));

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act
        PropertyResponseTotalValueDTO responseProperty=  propertyService.getTotalValue(property);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
        Assertions.assertEquals(expectedResponse,responseProperty);
    }
    @Test
    void getTotalValueFailInvalidDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWInvalidDistrictSettings();
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( Optional.empty() );

        //Assert
        TuCasitaApiListException exceptions =Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getTotalValue(property));

        Optional<TuCasitaApiException> exception=exceptions.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.district.NoSuchDistrictNameException")
                        &&e.getMessage().equals(property.getDistrictName() +" no ha sido encontrado.")
        ).findFirst();
        Assertions.assertNotEquals(Optional.empty(),exception);
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getTotalValueFailInvalidFailInvalidLand(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        double diff=-475.0;
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Assert
        TuCasitaApiListException exception = Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getTotalValue(property));
        Assertions.assertNotEquals(Optional.empty(),exception.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException")
                        &&e.getMessage().equals("La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0.")
                ).findFirst()
        );
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getEnvironmentsTotalSquareFeetSuccessAndCorrect(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getValidProperty();
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        EnvironmentDTO room1= property.getEnvironments().get(0);
        EnvironmentDTO room2= property.getEnvironments().get(1);

        PropertyResponseEnvironmentsSquareFeetDTO expectedResponse=
                new PropertyResponseEnvironmentsSquareFeetDTO(property.getName(),
                        new ArrayList<>(Arrays.asList(
                                new EnvironmentResponseSquareFeetDTO(
                                        room1.getName(),
                                        room1.getLength()*room2.getWidth()),
                                new EnvironmentResponseSquareFeetDTO(
                                        room2.getName(),
                                        room2.getLength()*room2.getWidth())
                        ))
                );

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Act
        PropertyResponseEnvironmentsSquareFeetDTO responseProperty=  propertyService.getEnvironmentsTotalSquareFeet(property);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
        Assertions.assertEquals(expectedResponse,responseProperty);
    }
    @Test
    void getEnvironmentsTotalSquareFeetFailInvalidDistrict(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getPropertyWInvalidDistrictSettings();
        when( districtRepository.findByName(property.getDistrictName())).thenReturn( Optional.empty() );

        //Assert
        TuCasitaApiListException exceptions =Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getEnvironmentsTotalSquareFeet(property));

        Optional<TuCasitaApiException> exception=exceptions.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.district.NoSuchDistrictNameException")
                        &&e.getMessage().equals(property.getDistrictName() +" no ha sido encontrado.")
        ).findFirst();
        Assertions.assertNotEquals(Optional.empty(),exception);
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
    @Test
    void getEnvironmentsTotalSquareFeetFailInvalidLand(){
        //Arrange
        PropertyDTO property = TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        double diff=-475.0;
        Optional<District> district = Optional.ofNullable(TestUtilsGenerator.getValidsDistricts().get(0));
        property.setDistrictName(district.get().getName());

        when( districtRepository.findByName(property.getDistrictName())).thenReturn( district );

        //Assert
        TuCasitaApiListException exception = Assertions.assertThrows(TuCasitaApiListException.class,
                ()-> propertyService.getEnvironmentsTotalSquareFeet(property));
        Assertions.assertNotEquals(Optional.empty(),exception.getExceptionList().stream().filter(
                e->e.getClass().getCanonicalName().equals("com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException")
                        &&e.getMessage().equals("La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0.")
                ).findFirst()
        );
        verify(districtRepository, atLeast(1)).findByName(property.getDistrictName());
    }
}
