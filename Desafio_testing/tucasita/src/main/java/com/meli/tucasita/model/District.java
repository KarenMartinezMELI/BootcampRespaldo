package com.meli.tucasita.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District {
    @JsonProperty("district_name")
    private String name;
    @JsonProperty("district_built_price")
    private Double builtPrice;
    @JsonProperty("district_unbuilt_price")
    private Double unbuiltPrice;
}
