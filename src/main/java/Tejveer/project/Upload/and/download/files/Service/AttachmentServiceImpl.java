package Tejveer.project.Upload.and.download.files.Service;

import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import Tejveer.project.Upload.and.download.files.Entity.User;
import Tejveer.project.Upload.and.download.files.Repository.AttachmentRepository;
import Tejveer.project.Upload.and.download.files.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String version = file.getName()+ fileName.length() + file.hashCode();

        try {
            Attachment attachment = new Attachment(fileName,
                                       file.getContentType(),
                                       file.getBytes());
            attachment.setVersion(version);
            return  attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save file : "+fileName);
        }
    }


    public Attachment getFileByVersion(String fileName, String version) {
        return attachmentRepository.findByFileNameAndVersion(fileName, version)
                .orElseThrow(() -> new RuntimeException("File not found with name " + fileName + " and version " + version));
    }

    public List<Attachment> getAllVersionsOfFile(String fileName) {
        return attachmentRepository.findByFileName(fileName);
    }



    @Override
    public Optional<Attachment> getAttachment(String fileId) throws Exception {
        return Optional.ofNullable(attachmentRepository.findById(fileId)
                .orElseThrow(() -> new Exception("file not found with id : " + fileId)));
    }

    public void shareAttachment(String attachmentId, List<String> userIds) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        // Add user ids to sharedWith set
        List<User> list = new ArrayList<>();
        for (String userId : userIds){
            list.add(userRepository.findById(userId).get());
        }
        attachment.setSharedWith(list);

        attachmentRepository.save(attachment);
    }

    public List<Attachment> getSharedAttachments() {
        List<Attachment> list = attachmentRepository.findAll();
        List<Attachment> resultList = new ArrayList<>();
        for (Attachment attachment : list){
            if (!attachment.getSharedWith().isEmpty()){
                resultList.add(attachment);
            }
        }
        return resultList;

    }

}
