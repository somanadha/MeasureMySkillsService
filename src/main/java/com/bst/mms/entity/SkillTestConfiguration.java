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

    private Integer topicId;

    private Integer questionCount;

    @Enumerated(EnumType.ORDINAL)
    private SkillLevel skillLevel;
}
