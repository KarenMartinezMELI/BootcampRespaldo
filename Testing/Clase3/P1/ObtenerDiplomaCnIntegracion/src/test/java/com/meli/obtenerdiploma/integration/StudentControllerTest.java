package com.meli.obtenerdiploma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @Autowired
    IStudentDAO studentDAO;

    @BeforeAll
    static void settings() {
        mapper = new ObjectMapper();
    }

    @BeforeEach
    @AfterEach
    void beforeEach() {

        TestUtilsGenerator.emptyUsersFile();
        studentDAO.reset();
    }

    @Test
    public void registerStudentSuccess() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
         Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/student/registerStudent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseEntity,result.getResponse().getContentAsString());
    }

    @Test
    public void registerStudentEmptyStudentName() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Materia",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("El nombre del estudiante no puede estar vacío."))
                .andReturn();

    }

    @Test
    public void registerStudentStudentNameMoreThan50Char() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("MariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMariaMaria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("La longitud del nombre del estudiante no puede superar los 50 caracteres."))
                .andReturn();
    }

    @Test
    public void registerStudentStudentNameNotWithMayus() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("El nombre del estudiante debe comenzar con mayúscula."))
                .andReturn();
    }

    @Test
    public void registerStudentNoSubjects() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>()
        );

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("La lista de materias no puede estar vacía."))
                .andReturn();
    }

    @Test
    public void registerAtLeast1SubjectScoreBelow0()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",-1.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("La nota mínima de la materia es de 0 pts."))
                .andReturn();
    }

    @Test
    public void registerAtLeast1SubjectScoreOver10()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",20.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("La nota máxima de la materia es de 10 pts."))
                .andReturn();
    }

    @Test
    public void registerAtLeast1SubjectNameNotWithMayus()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("El nombre de la materia debe comenzar con mayúscula."))
                .andReturn();
    }

    @Test
    public void registerStudentAtLeast1SubjectNameWithMoreThan30Char()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("MatematicasMatematicasMatematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("La longitud del nombre de la materia no puede superar los 30 caracteres."))
                .andReturn();
    }

    @Test
    public void registerStudentAtLeast1SubjectWithBlankName() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO(null,5.0),new SubjectDTO("Fisica",5.0))
        ));

        String responseEntity="";

        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        //String responseJson = writer.writeValueAsString(responseEntity);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("MethodArgumentNotValidException"))
                .andExpect(jsonPath("$.description")
                        .value("El nombre de la materia no puede estar vacío."))
                .andReturn();
    }

    @Test
    public void getStudentExistingId() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L); //varia segun el size
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(payLoadDTO);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/getStudent/{id}",payLoadDTO.getId())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result2.getResponse().getContentAsString());
    }

    @Test
    public void getStudentNotExistingId()  throws Exception{
        MvcResult result = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/getStudent/{id}",100)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("StudentNotFoundException"))
                .andExpect(jsonPath("$.description")
                        .value("El alumno con Id 100 no se encuetra registrado."))
                .andReturn();
    }

    @Test
    //Es que si no existe lo agrega... Esta bien este test
    public void modifyStudentNotExistingId()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(100L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",10.0),new SubjectDTO("Lengua",10.0))
        ));


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);

        this.mockMvc
                .perform(
                        post("/student/modifyStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("StudentNotFoundException"))
                .andExpect(jsonPath("$.description")
                        .value("El alumno con Id 100 no se encuetra registrado."));
    }

    @Test
    public void modifyStudentExistingId()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",10.0),new SubjectDTO("Fisica",10.0))
        ));

        StudentDTO payLoadDTOModified= new StudentDTO();
        payLoadDTOModified.setId(payLoadDTO.getId());
        payLoadDTOModified.setStudentName("Nano");
        payLoadDTOModified.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",8.0),new SubjectDTO("Lengua",10.0))
        ));


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String payToLoadModifiedJson = mapper.writeValueAsString(payLoadDTOModified);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.mockMvc
                .perform(
                        post("/student/modifyStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payToLoadModifiedJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result3 = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/getStudent/{id}",payLoadDTO.getId())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();


        Assertions.assertEquals("",result.getResponse().getContentAsString());
        Assertions.assertEquals("",result2.getResponse().getContentAsString());
        Assertions.assertEquals(payToLoadModifiedJson,result3.getResponse().getContentAsString());
    }

    @Test
    public void removeStudentNotExistingId()  throws Exception{
        MvcResult result = this.mockMvc
                .perform(
                        post("/student/removeStudent/{id}",100)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("",result.getResponse().getContentAsString());
    }

    @Test
    public void removeStudentExistingId() throws Exception {
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.mockMvc
                .perform(
                        post("/student/removeStudent/{id}",payLoadDTO.getId())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/getStudent/{id}",payLoadDTO.getId())
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("StudentNotFoundException"))
                .andExpect(jsonPath("$.description")
                        .value("El alumno con Id 1 no se encuetra registrado."));

        Assertions.assertEquals("",result.getResponse().getContentAsString());
        Assertions.assertEquals("",result2.getResponse().getContentAsString());
    }


    @Test
    public void return1Students()  throws Exception{
        StudentDTO payLoadDTO= new StudentDTO();
        payLoadDTO.setId(1L);
        payLoadDTO.setStudentName("Maria");
        payLoadDTO.setSubjects(new ArrayList<>(
                Arrays.asList(new SubjectDTO("Matematicas",5.0),new SubjectDTO("Fisica",5.0))
        ));


        String payLoadJson = mapper.writeValueAsString(payLoadDTO);
        String responseJson = mapper.writeValueAsString(new ArrayList<>(Arrays.asList(payLoadDTO)));

        MvcResult result = this.mockMvc
                .perform(
                        post("/student/registerStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payLoadJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult result2 = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/listStudents")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals("",result.getResponse().getContentAsString());
        Assertions.assertEquals(responseJson,result2.getResponse().getContentAsString());
    }

    @Test
    public void returnVoidStudents()  throws Exception{

        String responseJson = mapper.writeValueAsString(new ArrayList<>());

        MvcResult result = this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/student/listStudents")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }
}
