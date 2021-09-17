package com.meli.obtenerdiploma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.StudentDAO;
import com.meli.obtenerdiploma.service.IObtenerDiplomaService;
import com.meli.obtenerdiploma.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.*;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ObtenerDiplomaControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private static ObjectMapper mapper;

    @Autowired
    IStudentDAO studentDAO;


    @BeforeAll
    static void start(){
       mapper = new ObjectMapper();

    }
    @BeforeEach
    @AfterEach
    void beforeEach() {
        TestUtilsGenerator.emptyUsersFile();
        studentDAO.reset();
    }

    private void postStudent(StudentDTO st) throws Exception {
        st.setId(1L);
        String payLoadJson = mapper.writeValueAsString(st);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/student/registerStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoadJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void analyzeScoresSuccesOver9() throws Exception {
        postStudent(TestUtilsGenerator.getStudentWith3SubjectsAverageOver9("Juan"));
        StudentDTO responseLoadDTO= TestUtilsGenerator.getStudentWith3SubjectsAverageOver9("Juan");

        double average= responseLoadDTO.getSubjects().stream().mapToDouble(SubjectDTO::getScore).sum();
        average=average/responseLoadDTO.getSubjects().size();
        responseLoadDTO.setAverageScore(average);
        responseLoadDTO.setMessage("El alumno " + responseLoadDTO.getStudentName() + " ha obtenido un promedio de "+9.17
                +". Felicitaciones!");


        String responseJson = mapper.writeValueAsString(responseLoadDTO);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }

    @Test
    @Order(1)
    void analyzeScoresSuccesEqualsOrLess9() throws Exception {
        postStudent(TestUtilsGenerator.getStudentWith3SubjectsAverageLessThan9("Manual"));
        StudentDTO responseLoadDTO=TestUtilsGenerator.getStudentWith3SubjectsAverageLessThan9("Manual");
        double average= responseLoadDTO.getSubjects().stream().mapToDouble(SubjectDTO::getScore).sum();
        average=average/responseLoadDTO.getSubjects().size();
        responseLoadDTO.setAverageScore(average);
        responseLoadDTO.setMessage("El alumno " + responseLoadDTO.getStudentName() + " ha obtenido un promedio de 7"+
                ". Puedes mejorar.");

        String responseJson = mapper.writeValueAsString(responseLoadDTO);

        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertEquals(responseJson,result.getResponse().getContentAsString());
    }

    @Test
    @Order(3)
    void analyzeScoresNotExistingId() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/analyzeScores/{studentId}",2))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name")
                        .value("StudentNotFoundException"))
                .andExpect(jsonPath("$.description")
                        .value("El alumno con Id 2 no se encuetra registrado."));

    }

}
