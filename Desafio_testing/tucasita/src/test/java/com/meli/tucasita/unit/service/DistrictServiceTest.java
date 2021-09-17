package com.meli.tucasita.unit.service;

import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.exception.district.DistrictWNameAlreadyExistsException;
import com.meli.tucasita.exception.district.NoSuchDistrictNameException;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.service.DistrictServiceImp;
import com.meli.tucasita.service.IDistrictService;
import com.meli.tucasita.util.Parse;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class DistrictServiceTest {

    private final IDistrictRepository districtRepository =
            Mockito.mock(IDistrictRepository.class);

    private IDistrictService districtService;

    @BeforeEach
    void setUp(){
        this.districtService = new DistrictServiceImp(districtRepository);
    }

    @Test
    void addDistrictSuccess(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        district.setName("NuevoBarrio");

        when( districtRepository.findByName(district.getName())).thenReturn( Optional.empty());

        //Act
        DistrictDTO responseDistrict=  districtService.addDistrict(district);

        //Assert
        verify(districtRepository, atLeast(1)).findByName(district.getName());
        verify(districtRepository, atLeast(1)).add(Parse.parseDistrictDTOToDistrict(district));
        Assertions.assertEquals(district,responseDistrict);
    }
    @Test
    void addDistrictFailDistrictAlreadyExists(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        when( districtRepository.findByName(district.getName())).thenReturn(Optional.ofNullable(Parse.parseDistrictDTOToDistrict(district)) );

        //Assert
        Assertions.assertThrows(DistrictWNameAlreadyExistsException.class, ()-> districtService.addDistrict(district));
        verify(districtRepository, atLeast(1)).findByName(district.getName());
        verify(districtRepository, atLeast(0)).add(Parse.parseDistrictDTOToDistrict(district));
    }

    @Test
    void removeDistrictByNameSuccess(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));

        when( districtRepository.findByName(district.getName())).thenReturn( Optional.ofNullable(Parse.parseDistrictDTOToDistrict(district)));
        //Act
        districtService.removeDistrictByName(district.getName());

        //Assert
        verify(districtRepository, atLeast(1)).findByName(district.getName());
        verify(districtRepository, atLeast(1)).deleteByName(district.getName());
    }

    @Test
    void removeDistrictFailDistrictDoesNotExists(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        when( districtRepository.findByName(district.getName())).thenReturn(Optional.empty() );

        //Assert
        Assertions.assertThrows(NoSuchDistrictNameException.class, ()-> districtService.removeDistrictByName(district.getName()));
        verify(districtRepository, atLeast(1)).findByName(district.getName());
        verify(districtRepository, atLeast(0)).deleteByName(district.getName());
    }

    @Test
    void getDistrictByNameSuccess(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));

        when( districtRepository.findByName(district.getName())).thenReturn(Optional.ofNullable(Parse.parseDistrictDTOToDistrict(district)));

        //Act
        DistrictDTO responseDistrict=  districtService.getDistrictByName(district.getName());

        //Assert
        verify(districtRepository, atLeast(1)).findByName(district.getName());
        Assertions.assertEquals(district,responseDistrict);
    }

    @Test
    void getDistrictByNameFailDistrictDoesNotExists(){
        //Arrange
        DistrictDTO district = Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));
        when( districtRepository.findByName(district.getName())).thenReturn(Optional.empty() );

        //Assert
        Assertions.assertThrows(NoSuchDistrictNameException.class, ()-> districtService.getDistrictByName(district.getName()));
        verify(districtRepository, atLeast(1)).findByName(district.getName());
    }

    @Test
    void getAllDistrictsSuccess(){
        //Arrange
        List<DistrictDTO> districts = TestUtilsGenerator.getValidsDistricts().stream().map(d->Parse.parseDistrictToDTO(d)).collect(Collectors.toList());
        when( districtRepository.getAll()).thenReturn(TestUtilsGenerator.getValidsDistricts());

        //Act
        List<DistrictDTO> responseDistrict=  districtService.getDistricts();

        //Assert
        verify(districtRepository, atLeast(1)).getAll();
        Assertions.assertEquals(districts,responseDistrict);
    }

}
