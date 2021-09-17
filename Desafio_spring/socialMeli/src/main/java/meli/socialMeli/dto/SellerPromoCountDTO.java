package meli.socialMeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellerPromoCountDTO {
    private int userId;
    private String userName;
    private int promoproducts_count;
}
