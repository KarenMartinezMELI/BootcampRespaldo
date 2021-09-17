package com.meli.obtenerdiploma.unit.service;

import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import com.meli.obtenerdiploma.service.ObtenerDiplomaService;
import com.meli.obtenerdiploma.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class ObtenerDiplomaServiceTest {

    //@MockBean
    private IStudentDAO studentDAO =
            Mockito.mock(IStudentDAO.class);

    //@InjectMocks
    private ObtenerDiplomaService obtenerDiplomaService =
            new ObtenerDiplomaService(studentDAO);

    @Test
    void analyzeScoresExistingStudent() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));
        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenReturn(entryStudent);

        StudentDTO expectedStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);

        expectedStudent.setAverageScore(22.0/expectedStudent.getSubjects().size());

        expectedStudent.setMessage("El alumno " + entryStudent.getStudentName() + " ha obtenido un promedio de 7.33. Puedes mejorar.");

        //Act
        StudentDTO responseDTO=obtenerDiplomaService.analyzeScores(entryStudent.getId());

        //Assert
        verify(studentDAO, atLeast(1)).findById(entryStudent.getId());
        Assertions.assertEquals(responseDTO,expectedStudent);
    }

    @Test
    void analyzeScoresExistingStudentWithGreet() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",10.0));
        subjects1.add(new SubjectDTO("Química",8.0));
        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenReturn(entryStudent);

        StudentDTO expectedStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);

        expectedStudent.setAverageScore(28.0/expectedStudent.getSubjects().size());

        expectedStudent.setMessage("El alumno " + entryStudent.getStudentName() + " ha obtenido un promedio de 9.33"
                +". Felicitaciones!");

        //Act
        StudentDTO responseDTO=obtenerDiplomaService.analyzeScores(entryStudent.getId());

        //Assert
        verify(studentDAO, atLeast(1)).findById(entryStudent.getId());
        Assertions.assertEquals(expectedStudent,responseDTO);
    }

    @Test
    void analyzeScoresNotExistingStudent() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));
        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenThrow(StudentNotFoundException.class);

        StudentDTO expectedStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);

        expectedStudent.setAverageScore(22.0/expectedStudent.getSubjects().size());

        expectedStudent.setMessage("El alumno " + entryStudent.getStudentName() + " ha obtenido un promedio de 7.33"
                +". Felicitaciones!");

        //Act

        //Assert
        Assertions.assertThrows(StudentNotFoundException.class, ()-> obtenerDiplomaService.analyzeScores(entryStudent.getId()) );
    }

    @Test
    void analyzeScoresExistingStudentWithZeroAsAverange() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",0.0));
        subjects1.add(new SubjectDTO("Física",0.0));
        subjects1.add(new SubjectDTO("Química",0.0));
        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenThrow(StudentNotFoundException.class);

        StudentDTO expectedStudent = new StudentDTO(3L,"Manolo",null,0.0,subjects1);

        expectedStudent.setMessage("El alumno " + entryStudent.getStudentName() + " ha obtenido un promedio de 7.33"
                +". Puedes mejorar.");


        //Assert
        Assertions.assertThrows(StudentNotFoundException.class, ()-> obtenerDiplomaService.analyzeScores(entryStudent.getId()) );
    }



}
