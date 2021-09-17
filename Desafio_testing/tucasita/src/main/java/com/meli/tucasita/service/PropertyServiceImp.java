package com.meli.tucasita.service;

import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.dto.environment.EnvironmentResponseSquareFeetDTO;
import com.meli.tucasita.dto.property.*;
import com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException;
import com.meli.tucasita.exception.district.NoSuchDistrictNameException;
import com.meli.tucasita.exception.TuCasitaApiException;
import com.meli.tucasita.exception.TuCasitaApiListException;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.util.Parse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Primary
public class PropertyServiceImp implements IPropertyService {

  IDistrictRepository districtRepository;

  //@Autowired
  public PropertyServiceImp(IDistrictRepository districtRepository) {
    this.districtRepository = districtRepository;
  }

  @Override
  public PropertyResponseSquareFeetDTO getTotalSquareFeet(PropertyDTO property) {
    applyCommonValidationsWPropertyDTO(property);
    var response = new PropertyResponseSquareFeetDTO(property.getName(), calculatePropertySquareFeet(property) );

    return response;
  }

  @Override
  public PropertyResponseSquareFeetUnbuiltDTO getTotalSquareFeetUnbuilt(PropertyDTO property) {
    applyCommonValidationsWPropertyDTO(property);
    return new PropertyResponseSquareFeetUnbuiltDTO(property.getName(), calculateTotalSquareFeetUnbuilt(property));
  }

  public double calculateTotalSquareFeetUnbuilt(PropertyDTO property){
    double total=calculatePropertySquareFeet(property);
    double builtTotal=calculateRoomsSquareFeet(property);
    return total-builtTotal;
  }

  @Override
  public EnvironmentDTO getBiggestEnvironment(PropertyDTO property) {
    applyCommonValidationsWPropertyDTO(property);
    return calculateBiggestEnvironment(property);
  }

  @Override
  public PropertyResponseTotalValueDTO getTotalValue(PropertyDTO property) {
    applyCommonValidationsWPropertyDTO(property);
    double total = calculatePropertySquareFeet(property);
    double unbuilt= calculateTotalSquareFeetUnbuilt(property);
    double built=total-unbuilt;

    PropertyResponseTotalValueDTO propResponse= parsePropertyDTOtoResponse(property);
    District district= districtRepository.findByName(property.getDistrictName()).get();
    propResponse.setBuiltPrice(district.getBuiltPrice()*built);
    propResponse.setUnbuiltPrice(district.getUnbuiltPrice()*unbuilt);
    propResponse.setPrice(propResponse.getBuiltPrice()+propResponse.getUnbuiltPrice());
    propResponse.setDistrict(Parse.parseDistrictToDTO(district));
    return propResponse;
  }

  @Override
  public PropertyResponseEnvironmentsSquareFeetDTO getEnvironmentsTotalSquareFeet(PropertyDTO property) {
    applyCommonValidationsWPropertyDTO(property);
    List<EnvironmentResponseSquareFeetDTO> roomsResponse = new ArrayList<>();
    for(EnvironmentDTO r:property.getEnvironments()){
      roomsResponse.add(new EnvironmentResponseSquareFeetDTO(r.getName(),calculateRoomSquareFeet(r)));
    }

    return new PropertyResponseEnvironmentsSquareFeetDTO(property.getName(),roomsResponse);
  }

  private PropertyResponseTotalValueDTO parsePropertyDTOtoResponse(PropertyDTO property){
    PropertyResponseTotalValueDTO prop = new PropertyResponseTotalValueDTO();
    prop.setName(property.getName());
    prop.setLength(property.getLength());
    prop.setWidth(property.getWidth());
    return prop;
  }
  private double calculateRoomSquareFeet(EnvironmentDTO room) {
    double result = 0;
    result = room.getWidth() * room.getLength();
    return result;
  }
  private double calculatePropertySquareFeet(PropertyDTO property){
    return property.getLength()*property.getWidth();
  }

  private double calculateRoomsSquareFeet(PropertyDTO property) {
    double totalSquareFeet = 0;
    for (EnvironmentDTO room : property.getEnvironments()) {
      totalSquareFeet += calculateRoomSquareFeet(room);
    }

    return totalSquareFeet;
  }

  private EnvironmentDTO calculateBiggestEnvironment(PropertyDTO property) {
    EnvironmentDTO biggest = null;
    double maxRoom = 0;
    for (EnvironmentDTO room : property.getEnvironments()) {
      double squareFeet = calculateRoomSquareFeet(room);
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }

    return biggest;
  }

  private void applyCommonValidationsWPropertyDTO(PropertyDTO property){
    List<TuCasitaApiException> errors = new ArrayList<>();
    validateTotalPropertySquareNotLessThanEnvironments(property,errors);
    validateDistrictByName(property.getDistrictName(),errors);
    if(errors.size()>0){
      throw new TuCasitaApiListException(errors);
    }
  }

  private void validateTotalPropertySquareNotLessThanEnvironments(PropertyDTO property, List<TuCasitaApiException> errors){
    double diff= calculatePropertySquareFeet(property)-calculateRoomsSquareFeet(property);
    if(diff<0){
      errors.add(new EnviromentsBiggerThanPropertyException(Double.toString(diff)));
    }
  }

  private void validateDistrictByName(String districtName,List<TuCasitaApiException> errors){
    Optional<District> district =districtRepository.findByName(districtName);
    if(!district.isPresent()){
      errors.add(new NoSuchDistrictNameException(districtName));
    }
  }

}
