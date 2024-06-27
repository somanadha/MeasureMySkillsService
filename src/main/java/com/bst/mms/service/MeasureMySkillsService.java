package com.bst.mms.service;

import com.bst.mms.controller.proxy.QuestionManagementServiceControllerProxy;
import com.bst.mms.dto.AnswerOptionDTO;
import com.bst.mms.dto.QuestionDTO;
import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.repository.MeasureMySkillsServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map.Entry;
import java.util.List;
import java.util.Map;

@Service
public class MeasureMySkillsService {

    @Autowired
    private QuestionManagementServiceControllerProxy questionManagementServiceControllerProxy;

    @Autowired
    private MeasureMySkillsServiceRepository measureMySkillsServiceRepository;

    @Autowired
    private SkillTestService skillTestService;

    public ResponseEntity<List<QuestionDTO>> findRandomQuestions(SkillTestConfiguration skillTestConfiguration) {

        return questionManagementServiceControllerProxy.findRandomQuestions(skillTestConfiguration.getTopicId(),
                skillTestConfiguration.getDifficultyLevel(), skillTestConfiguration.getQuestionCount());
    }

    public ResponseEntity<List<QuestionDTO>> findConfiguredSkillTestQuestions(
            SkillTestConfiguration skillTestConfiguration) {

        List<Integer> questionIds = skillTestService.findConfiguredQuestionIds(skillTestConfiguration.getSkillTestId());

        return questionManagementServiceControllerProxy.findQuestionsByQuestionIdList(questionIds);
    }
 }
