package com.model.payload;

import com.entity.Category;
import com.entity.Language;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
public class TaskDto {


    private String name;

    private String description;

    private String example;

    private String body;

    private String hint;

    private String solution;

    private boolean hasStar;

    private Long languageId;

    private Long categoryId;

}
