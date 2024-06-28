package Tejveer.project.Upload.and.download.files.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
