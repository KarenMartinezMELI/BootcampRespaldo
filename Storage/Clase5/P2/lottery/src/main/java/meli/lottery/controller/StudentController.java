package meli.lottery.controller;


import meli.lottery.dto.student.StudentRequestDTO;
import meli.lottery.dto.student.StudentResponseDTO;
import meli.lottery.service.IStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    IStudentService studentService;

    StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping()
    public StudentResponseDTO createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        return studentService.createStudent(studentRequestDTO);
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable(value = "id") Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable(value = "id") Long studentId, @Valid @RequestBody StudentRequestDTO studentDTO) {
        return studentService.updateStudent(studentId, studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }
}
