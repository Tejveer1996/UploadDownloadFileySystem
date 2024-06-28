package Tejveer.project.Upload.and.download.files.Service;

import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Optional<Attachment> getAttachment(String fileId) throws Exception;
}
