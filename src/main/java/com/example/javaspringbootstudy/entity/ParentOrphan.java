package com.example.javaspringbootstudy.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ParentOrphan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<ChildOrphan> children = new ArrayList<>();
}
