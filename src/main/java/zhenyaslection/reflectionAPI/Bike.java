package zhenyaslection.reflectionAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bike {
    private String model;
    private String serialNo;
    public int year;

    @SimpleAnnotation(name = "myName")
    private void setYearAndModel(int year, String model) {
        this.year = year;
        this.model = model;
    }
}
