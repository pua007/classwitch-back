package com.classwitch.classes.repository;

import com.classwitch.classes.model.dto.response.classes.GetClassMainDto;
import com.classwitch.classes.model.entity.ClassesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassesRepository extends JpaRepository<ClassesEntity, Integer> {
  boolean existsByTitle(String title);

  @Query("SELECT new com.classwitch.classes.model.dto.response.classes.GetClassMainDto(c.title, c.thumbnail, c.price, c.discountRate, c.level) FROM ClassesEntity c WHERE c.classState = :classState")
  List<GetClassMainDto> findGetClasses(@Param("classState") String classState);
}
