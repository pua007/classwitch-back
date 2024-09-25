package com.classwitch.classes.service;


import com.classwitch.classes.model.dto.request.ClassesRequestDto;
import com.classwitch.classes.model.dto.response.classes.AddClassResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public interface ClassesService {
    String uploadFile(MultipartFile file) throws IOException;
    void addClass(ClassesRequestDto dto) throws IOException;

}
