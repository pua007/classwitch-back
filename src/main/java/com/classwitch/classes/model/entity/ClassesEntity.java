package com.classwitch.classes.model.entity;


import com.classwitch.classes.model.dto.request.ClassesRequestDto;
import jakarta.persistence.*;
import lombok.NonNull;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Setter
@Entity
@Table(name ="classes")
public class ClassesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classId;

    @Column
    @NonNull
    private String title;

    @Column
    @NonNull
    private String thumbnail;

    @Column
    @NonNull
    private String description;

    @Column
    @NonNull
    private String level;

    @Column
    @NonNull
    private String price;

    @Column
    @Nullable
    private int discountRate;

    @Column
    @NonNull
    private String classState;

    @Column
    private String promotionalMessage;

    @Column
    @NonNull
    private int numberOfLectures;

    @Column
    @NonNull
    private int learningHours;

    @Column
    @NonNull
    private int creatorId;

    @Column
    private int likeCount;

    public static ClassesEntity toClassesEntity(ClassesRequestDto dto, String classUrl){
        ClassesEntity classesEntity = new ClassesEntity();


        classesEntity.setTitle(dto.getTitle());
        classesEntity.setDescription(dto.getDescription());
        classesEntity.setThumbnail(classUrl);
        classesEntity.setClassState(dto.getClassState());
        classesEntity.setLevel(dto.getLevel());
        classesEntity.setDiscountRate(dto.getDiscountRate());
        classesEntity.setLikeCount(0);
        classesEntity.setPrice(dto.getPrice());
        classesEntity.setPromotionalMessage(dto.getPromotionalMessage());
        classesEntity.setLearningHours(dto.getLearningHours());
        classesEntity.setNumberOfLectures(dto.getNumberOfLectures());
        classesEntity.setCreatorId(dto.getCreatorId());

        return classesEntity;
    }
}
