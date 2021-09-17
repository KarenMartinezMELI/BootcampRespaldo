package com.meli.tucasita.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.dto.ErrorDTO;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.util.Parse;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
public class DistrictControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @Autowired
    IDistrictRepository districtRepository;

    @BeforeAll
    static void settings() {
        mapper = new ObjectMapper();
    }

    @BeforeEach
    void setUp(){
        TestUtilsGenerator.loadDefaultDataInJson();
        districtRepository.reloadData();
    }

    @Test
    public void isValidFailValidations() throws Exception{
        excecuteValidations("/districts/isValid",HttpMethod.POST);
    }

    @Test
    public void isValidCorrect() throws Exception {
        DistrictDTO payLoadDTO= TestUtilsGenerator.getValidDistrict();

        String responseEntity="El distrito es válido.";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/districts/isValid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseEntity,result.getResponse().getContentAsString());
    }

    @Test
    public void getAllDistrictsCorrect() throws Exception {
        List<DistrictDTO> responseEntity=TestUtilsGenerator.getValidsDistricts().stream().map(d->Parse.parseDistrictToDTO(d)).collect(Collectors.toList());

        String payLoadJson;

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/districts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String responseString=result.getResponse().getContentAsString();
        boolean hasAll=true;
        for(DistrictDTO r:responseEntity){
            payLoadJson=mapper.writeValueAsString(r);
            if(!responseString.contains(payLoadJson)){
                hasAll=false;
            }
        }

        Assertions.assertEquals(true,hasAll);
    }

    @Test
    public void getDistrictsByNameCorrect() throws Exception {
        DistrictDTO responseEntity=Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(0));

        String reponseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/districts/"+responseEntity.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(reponseJson,result.getResponse().getContentAsString());
    }

    @Test
    public void getDistrictsByNameDoesNotExist() throws Exception {
        ErrorDTO responseEntity=new ErrorDTO(
                "com.meli.tucasita.exception.district.NoSuchDistrictNameException",
                "BarrioInexistente no ha sido encontrado.");

        String responseJson = mapper.writeValueAsString(responseEntity);

        this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/districts/"+"BarrioInexistente"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }

    @Test
    public void addDistrictFailValidations() throws Exception{
        excecuteValidations("/districts",HttpMethod.POST);
    }

    @Test
    public void addDistrictSuccess() throws Exception{
        DistrictDTO payLoadDTO=Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(1));
        payLoadDTO.setName("DistritoNuevo");

        DistrictDTO responseEntity=Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(1));
        responseEntity.setName("DistritoNuevo");

        List<District> districts = TestUtilsGenerator.getValidsDistricts();
        districts.add(Parse.parseDistrictDTOToDistrict(responseEntity));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/districts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<District> list= (List<District>) ReflectionTestUtils.getField(districtRepository, "districts");
        boolean found=true;
        for(District d:districts){
            if(!list.stream().filter(di->di.equals(d)).findFirst().isPresent()){
                found=false;
                break;
            }
        }

        Assertions.assertTrue(found);

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
        Assertions.assertTrue(found);
    }

    @Test
    public void addDistrictFailAlreadyExists() throws Exception{
        DistrictDTO payLoadDTO= Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(1));

        DistrictDTO responseEntity=Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(1));

        ErrorDTO error= new ErrorDTO(
                "com.meli.tucasita.exception.district.DistrictWNameAlreadyExistsException",
                responseEntity.getName()+" ya existe.");


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(error);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(HttpMethod.POST,"/districts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }

    @Test
    public void deleteDistrictByNameSuccess() throws Exception{
        List<District> districts= TestUtilsGenerator.getValidsDistricts();
        District aDistrict =districts.get(0);
        districts.remove(0);

        System.out.println(aDistrict);
        this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.DELETE,"/districts/{name}",aDistrict.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<District> list= (List<District>) ReflectionTestUtils.getField(districtRepository, "districts");
        boolean found=true;
        for(District d:districts){
            if(!list.stream().filter(di->di.equals(d)).findFirst().isPresent()){
                found=false;
                break;
            }
        }

        Assertions.assertTrue(found);
    }

    @Test
    public void deleteDistrictByNameFailNotExists() throws Exception{
        DistrictDTO responseEntity=Parse.parseDistrictToDTO(TestUtilsGenerator.getValidsDistricts().get(1));
        responseEntity.setName("BarrioInexistente");

        ErrorDTO error= new ErrorDTO(
                "com.meli.tucasita.exception.district.NoSuchDistrictNameException",
                responseEntity.getName()+" no ha sido encontrado.");

        String responseJson = mapper.writeValueAsString(error);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(HttpMethod.DELETE,"/districts/{name}",responseEntity.getName())
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }

    private void excecuteValidations(String url,HttpMethod method) throws Exception{
        invalidDtoByDistrictNameLength(url,method);
        invalidDtoByDistrictNameBlank(url,method);
        invalidDtoByDistrictBuiltPriceNull(url,method);
        invalidDtoByDistrictBuiltPriceMax(url,method);
        invalidDtoByDistrictBuiltPriceMin(url,method);
        invalidDtoByDistrictUnbuiltPriceNull(url,method);
        invalidDtoByDistrictUnbuiltPriceMax(url,method);
        invalidDtoByDistrictUnbuiltPriceMin(url,method);
        invalidDtoByMalformedMessage(url, method);
    }

    private void invalidDtoByDistrictNameLength(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setName("12caracteres12caracteres12caracteres12caracteres");

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "La longitud del nombre del barrio no puede superar los 45 caracteres."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictNameBlank(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setName(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El nombre del barrio no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictBuiltPriceNull(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setBuiltPrice(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio del metro cuadrado construido de un barrio no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictBuiltPriceMax(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setBuiltPrice(4000.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio máximo permitido por metro cuadrado construido no puede superar los 4000.0 U$S."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictBuiltPriceMin(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setBuiltPrice(-0.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio mínimo permitido por metro cuadrado construido no puede ser menor a 0.0 U$S."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictUnbuiltPriceNull(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setUnbuiltPrice(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio del metro cuadrado no construido de un barrio no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictUnbuiltPriceMax(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setUnbuiltPrice(1000.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio máximo permitido por metro cuadrado no construido no puede superar los 1000.0 U$S."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }
    private void invalidDtoByDistrictUnbuiltPriceMin(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setUnbuiltPrice(-0.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El precio mínimo permitido por metro cuadrado no construido no puede ser menor a 0.0 U$S."));

        String payLoadJson = mapper.writeValueAsString(property);
        String responseJson = mapper.writeValueAsString(errors);

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson));
    }

    private void invalidDtoByMalformedMessage(String url,HttpMethod method) throws Exception {
        //Arrange
        DistrictDTO property = TestUtilsGenerator.getValidDistrict();
        property.setName("BarrioNoExistente");

        String payLoadJson = mapper.writeValueAsString(property).replace("}","]");

         this.mockMvc
                .perform(
                        MockMvcRequestBuilders.request(method,url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.code").value("org.springframework.http.converter.HttpMessageNotReadableException"));
    }

}
