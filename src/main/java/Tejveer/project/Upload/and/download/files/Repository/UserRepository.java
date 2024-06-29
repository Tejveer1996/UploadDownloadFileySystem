package Tejveer.project.Upload.and.download.files.Repository;

import Tejveer.project.Upload.and.download.files.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
