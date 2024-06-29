package Tejveer.project.Upload.and.download.files.Controller;

import Tejveer.project.Upload.and.download.files.DTO.ResponseDTO;
import Tejveer.project.Upload.and.download.files.Entity.Attachment;
import Tejveer.project.Upload.and.download.files.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseDTO uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = attachmentService.saveAttachment(file);
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseDTO(attachment.getFileName(),
                               downloadUrl,
                               file.getContentType(),
                               file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Optional<Attachment> attachment = attachmentService.getAttachment(fileId);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(attachment.get().getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + attachment.get().getFileName()
                                    + "\"")
                    .body(new ByteArrayResource(attachment.get().getData()));

    }

    // Endpoint to share an attachment with users
    @PostMapping("/{attachmentId}/share")
    public ResponseEntity<?> shareAttachment(@PathVariable String attachmentId, @RequestBody List<String> userIds) {
        try {
            attachmentService.shareAttachment(attachmentId,userIds);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to get shared attachments for a user
    @GetMapping("/shared")
    public ResponseEntity<Object> getSharedAttachments() {
        List<Attachment> sharedAttachments = attachmentService.getSharedAttachments();
        return ResponseEntity.ok(sharedAttachments);
    }


}
