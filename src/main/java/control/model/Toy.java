package control.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Toy {
    private int id;
    private int weight;
    private String title;
}
