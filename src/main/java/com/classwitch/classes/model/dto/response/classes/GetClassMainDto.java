package com.classwitch.classes.model.dto.response.classes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetClassMainDto {

    private  String title;
    private  String thumbnail;
    private  String price;
    private  int discountRate;
    private  String level;

}
