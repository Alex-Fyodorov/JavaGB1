package javathree.hw2;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class MyDate {
    @RandomDate
    private Date date;

    @RandomDate
    private Instant instant;

    @RandomDate
    private LocalDate localDate;

    @RandomDate
    private LocalDateTime localDateTime;
}
