package com.meli.obtenerdiploma.unit.repository;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import com.meli.obtenerdiploma.repository.StudentDAO;
import com.meli.obtenerdiploma.repository.StudentRepository;
import com.meli.obtenerdiploma.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;


public class StudentRepositoryTest {

    private IStudentRepository studentRepository;

    @BeforeEach
    @AfterEach
    void setStudentRepository(){
        this.studentRepository = new StudentRepository();
        TestUtilsGenerator.emptyUsersFile();

    }


    @Test
    void findAllTestReturn2Entities(){
        //Arrange
        ReflectionTestUtils.setField(studentRepository,"SCOPE","test");
        Set<StudentDTO> students = TestUtilsGenerator.getStudentsW3Subjects("Juan","Pedro");
        students.forEach(s->TestUtilsGenerator.appendNewStudent(s));
        //Act
        Set<StudentDTO> SetStudentDTOResponse =studentRepository.findAll();

        //Assert
        Assertions.assertArrayEquals(new Set[]{students}, new Set[]{SetStudentDTOResponse});

    }

    @Test
    void findAllTestFileNotFound(){
        studentRepository=new StudentRepository();
        ReflectionTestUtils.setField(studentRepository,"SCOPE","testdoesntexist");
        //Arrange
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

        //Act
        Set<StudentDTO> SetStudentDTOResponse =studentRepository.findAll();

        //Assert
        Assertions.assertEquals(SetStudentDTOResponse,new HashSet<>() );

    }

}
