package clase4.ej.linkTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkAllDTO {
    int id;
    String url;
    String password;
    int metric;
}
