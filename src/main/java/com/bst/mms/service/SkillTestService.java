package com.bst.mms.service;

import com.bst.mms.controller.proxy.QuestionManagementServiceControllerProxy;
import com.bst.mms.dto.QuestionDTO;
import com.bst.mms.entity.SkillTest;
import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.repository.SkillTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillTestService {

    @Autowired
    private QuestionManagementServiceControllerProxy questionManagementServiceControllerProxy;

    @Autowired
    private SkillTestRepository skillTestRepository;

    @Autowired
    SkillTestConfigurationService skillTestConfigurationService;

    public  SkillTest createSkillTest(SkillTestConfiguration skillTestConfiguration) {

        ResponseEntity<List<QuestionDTO>> createdResponseEntity =
                questionManagementServiceControllerProxy.findRandomQuestions(skillTestConfiguration.getTopicId(),
                        skillTestConfiguration.getDifficultyLevel(), skillTestConfiguration.getQuestionCount());

        List<QuestionDTO> questionDTOList = Objects.requireNonNull(createdResponseEntity.getBody());

        SkillTest skillTest = new SkillTest(questionDTOList);
        skillTest = saveSkillTest(skillTest);

        skillTestConfiguration.setSkillTestId(skillTest.getSkillTestId());
        skillTestConfigurationService.saveSkillTestConfiguration(skillTestConfiguration);
        return skillTest;
    }

    public SkillTest findSkillTest(Integer skillTestId) {
        SkillTest skillTest = skillTestRepository.findById(skillTestId).orElse(null);

        if (skillTest != null) {
            List<QuestionDTO> questionDTOList =
                    questionManagementServiceControllerProxy.findQuestionsByQuestionIdList(
                            skillTest.extractQuestionIDList()).getBody();

            skillTest.setQuestionDTOList(questionDTOList);
        }
        return skillTest;
    }

    public SkillTest saveSkillTest(SkillTest skillTest) {
        skillTest.syncFromUserSelectedAnswers();
        SkillTest dummySkillSet = skillTestRepository.save(skillTest);
        return skillTest;
    }
}
