package com.meli.obtenerdiploma.unit.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class TestUtilsGenerator {

    private static String SCOPE;
    private static ObjectWriter mapper;

    public static void emptyUsersFile() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            SCOPE = properties.getProperty("api.scope");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/users.json"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        writer.print("[]");
        writer.close();
    }

    public static Set<StudentDTO> getStudentsW3Subjects(String name1, String name2){
        Set<StudentDTO> students = new HashSet<>();
        List<SubjectDTO> subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",9.0));
        subjects1.add(new SubjectDTO("Física",7.0));
        subjects1.add(new SubjectDTO("Química",6.0));

        students.add(new StudentDTO((long) 1,name1,null,null,subjects1));

        subjects1= new ArrayList<>();
        subjects1.add(new SubjectDTO("Matemática",10.0));
        subjects1.add(new SubjectDTO("Física",8.0));
        subjects1.add(new SubjectDTO("Química",4.0));

        students.add(new StudentDTO((long) 2,name2,null,null,subjects1));

        return students;
    }

    public static StudentDTO getStudentWith3Subjects(String name) {
        SubjectDTO subject1 = new SubjectDTO("Matematica", 8.0);
        SubjectDTO subject2 = new SubjectDTO("Lengua", 6.0);
        SubjectDTO subject3 = new SubjectDTO("Fisica", 4.0);

        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        StudentDTO stu = new StudentDTO();
        stu.setId(1L);
        stu.setStudentName(name);
        stu.setSubjects(subjects);

        return stu;
    }

    public static StudentDTO getStudentWith3SubjectsAverageOver9(String name) {
        SubjectDTO subject1 = new SubjectDTO("Matematica", 8.5);
        SubjectDTO subject2 = new SubjectDTO("Lengua", 9.0);
        SubjectDTO subject3 = new SubjectDTO("Fisica", 10.0);

        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        StudentDTO stu = new StudentDTO();
        stu.setId(1L);
        stu.setStudentName(name);
        stu.setSubjects(subjects);

        return stu;
    }

    public static StudentDTO getStudentWith3SubjectsAverageLessThan9(String name) {
        SubjectDTO subject1 = new SubjectDTO("Matematica", 5.0);
        SubjectDTO subject2 = new SubjectDTO("Lengua", 9.0);
        SubjectDTO subject3 = new SubjectDTO("Fisica", 7.0);

        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        StudentDTO stu = new StudentDTO();
        stu.setId(1L);
        stu.setStudentName(name);
        stu.setSubjects(subjects);

        return stu;
    }

    public static StudentDTO getStudentWithId(Long id) {
        SubjectDTO subject1 = new SubjectDTO("Matemática", 8.0);
        SubjectDTO subject2 = new SubjectDTO("Lengua", 6.0);
        SubjectDTO subject3 = new SubjectDTO("Física", 4.0);

        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(subject1);
        subjects.add(subject2);
        subjects.add(subject3);

        StudentDTO stu = new StudentDTO();
        stu.setId(id);
        stu.setStudentName("student1");
        stu.setSubjects(subjects);

        return stu;
    }

    public static Set<StudentDTO> getStudentSet() {
        StudentDTO stu1 = getStudentWith3Subjects("Marco");
        StudentDTO stu2 = getStudentWith3Subjects("Marco Polo");
        StudentDTO stu3 = getStudentWith3Subjects("Julio");
        StudentDTO stu4 = getStudentWith3Subjects("Julio Cesar");

        return new HashSet<StudentDTO>(){{add(stu1); add(stu2); add(stu3); add(stu4);}};
    }

    public static void appendNewStudent(StudentDTO stu) {
        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();

        PrintWriter writer = null;

        try {
            String content = Files.readString(new File("./src/" + SCOPE + "/resources/users.json").getAbsoluteFile().toPath(), StandardCharsets.UTF_8);
            writer = new PrintWriter(ResourceUtils.getFile("./src/" + SCOPE + "/resources/users.json"));

            try {
                String studentAsString = mapper.writeValueAsString(stu);
                writer.print(content.substring(0, content.length()-1));
                if (content.length()>2) writer.print(", ");
                writer.print(studentAsString);
                writer.print("]");
            } catch (JsonProcessingException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.close();
    }

}
