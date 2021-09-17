package com.example.CalculadoraMetrosCuadrados.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HouseDTO {
  @NotBlank(message = "El nombre de la casa no puede estar vacío.")
  @Pattern(regexp="([A-Z])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la casa debe comenzar con mayúscula.")
  @Size(max = 50, message = "La longitud del nombre de la casa no puede superar los 50 caracteres.")
  private String name;

  @Email(message = "El mail del dueño no es valido.")
  private String ownerEmail;

  @NotBlank(message = "La dirección de la casa no puede estar vacío.")
  @Size(max = 255, message = "La longitud de la dirección de la casa no puede superar los 255 caracteres.")
  private String address;

  @NotEmpty(message = "La lista de habitaciones no puede estar vacía.")
  private List<RoomDTO> rooms;

  @NotBlank(message = "El nombre del barrio no puede estar vacío.")
  @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del barrio debe comenzar con mayúscula.")
  @Size(max = 150, message = "La longitud del nombre del barrio no puede superar los 150 caracteres.")
  private String barrio;

}
