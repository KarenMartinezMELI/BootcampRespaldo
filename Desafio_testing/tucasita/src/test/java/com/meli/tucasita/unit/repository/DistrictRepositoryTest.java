package com.meli.tucasita.unit.repository;

import com.meli.tucasita.exception.FailedToSaveDataException;
import com.meli.tucasita.exception.TuCasitaApiListException;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.DistrictRepositoryImp;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


public class DistrictRepositoryTest {

    protected IDistrictRepository districtRepository;

    @BeforeEach
    void setUp(){
        TestUtilsGenerator.loadDefaultDataInJson();
        this.districtRepository = new DistrictRepositoryImp();
    }

    @Test
    void getAllDistrictsCorrect(){
        //Arrange
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        //Act
        List<District> districtsResult=districtRepository.getAll();
        //Assert

        boolean found=true;
        for(District d:districts){
            if(!districtsResult.stream().filter(di->di.equals(d)).findFirst().isPresent()){
                found=false;
                break;
            }
        }

        Assertions.assertEquals(districts.size(),districtsResult.size());
        Assertions.assertTrue(found);
    }

    @Test
    void getAllDistrictsReturns0InvalidScope(){
        //Arrange
        TestUtilsGenerator.loadDefaultDataInJson();
        ReflectionTestUtils.setField(districtRepository,"SCOPE","ScopeNoExistente");
        districtRepository.reloadData();
        //Act
        List<District> districtsResult=districtRepository.getAll();
        //Assert

        Assertions.assertEquals(0,districtsResult.size());
    }

    @Test
    void getAllDistrictsReturns0InvalidJSON(){
        //Arrange
        TestUtilsGenerator.loadDefaultDataInJson();
        TestUtilsGenerator.generateInvalidJsonFileFormat();
        districtRepository.reloadData();
        //Act
        List<District> districtsResult=districtRepository.getAll();
        //Assert

        Assertions.assertEquals(0,districtsResult.size());
    }

    @Test
    void findByNameCorrect(){
        //Arrange
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        //Act
        Optional<District> aDistrict=districtRepository.findByName(district.getName());
        //Assert
        Assertions.assertNotEquals(Optional.empty(),aDistrict);
        Assertions.assertEquals(district,aDistrict.get());
    }

    @Test
    void findByNameInvalidDistrictDoesNotExists(){
        //Arrange
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        district.setName("BarrioInexistente");
        //Act
        Optional<District> aDistrict=districtRepository.findByName(district.getName());
        //Assert
        Assertions.assertEquals(Optional.empty(),aDistrict);
    }

    @Test
    void deleteByNameCorrect(){
        //Arrange
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        districts.remove(0);
        //Act
        districtRepository.deleteByName(district.getName());
        //Assert
        List<District> districtsResult = (List<District>) ReflectionTestUtils.getField(districtRepository, "districts");
        boolean found=true;
        for(District d:districts){
            if(!districtsResult.stream().filter(di->di.equals(d)).findFirst().isPresent()){
                found=false;
                break;
            }
        }

        Assertions.assertEquals(districts.size(),districtsResult.size());
        Assertions.assertTrue(found);
    }

    @Test
    void deleteByNameFailInvalidScope(){
        //Arrange
        ReflectionTestUtils.setField(districtRepository,"SCOPE","ScopeNoExistente");
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        districts.remove(0);
        //Act

        //Assert
        Assertions.assertThrows(FailedToSaveDataException.class,
                ()-> districtRepository.add(district));
    }

    @Test
    void addDistrictCorrect(){
        //Arrange
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        district.setName("NuevoBarrio");
        districts.add(district);
        //Act
        districtRepository.add(district);
        //Assert
        List<District> districtsResult = (List<District>) ReflectionTestUtils.getField(districtRepository, "districts");
        boolean found=true;
        for(District d:districts){
            if(!districtsResult.stream().filter(di->di.equals(d)).findFirst().isPresent()){
                found=false;
                break;
            }
        }

        Assertions.assertEquals(districts.size(),districtsResult.size());
        Assertions.assertTrue(found);
    }

    @Test
    void addDistrictWithInvalidJSONAddsAnyways(){
        //Arrange
        TestUtilsGenerator.generateInvalidJsonFileFormat();
        districtRepository.reloadData();
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        district.setName("NuevoBarrio");
        districts.add(district);
        //Act
        districtRepository.add(district);
        //Assert
        List<District> districtsResult = (List<District>) ReflectionTestUtils.getField(districtRepository, "districts");

        Assertions.assertEquals(1,districtsResult.size());
        Assertions.assertEquals(district,districtsResult.get(0));
    }

    @Test
    void addDistrictFailInvalidScope(){
        //Arrange
        ReflectionTestUtils.setField(districtRepository,"SCOPE","ScopeNoExistente");
        District district = TestUtilsGenerator.getValidsDistricts().get(0);
        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        district.setName("NuevoBarrio");
        districts.add(district);
        //Act

        //Assert
        Assertions.assertThrows(FailedToSaveDataException.class,
                ()-> districtRepository.add(district));
    }
}
