package repository.entities;

import model.Email;
import model.Password;
import model.User;
import model.UserName;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AUTH_USER")
@NamedQueries({
        @NamedQuery(name = "UserPO.findByEmail", query = "Select u FROM UserPO u where u.email=:email or u.userName=:userName"),
})

public class UserPO {
    public static final String FIND_BY_EMAIL = "UserPO.findByEmail";
    public static final String EMAIL_PARAM = "email";
    public static final String USERNAME_PARAM = "userName";

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "ID", columnDefinition = "varchar(40)")
    private UUID id;

    @Column(name = "EMAIL", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", columnDefinition = "varchar(60)", nullable = false)
    private String password;

    @Column(name = "USER_NAME", columnDefinition = "varchar(40)", unique = true, nullable = false)
    private String userName;

    public UserPO() {
        //JPA
    }

    public UserPO(String email, String userName, String password) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public User toUser() {
        return new User(new model.Id(id), new Email(email), new UserName(userName), new Password(password));

    }
}
