package javathree.hw4;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(String firstName, String secondName, Group group) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id: " + id +
                ", " + firstName +
                " " + secondName +
                ", group: " + group.getName() +
                '}';
    }
}
