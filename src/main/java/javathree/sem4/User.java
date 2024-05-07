package javathree.sem4;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Animal> animals;

    public User(String login, Boolean active) {
        this.login = login;
        this.active = active;
    }
}
