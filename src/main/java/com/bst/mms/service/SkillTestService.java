package com.bst.mms.service;

import com.bst.mms.dto.QuestionDTO;
import com.bst.mms.entity.SkillTest;
import com.bst.mms.repository.SkillTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkillTestService {

    @Autowired
    private SkillTestRepository skillTestRepository;

    public SkillTest saveSkillTest(List<QuestionDTO> questionDTOsList) {
        SkillTest skillTest = new SkillTest();

        Map<Integer, List<Integer>> questionAndAnswerIds = new HashMap<>();

        questionDTOsList.forEach(questionDTO -> questionAndAnswerIds.put(questionDTO.getQuestionId(),
                questionDTO.getAnswerOptionIds()));

        skillTest.setQuestionAndAnswerIds(questionAndAnswerIds);

        return skillTestRepository.save(skillTest);
    }

    public List<Integer> findConfiguredQuestionIds(Integer skillTestId) {
        List<Integer> questionIdsList = new ArrayList<Integer>();

        skillTestRepository.findById(skillTestId).ifPresent(skillTest -> skillTest.getQuestionAndAnswerIds().forEach(
                (questionId, answerIdsList) ->questionIdsList.add(questionId)));

        return questionIdsList;
    }
}
