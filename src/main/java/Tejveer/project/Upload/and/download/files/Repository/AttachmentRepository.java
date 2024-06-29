package Tejveer.project.Upload.and.download.files.Repository;

import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
}
