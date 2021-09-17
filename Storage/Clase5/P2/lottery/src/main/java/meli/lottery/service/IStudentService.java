package meli.lottery.service;



import meli.lottery.dto.student.StudentRequestDTO;
import meli.lottery.dto.student.StudentResponseDTO;

import java.util.List;

public interface IStudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO getStudentById(Long studentId);

    StudentResponseDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO);

    void deleteStudent(Long studentId);
}
