package com.meli.obtenerdiploma.unit.controller;

import com.meli.obtenerdiploma.controller.ObtenerDiplomaController;
import com.meli.obtenerdiploma.controller.StudentController;
import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.service.IObtenerDiplomaService;
import com.meli.obtenerdiploma.service.IStudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;


public class ObtenerDiplomaControllerTest {

    //@MockBean
    private IObtenerDiplomaService obtenerDiplomaService =
            Mockito.mock(IObtenerDiplomaService.class);

    //@InjectMocks
    private ObtenerDiplomaController obtenerDiplomaController =
            new ObtenerDiplomaController(obtenerDiplomaService);

    private Set<StudentDTO> getStudents(){
        Set<StudentDTO> students = new HashSet<>();
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        students.add(new StudentDTO((long) 1,"Juan",null,null,subjects1));

        subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        students.add(new StudentDTO((long) 2,"Pedro",null,null,subjects1));

        return students;
    }

    @Test
    void analyzeScoresExistingId() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        StudentDTO resultCalcStudent=getStudents().stream().collect(Collectors.toList()).get(1);

        resultCalcStudent.setMessage("El alumno " + entryStudent.getStudentName() + " ha obtenido un promedio de 7.33"
                +". Puedes mejorar.");
        resultCalcStudent.setAverageScore(7.33);

        when( obtenerDiplomaService.analyzeScores(entryStudent.getId())).thenReturn( resultCalcStudent );;
        //Acts
        StudentDTO response=obtenerDiplomaController.analyzeScores(entryStudent.getId());

        //Assert
        verify(obtenerDiplomaService, atLeast(1)).analyzeScores(entryStudent.getId());
        Assertions.assertEquals(response,resultCalcStudent);
    }

    @Test
    void analyzeScoresNotExistingId() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        when( obtenerDiplomaService.analyzeScores(entryStudent.getId())).thenThrow(StudentNotFoundException.class);
        //Acts

        //Assert
        Assertions.assertThrows(StudentNotFoundException.class,()->obtenerDiplomaController.analyzeScores(entryStudent.getId()));
        verify(obtenerDiplomaService, atLeast(1)).analyzeScores(entryStudent.getId());
    }

}
