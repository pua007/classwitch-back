package com.classwitch.classes.model.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassesRequestDto {

    private String title;
    private MultipartFile thumbnail;
    private String description;
    private String level;
    private String price;
    private int discountRate;
    private String classState;
    private String promotionalMessage;
    private int numberOfLectures;
    private int learningHours;
    private int creatorId;
}
