package com.example.carecareforeldres.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface FileUploadBrahmi {
    String uploadFile(MultipartFile multipartFile) throws IOException;

}
