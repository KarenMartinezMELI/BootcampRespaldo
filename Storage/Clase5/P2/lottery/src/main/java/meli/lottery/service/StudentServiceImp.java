package meli.lottery.service;


import meli.lottery.dto.student.StudentRequestDTO;
import meli.lottery.dto.student.StudentResponseDTO;
import meli.lottery.exception.ResourceNotFoundException;
import meli.lottery.model.Student;
import meli.lottery.repository.IStudentRepository;
import meli.lottery.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements IStudentService {


    IStudentRepository studentRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    StudentServiceImp(IStudentRepository studentRepository,
                      ModelMapper modelMapper,
                      ListMapper listMapper) {
        this.studentRepository = studentRepository;
        this.listMapper = listMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAllActive();
        return listMapper.mapList(students, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        // Create new note
        Student student = modelMapper.map(studentRequestDTO, Student.class);
        student.setActive(1);
        Student studentReq = studentRepository.save(student);
        StudentResponseDTO resp =  modelMapper.map(studentReq, StudentResponseDTO.class);
        return resp;
    }

    @Override
    public StudentResponseDTO getStudentById(Long studentId) {
        Student student = studentRepository.findByIdActive(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        return modelMapper.map(student, StudentResponseDTO.class);
    }

    @Override
    public StudentResponseDTO updateStudent(Long studentId,
                                        StudentRequestDTO studentDTO) {

        Student student = studentRepository.findByIdActive(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));

        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());


        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentResponseDTO.class);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findByIdActive(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        student.setActive(0);
        studentRepository.save(student);
    }
}
