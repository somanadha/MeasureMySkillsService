package com.bst.mms.service;

import com.bst.mms.controller.proxy.QuestionManagementServiceControllerProxy;
import com.bst.mms.entity.SkillTestConfiguration;
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

    public Map<Integer, Entry<String, List<Entry<Integer, String>>>>
    findRandomQuestionsByTopicIdAndDifficultyLevel(SkillTestConfiguration skillTestConfiguration) {

        return questionManagementServiceControllerProxy.findRandomQuestions(skillTestConfiguration.getTopicId(),
                skillTestConfiguration.getDifficultyLevel(), skillTestConfiguration.getQuestionCount()).getBody();
    }

    public Map<Integer, Entry<String, List<Entry<Integer, String>>>> findConfiguredQuestions(
            SkillTestConfiguration skillTestConfiguration) {


        return questionManagementServiceControllerProxy.findConfiguredQuestions().getBody();
    }
}
