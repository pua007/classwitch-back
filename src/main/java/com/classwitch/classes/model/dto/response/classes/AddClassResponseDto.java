package com.classwitch.classes.model.dto.response.classes;

import com.classwitch.classes.Exception.ExceptionCode;
import com.classwitch.classes.Exception.ExceptionMessage;
import com.classwitch.classes.model.dto.response.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class AddClassResponseDto extends ResponseDto {
    public  AddClassResponseDto(){
        super();
    }

    public static ResponseEntity<AddClassResponseDto> success(){
        AddClassResponseDto responseBody = new AddClassResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicateTitle(){
        ResponseDto responseBody = new ResponseDto(ExceptionCode.DUPLICATE_TITLE, ExceptionMessage.DUPLICATE_TITLE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
