package Tejveer.project.Upload.and.download.files.Service;

import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import Tejveer.project.Upload.and.download.files.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Attachment attachment = new Attachment(fileName,
                                       file.getContentType(),
                                       file.getBytes());
            return  attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save file : "+fileName);
        }
    }

    @Override
    public Optional<Attachment> getAttachment(String fileId) throws Exception {
        return Optional.ofNullable(attachmentRepository.findById(fileId)
                .orElseThrow(() -> new Exception("file not found with id : " + fileId)));
    }
}
