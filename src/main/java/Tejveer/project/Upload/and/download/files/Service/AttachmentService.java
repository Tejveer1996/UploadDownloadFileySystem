package Tejveer.project.Upload.and.download.files.Service;

import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Optional<Attachment> getAttachment(String fileId) throws Exception;

    void shareAttachment(String attachmentId, List<String> userIds);

    List<Attachment> getSharedAttachments();

    List<Attachment> getAllVersionsOfFile(String fileName);
}
