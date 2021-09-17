package com.meli.tucasita.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.tucasita.dto.*;
import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.dto.environment.EnvironmentDTO;
import com.meli.tucasita.dto.environment.EnvironmentResponseSquareFeetDTO;
import com.meli.tucasita.dto.property.*;
import com.meli.tucasita.model.District;
import com.meli.tucasita.repository.IDistrictRepository;
import com.meli.tucasita.service.IPropertyService;
import com.meli.tucasita.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PropertyControllerIntegrationTest {

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
    public void calculateTotalSquareFeetFailValidations() throws Exception {
        executeAllValidations("/properties/totalSquareFeet",HttpMethod.POST);
    }

    @Test
    public void calculateTotalSquareFeetSuccess() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        double value= payLoadDTO.getLength()*payLoadDTO.getWidth();

        PropertyResponseSquareFeetDTO responseEntity=new PropertyResponseSquareFeetDTO(payLoadDTO.getName(),value);
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/totalSquareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }
    @Test
    public void calculateTotalSquareFeetFailInvalidLand() throws Exception {
        invalidDtoByInvalidLand("/properties/totalSquareFeet",HttpMethod.POST);
    }
    @Test
    public void calculateTotalSquareFeetFailInvalidDistrict() throws Exception {
        invalidDtoByDistrictNonExistent("/properties/totalSquareFeet",HttpMethod.POST);
    }

    @Test
    public void calculateTotalSquareFeetUnbuiltFailValidations() throws Exception {
        executeAllValidations("/properties/totalSquareFeetUnbuilt",HttpMethod.POST);
    }

    @Test
    public void calculateTotalSquareFeetUnbuiltSuccess() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        double value= payLoadDTO.getLength()*payLoadDTO.getWidth();
        value-=payLoadDTO.getEnvironments().get(0).getLength()*payLoadDTO.getEnvironments().get(0).getWidth();
        value-=payLoadDTO.getEnvironments().get(1).getLength()*payLoadDTO.getEnvironments().get(1).getWidth();

        PropertyResponseSquareFeetUnbuiltDTO responseEntity=new PropertyResponseSquareFeetUnbuiltDTO(payLoadDTO.getName(),value);
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/totalSquareFeetUnbuilt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }
    @Test
    public void calculateTotalSquareFeetUnbuiltFailInvalidLand() throws Exception {
        invalidDtoByInvalidLand("/properties/totalSquareFeetUnbuilt",HttpMethod.POST);
    }
    @Test
    public void calculateTotalSquareFeetUnbuiltFailInvalidDistrict() throws Exception {
        invalidDtoByDistrictNonExistent("/properties/totalSquareFeetUnbuilt",HttpMethod.POST);
    }

    @Test
    public void calculateTotalValueFailValidations() throws Exception {
        executeAllValidations("/properties/totalValue",HttpMethod.POST);
    }

    @Test
    public void  calculateTotalValueSuccess() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        double totalValue= payLoadDTO.getLength()*payLoadDTO.getWidth();
        double buildValue=payLoadDTO.getEnvironments().get(0).getLength()*payLoadDTO.getEnvironments().get(0).getWidth();
        buildValue+=payLoadDTO.getEnvironments().get(1).getLength()*payLoadDTO.getEnvironments().get(1).getWidth();
        double unbuildValue=totalValue-buildValue;
        District district =TestUtilsGenerator.getValidsDistricts().get(0);

        buildValue*=district.getBuiltPrice();
        unbuildValue*=district.getUnbuiltPrice();

        totalValue=buildValue+unbuildValue;

        PropertyResponseTotalValueDTO responseEntity=
                new PropertyResponseTotalValueDTO(
                        payLoadDTO.getName(),
                        payLoadDTO.getWidth(),
                        payLoadDTO.getLength(),
                        buildValue,
                        unbuildValue,
                        totalValue,
                        new DistrictDTO(district.getName(),
                                district.getBuiltPrice(),
                                district.getUnbuiltPrice())
                        );
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/totalValue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }
    @Test
    public void  calculateTotalValueFailInvalidLand() throws Exception {
        invalidDtoByInvalidLand("/properties/totalValue",HttpMethod.POST);
    }
    @Test
    public void  calculateTotalValueFailInvalidDistrict() throws Exception {
        invalidDtoByDistrictNonExistent("/properties/totalValue",HttpMethod.POST);
    }

    @Test
    public void calculateBiggestEnvironmentFailValidations() throws Exception {
        executeAllValidations("/properties/biggestEnvironment",HttpMethod.POST);
    }

    @Test
    public void calculateBiggestEnvironmentSuccessSecondBigger() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(0).setLength(20.0);

        double size1=payLoadDTO.getEnvironments().get(0).getLength()*payLoadDTO.getEnvironments().get(0).getWidth();
        double size2=payLoadDTO.getEnvironments().get(1).getLength()*payLoadDTO.getEnvironments().get(1).getWidth();

        EnvironmentDTO responseEntity=payLoadDTO.getEnvironments().get(1);
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/biggestEnvironment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
        Assertions.assertTrue(size2>size1);
    }

    @Test
    public void calculateBiggestEnvironmentSuccessFirstBigger() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setLength(25.0);
        payLoadDTO.getEnvironments().get(1).setWidth(25.0);

        double size1=payLoadDTO.getEnvironments().get(0).getLength()*payLoadDTO.getEnvironments().get(0).getWidth();
        double size2=payLoadDTO.getEnvironments().get(1).getLength()*payLoadDTO.getEnvironments().get(1).getWidth();

        EnvironmentDTO responseEntity=payLoadDTO.getEnvironments().get(0);
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/biggestEnvironment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
        Assertions.assertTrue(size1>size2);
    }
    @Test
    public void calculateBiggestEnvironmentInvalidLand() throws Exception {
        invalidDtoByInvalidLand("/properties/biggestEnvironment",HttpMethod.POST);
    }
    @Test
    public void calculateBiggestEnvironmentInvalidDistrict() throws Exception {
        invalidDtoByDistrictNonExistent("/properties/biggestEnvironment",HttpMethod.POST);
    }

    @Test
    public void calculateEnvironmentsTotalSquareFeetFailValidations() throws Exception {
        executeAllValidations("/properties/environments/totalSquareFeet",HttpMethod.POST);
    }

    @Test
    public void calculateEnvironmentsTotalSquareFeetSuccess() throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setLength(25.0);
        payLoadDTO.getEnvironments().get(1).setWidth(25.0);
        payLoadDTO.getEnvironments().get(0).setLength(20.0);
        payLoadDTO.getEnvironments().get(0).setWidth(25.0);

        double size1=payLoadDTO.getEnvironments().get(0).getLength()*payLoadDTO.getEnvironments().get(0).getWidth();
        double size2=payLoadDTO.getEnvironments().get(1).getLength()*payLoadDTO.getEnvironments().get(1).getWidth();

        PropertyResponseEnvironmentsSquareFeetDTO responseEntity= new PropertyResponseEnvironmentsSquareFeetDTO(
                payLoadDTO.getName(),
                new ArrayList<>(
                        Arrays.asList(new EnvironmentResponseSquareFeetDTO(
                                        payLoadDTO.getEnvironments().get(0).getName(),
                                        size1),
                                new EnvironmentResponseSquareFeetDTO(
                                        payLoadDTO.getEnvironments().get(1).getName(),
                                        size2))
                ));
        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/properties/environments/totalSquareFeet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }
    @Test
    public void calculateEnvironmentsTotalSquareFeetFailInvalidLand() throws Exception {
        invalidDtoByInvalidLand("/properties/environments/totalSquareFeet",HttpMethod.POST);
    }
    @Test
    public void calculateEnvironmentsTotalSquareFeetFailInvalidDistrict() throws Exception {
        invalidDtoByDistrictNonExistent("/properties/environments/totalSquareFeet",HttpMethod.POST);
    }


    private void executeAllValidations(String url, HttpMethod method) throws Exception{
        invalidDtoByNameUpper(url,method);
        invalidDtoByNameBlank(url,method);
        invalidDtoByNameLength(url,method);
        invalidDtoByWidthNull(url,method);
        invalidDtoByWidthLength(url,method);
        invalidDtoByWidthZero(url,method);
        invalidDtoByLengthNull(url,method);
        invalidDtoByLengthLength(url,method);
        invalidDtoByDistrictNameBlank(url,method);
        invalidDtoByDistrictNameLength(url,method);
        invalidDtoByLengthZero(url,method);
        invalidDtoByEnvironmentEmptyList(url,method);
        invalidDtoByMalformedMessage(url,method);

        invalidDtoByEnvironmentNameUpper(url,method);
        invalidDtoByEnvironmentNameEmpty(url,method);
        invalidDtoByEnvironmentNameLength(url,method);
        invalidDtoByEnvironmentWidthMax(url,method);
        invalidDtoByEnvironmentLengthMax(url,method);
        invalidDtoByEnvironmentWidthMin(url,method);
        invalidDtoByEnvironmentLengthMin(url,method);
        invalidDtoByEnvironmentWidthNull(url,method);
        invalidDtoByEnvironmentLengthNull(url,method);
    }
    private void invalidDtoByNameUpper(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setName(payLoadDTO.getName().toLowerCase());

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El nombre de la propiedad debe comenzar con mayúscula."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByNameBlank(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setName(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El nombre de la propiedad no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByNameLength(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setName("12caracteres12caracteres12caracteres");

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "La longitud del nombre no puede superar los 30 caracteres."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByLengthZero(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setLength(0.0);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El mínimo largo permitido por terreno es de 0.1 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByWidthNull(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setWidth(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El ancho del terreno no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByWidthLength(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setWidth(75.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El máximo ancho permitido por terreno es de 75.0 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByWidthZero(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setWidth(0.0);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El mínimo ancho permitido por terreno es de 0.1 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByLengthNull(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setLength(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El largo del terreno no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByLengthLength(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setLength(100.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El máximo largo permitido por terreno es de 100.0 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByDistrictNameBlank(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setDistrictName(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El barrio no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByDistrictNameLength(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setDistrictName("12caracteres12caracteres12caracteres12caracteres");

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "La longitud del barrio no puede superar los 45 caracteres."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentEmptyList(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setEnvironments(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "La lista de ambientes no puede ser vacía."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentNameUpper(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setName(payLoadDTO.getEnvironments().get(1).getName().toLowerCase());

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El nombre del ambiente debe comenzar con mayúscula."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentNameEmpty(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setName(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El nombre del ambiente no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentNameLength(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setName("12caracteres12caracteres12caracteres");

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "La longitud del nombre no puede superar los 30 caracteres."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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

    private void invalidDtoByEnvironmentWidthMax(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setWidth(25.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El máximo ancho permitido por propiedad es de 25.0 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentLengthMax(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setLength(33.1);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El máximo largo permitido por propiedad es de 33.0 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentWidthMin(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setWidth(0.0);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El mínimo ancho permitido por propiedad es de 0.1 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentLengthMin(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setLength(0.0);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El mínimo largo permitido por propiedad es de 0.1 mts."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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

    private void invalidDtoByEnvironmentWidthNull(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setWidth(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El ancho del ambiente no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByEnvironmentLengthNull(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.getEnvironments().get(1).setLength(null);

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "org.springframework.validation.beanvalidation.SpringValidatorAdapter.ViolationFieldError",
                "El largo del ambiente no puede estar vacío."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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

    private void invalidDtoByDistrictNonExistent(String url,HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getValidProperty();
        payLoadDTO.setDistrictName("BarrioInexistente");

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "com.meli.tucasita.exception.district.NoSuchDistrictNameException",
                payLoadDTO.getDistrictName()+" no ha sido encontrado."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
    private void invalidDtoByInvalidLand(String url, HttpMethod method) throws Exception {
        PropertyDTO payLoadDTO= TestUtilsGenerator.getInvalidPropertyWEnvironmentsSizesSettings();
        double diff=-475.0;

        List<ErrorDTO> errors= new ArrayList<>();
        errors.add(new ErrorDTO(
                "com.meli.tucasita.exception.property.EnviromentsBiggerThanPropertyException",
                "La propiedad tiene una diferencia de "+diff+" en relación a sus ambientes. Debería ser mayor o igual a 0."));

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
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
        PropertyDTO property = TestUtilsGenerator.getValidProperty();

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
