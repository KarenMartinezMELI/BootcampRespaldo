package meli.lottery.repository;



import meli.lottery.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT * FROM students WHERE active >0",nativeQuery = true)
    List<Student> findAllActive();

    @Query(value = "SELECT * FROM students WHERE active >0 AND id=?1",nativeQuery = true)
    Optional<Student> findByIdActive(Long id);




}
