package clase4.ej.linkTracker.modelLogic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    int id;
    String url;
    int metric;
    String password;
}
