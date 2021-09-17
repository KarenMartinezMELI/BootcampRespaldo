package com.meli.obtenerdiploma.unit.repository;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.StudentDAO;
import com.meli.obtenerdiploma.repository.StudentRepository;
import com.meli.obtenerdiploma.unit.util.TestUtilsGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

public class StudentDAOTest {

    protected IStudentDAO studentDAO;

    @BeforeEach
    @AfterEach
    void setStudentRepository(){
        TestUtilsGenerator.emptyUsersFile();
        this.studentDAO = new StudentDAO();

        ReflectionTestUtils.setField(studentDAO,"students",TestUtilsGenerator.getStudentsW3Subjects("Juan","Pedro"));

    }

    @Test
    void saveTestWithOtherId(){
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

        StudentDTO studentEntryDto=new StudentDTO((long) 3,"Manolo",null,null,subjects1);

        students.add(studentEntryDto);
        //Act
        studentDAO.save(studentEntryDto);

        //Assert
        ReflectionTestUtils.getField(studentDAO,"students");
        Assertions.assertArrayEquals(new Object[]{ReflectionTestUtils.getField(studentDAO, "students")}, new Set[]{students});
    }

    @Test
    void saveTestWithSameId(){
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

        StudentDTO studentChange = students.stream().collect(Collectors.toList()).get(1);
        studentChange.setStudentName("Manolo");
        studentChange.setSubjects(subjects1);

        StudentDTO studentEntryDto=new StudentDTO((long) 1,"Manolo",null,null,subjects1);

        //Act
        studentDAO.save(studentEntryDto);

        //Assert
        ReflectionTestUtils.getField(studentDAO,"students");
        Assertions.assertArrayEquals(new Object[]{ReflectionTestUtils.getField(studentDAO, "students")}, new Set[]{students});
    }

    @Test
    void deleteTestWithExistingEntity(){
        //Arrange

        //Act
        boolean response=studentDAO.delete((long) 1);

        //Assert
        Assertions.assertEquals(true,response);

    }


    @Test
    void deleteNotExistingEntity(){
        //Arrange

        //Act
        boolean response=studentDAO.delete((long)3);

        //Assert
        Assertions.assertEquals(response,false);
    }

    @Test
    void existExistingEntity(){
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        StudentDTO studentEntry=new StudentDTO((long) 1,"Juan",null,null,subjects1);
        //Act
        boolean response=studentDAO.exists(studentEntry);

        //Assert
        Assertions.assertEquals(response,true);
    }

    @Test
    void existNotExistingEntityWithSameName(){
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        StudentDTO studentEntry=new StudentDTO((long) 3,"Juan",null,null,subjects1);
        //Act
        boolean response=studentDAO.exists(studentEntry);

        //Assert
        Assertions.assertEquals(response,false);
    }

    @Test
    void existNotExistingEntityWithDiffName(){
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        StudentDTO studentEntry=new StudentDTO((long) 3,"Manolo",null,null,subjects1);
        //Act
        boolean response=studentDAO.exists(studentEntry);

        //Assert
        Assertions.assertEquals(false,response);
    }

    @Test
    void getByIdExistingEntity(){
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        StudentDTO studentEntryDto=new StudentDTO((long) 1,"Juan",null,null,subjects1);

        //Act
        StudentDTO studentResponseDto=studentDAO.findById(studentEntryDto.getId());

        //Assert
        Assertions.assertEquals(studentEntryDto,studentResponseDto);
    }

    @Test
    void getByIdNotExistingEntity(){
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        StudentDTO studentEntryDto=new StudentDTO((long) 2,"Juan","",0.0,subjects1);

        //Act
        StudentDTO studentResponseDto=studentDAO.findById(studentEntryDto.getId());

        //Assert
        Assertions.assertNotEquals(studentEntryDto,studentResponseDto);
    }
}
