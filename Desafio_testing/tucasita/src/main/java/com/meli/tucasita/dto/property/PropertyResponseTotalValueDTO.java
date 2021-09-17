package com.meli.tucasita.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.dto.district.DistrictDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyResponseTotalValueDTO {
    @JsonProperty("prop_name")
    String name;
    @JsonProperty("prop_land_width")
    Double width;
    @JsonProperty("prop_land_length")
    Double length;
    @JsonProperty("prop_built_price")
    Double builtPrice;
    @JsonProperty("prop_unbuilt_price")
    Double unbuiltPrice;
    @JsonProperty("prop_price")
    Double price;

    DistrictDTO district;
}
