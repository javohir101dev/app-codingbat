package com.model.payload;


import lombok.Data;


@Data
public class AnswerDto {

    private String code;

    private Long taskId;

    private Long userId;


}
