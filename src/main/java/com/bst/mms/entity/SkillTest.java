package com.bst.mms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Data
public class SkillTest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer skillTestId;

    @ElementCollection
    private Map<Integer, List<Integer>> questionAndAnswerIds;
}
