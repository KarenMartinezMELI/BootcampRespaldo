package com.meli.obtenerdiploma.unit.service;

import com.meli.obtenerdiploma.exception.StudentNotFoundException;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.IStudentDAO;
import com.meli.obtenerdiploma.repository.IStudentRepository;
import com.meli.obtenerdiploma.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;


public class StudentServiceTest {

    private final IStudentDAO studentDAO =
            Mockito.mock(IStudentDAO.class);
    private final IStudentRepository studentRepository =
            Mockito.mock(IStudentRepository.class);

    private final StudentService studentService =
            new StudentService(studentDAO, studentRepository);


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
    public void createStudent() {
        //Arrange

        StudentDTO entryStudent = new ArrayList<>(getStudents()).get(1);
        //Act
        studentService.create(entryStudent);

        //Assert
        verify(studentDAO, atLeast(1)).save(entryStudent);
    }

    @Test
    public void readStudentByIdExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(1L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenReturn( entryStudent );

        //Act
        StudentDTO responseStudent=  studentService.read(1L);

        //Arrange
        verify(studentDAO, atLeast(1)).findById(entryStudent.getId());
        Assertions.assertEquals(entryStudent,responseStudent);
    }

    @Test
    public void readStudentByIdDoesntExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenThrow(StudentNotFoundException.class);

        //Act

        //Assert
        Assertions.assertThrows(StudentNotFoundException.class, ()-> studentService.read(entryStudent.getId()));
        verify(studentDAO,atLeast(1)).findById(entryStudent.getId());
    }

    @Test
    public void updateStudentExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenReturn(entryStudent);

        //Act
        studentService.update(entryStudent);

        //Assert
        verify(studentDAO, atLeast(1)).save(entryStudent);
    }

    @Test
    public void updateStudentDoesntExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenThrow(StudentNotFoundException.class);

        //Act
        studentService.update(entryStudent);

        //Assert
        verify(studentDAO, atLeast(1)).save(entryStudent);
    }

    @Test
    public void deleteStudentByIdExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);

        //Act
        studentService.delete(entryStudent.getId());

        //Assert
        verify(studentDAO, atLeast(1)).delete(entryStudent.getId());
    }

    @Test
    public void deleteStudentByIdDoesntExists() {
        //Arrange
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        StudentDTO entryStudent = new StudentDTO(3L,"Manolo",null,null,subjects1);
        when( studentDAO.findById(entryStudent.getId())).thenThrow(StudentNotFoundException.class);

        //Act
        studentService.delete(entryStudent.getId());

        //Assert
        verify(studentDAO, atLeast(1)).delete(entryStudent.getId());
    }

    @Test
    public void getAllStudentsWithElements() {
        //Arrange
        when( studentRepository.findAll()).thenReturn(getStudents());

        //Act
        Set<StudentDTO> returnList = studentService.getAll();

        //Assert
        verify(studentRepository, atLeast(1)).findAll();
        Assertions.assertArrayEquals(new Set[]{getStudents()}, new Set[]{returnList});
    }

    @Test
    public void getAllStudentsEmpty() {
        //Arrange
        when( studentRepository.findAll()).thenReturn(new HashSet<>());

        //Act
        Set<StudentDTO> returnList = studentService.getAll();

        //Assert
        verify(studentRepository, atLeast(1)).findAll();
        Assertions.assertArrayEquals(new Set[]{new HashSet<>()}, new Set[]{returnList});
    }


}
