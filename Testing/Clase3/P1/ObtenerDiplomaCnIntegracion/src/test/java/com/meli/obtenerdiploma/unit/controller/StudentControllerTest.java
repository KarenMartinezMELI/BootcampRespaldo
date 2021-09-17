package com.meli.obtenerdiploma.unit.controller;

import com.meli.obtenerdiploma.controller.StudentController;
import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import com.meli.obtenerdiploma.service.IStudentService;
import com.meli.obtenerdiploma.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;


public class StudentControllerTest {


    //@MockBean
    private IStudentService studentService =
            Mockito.mock(IStudentService.class);

    //@InjectMocks
    private StudentController studentController =
            new StudentController(studentService);


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
    public void registerStudent() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);

        //Act
        ResponseEntity<?> responseEntity=studentController.registerStudent(entryStudent);

        //Assert
        verify(studentService, atLeast(1)).create(entryStudent);
        Assertions.assertEquals(responseEntity,new ResponseEntity<>(null, HttpStatus.OK));
    }

    @Test
    public void getStudentExists() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        when( studentService.read(entryStudent.getId())).thenReturn(entryStudent);
        //Act
        StudentDTO responseStudent=studentController.getStudent(entryStudent.getId());

        //Assert
        verify(studentService, atLeast(1)).read(entryStudent.getId());
        Assertions.assertEquals(responseStudent,entryStudent);
    }

    @Test
    public void getStudentDoesntExists() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        when( studentService.read(entryStudent.getId())).thenThrow(StudentNotFoundException.class);
        //Act

        //Assert
        Assertions.assertThrows(StudentNotFoundException.class,()->studentController.getStudent(entryStudent.getId()));
        verify(studentService, atLeast(1)).read(entryStudent.getId());
    }

    @Test
    public void modifyExistingStudent() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        when( studentService.read(entryStudent.getId())).thenReturn(entryStudent);
        //Act
        ResponseEntity<?> response=studentController.modifyStudent(entryStudent);

        //Assert
        verify(studentService, atLeast(1)).update(entryStudent);
        Assertions.assertEquals(response,new ResponseEntity(null,HttpStatus.OK));
    }

    @Test
    public void modifyNotExistingStudent() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        //Act
        ResponseEntity<?> response=studentController.modifyStudent(entryStudent);
        //Assert
        verify(studentService, atLeast(1)).update(entryStudent);
        Assertions.assertEquals(response,new ResponseEntity(null,HttpStatus.OK));
    }

    @Test
    public void removeStudent() {
        //Arrange
        StudentDTO entryStudent = getStudents().stream().collect(Collectors.toList()).get(1);
        //Acts
        ResponseEntity<?> response=studentController.removeStudent(entryStudent.getId());
        //Assert
        verify(studentService, atLeast(1)).delete(entryStudent.getId());
        Assertions.assertEquals(response,new ResponseEntity(null,HttpStatus.OK));
    }

    @Test
    public void listStudents() {
        //Arrange
        when( studentService.getAll()).thenReturn(getStudents());
        //Acts
        Set<StudentDTO> response=studentController.listStudents();
        //Assert
        verify(studentService, atLeast(1)).getAll();
        Assertions.assertEquals(response,getStudents());
    }

    @Test
    public void listBlankStudents() {
        //Arrange
        when( studentService.getAll()).thenReturn(new HashSet<>());
        //Acts
        Set<StudentDTO> response=studentController.listStudents();
        //Assert
        verify(studentService, atLeast(1)).getAll();
        Assertions.assertEquals(response,new HashSet<>());
    }


}
