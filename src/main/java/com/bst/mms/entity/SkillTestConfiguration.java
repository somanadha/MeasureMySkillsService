package com.bst.mms.entity;

import com.bst.mms.data.SkillLevel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SkillTestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer skillTestId;

    private String title;

    private Integer topicId;

    private Integer questionCount;

    private Integer difficultyLevel;
}
