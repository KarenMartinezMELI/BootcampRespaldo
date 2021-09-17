package meli.lottery.dto.student;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
public class StudentRequestDTO extends StudentDTO {

}
