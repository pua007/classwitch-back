package com.classwitch.classes.controller;

import com.classwitch.classes.model.dto.request.ClassesRequestDto;
import com.classwitch.classes.model.dto.response.classes.AddClassResponseDto;
import com.classwitch.classes.model.dto.response.classes.GetClassMainDto;
import com.classwitch.classes.repository.ClassesRepository;
import com.classwitch.classes.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassesController {
    private final ClassesService classesService;
    private final ClassesRepository classesRepository;

    @PostMapping("image")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        try{
            String uploaddeFilePath = classesService.uploadFile(file);
            return uploaddeFilePath;
        }catch (IOException e){
            e.printStackTrace();
            return "Fail";
        }
    }

    @PostMapping("add-class")
    public ResponseEntity<? super AddClassResponseDto> addClass(@ModelAttribute ClassesRequestDto dto){
        try {
            if(classesRepository.existsByTitle(dto.getTitle())){
                return AddClassResponseDto.duplicateTitle();
            }
            classesService.addClass(dto);
            return AddClassResponseDto.success();
        }catch (Exception e){
            e.printStackTrace();
            return AddClassResponseDto.databaseError();
        }

    }

    @PostMapping("class-main/{classState}")
    public List<GetClassMainDto> classMain(@PathVariable("classState") String classState ) throws MalformedURLException {
        System.out.println("시작?");
        List<GetClassMainDto> dto = classesRepository.findGetClasses(classState);
        UrlResource urlResource;
        for(GetClassMainDto data : dto){
            System.out.println(data.getThumbnail());
            urlResource = new UrlResource("file:" + data.getThumbnail());
            data.setThumbnail(urlResource.getURL().toString());
        }
        return dto;
    }

}
