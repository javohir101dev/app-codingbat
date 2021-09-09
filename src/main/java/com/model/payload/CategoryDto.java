package com.model.payload;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private String name;

    private List<Long> languageIds;

    private String description;
}
