package com.bst.mms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SkillTest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
}
