package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    private String example;

    @Column(nullable = false)
    private String body;

    @Column
    private String hint;

    @Column
    private String solution;

    @Column
    private boolean hasStar=false;

    @ManyToOne
    private Language language;

    @ManyToOne
    private Category category;


}
