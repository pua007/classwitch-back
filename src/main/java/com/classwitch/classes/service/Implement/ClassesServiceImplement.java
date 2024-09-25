package com.classwitch.classes.service.Implement;

import com.classwitch.classes.Exception.ExceptionCode;
import com.classwitch.classes.Exception.ExceptionMessage;
import com.classwitch.classes.model.dto.request.ClassesRequestDto;
import com.classwitch.classes.model.dto.response.ResponseDto;
import com.classwitch.classes.model.dto.response.classes.AddClassResponseDto;
import com.classwitch.classes.model.entity.ClassesEntity;
import com.classwitch.classes.repository.ClassesRepository;
import com.classwitch.classes.service.ClassesService;
import com.classwitch.users.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassesServiceImplement implements ClassesService {

    private final ClassesRepository classesRepository;

    @Value("${file.upload-dir}")
    String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString()+"_"+originalFileName;
        File saveFile = new File(uploadDir + uniqueFileName);

        file.transferTo(saveFile);
        return saveFile.getPath();
    }

    @Override
    public void addClass(ClassesRequestDto dto) throws IOException {
        String classUrl = uploadFile(dto.getThumbnail());
        ClassesEntity classesEntity = ClassesEntity.toClassesEntity(dto, classUrl);
        classesRepository.save(classesEntity);
    }


}
