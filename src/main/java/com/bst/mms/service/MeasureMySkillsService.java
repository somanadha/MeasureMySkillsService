package com.bst.mms.service;

import com.bst.mms.feign.QuestionManagementServiceControllerProxy;
import com.bst.mms.entity.SkillTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

@Service
public class MeasureMySkillsService {
    @Autowired
    private QuestionManagementServiceControllerProxy questionManagementServiceControllerProxy;

    public ResponseEntity<Map<Integer, SimpleEntry<String, List<String>>>> findRandomQuestionsByTopicIdAndDifficultyLevel(
            SkillTestConfiguration skillTestConfiguration) {
        return questionManagementServiceControllerProxy.findRandomQuestions(skillTestConfiguration.getTopicId(),
                1, skillTestConfiguration.getQuestionCount());
    }
}
