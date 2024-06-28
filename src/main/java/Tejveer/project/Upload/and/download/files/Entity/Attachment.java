package Tejveer.project.Upload.and.download.files.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Attachment {

    @Id
    @UuidGenerator
    private String id;

    private String fileName;
    private String fileType;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] data;

    public Attachment(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}

