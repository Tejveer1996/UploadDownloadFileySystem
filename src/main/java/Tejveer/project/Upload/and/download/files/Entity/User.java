package Tejveer.project.Upload.and.download.files.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @UuidGenerator
    private String Id;

    private String username;
    private String password;

    public User(Set<Long> userIds) {
    }
}
