package dom.ej.clase23.service;

import dom.ej.clase23.dto.DiplomaDTO;
import dom.ej.clase23.dto.StudentDTO;

public interface IDiplomaService {
    DiplomaDTO getDiploma(StudentDTO student) throws Exception;

}
