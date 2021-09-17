package dom.ej.clase23.dto;

import java.util.List;

public class StudentDTO {
    String name;
    List<SubjectDTO> subjects;

    public String getName() {
        return name;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
