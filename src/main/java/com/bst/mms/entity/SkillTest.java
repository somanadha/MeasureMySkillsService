package com.bst.mms.entity;

import com.bst.mms.dto.AnswerOptionDTO;
import com.bst.mms.dto.QuestionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Entity
@Data
public class SkillTest {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer skillTestId;

    @JsonIgnore
    @ElementCollection
    private Map<Integer, List<Integer>> questionAndSelectedAnswerIds = new HashMap<>();

    @Transient
    private List<QuestionDTO> questionDTOList = new ArrayList<>();

    public SkillTest(List<QuestionDTO> questionDTOList) {
        setQuestionDTOList(questionDTOList);
    }

    public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
        if (questionDTOList != null){
            this.questionDTOList = questionDTOList;
        }
        else {
            questionDTOList = new ArrayList<>();
        }
        syncToUserSelectedAnswers();
    }

    public void syncFromUserSelectedAnswers(){
        questionAndSelectedAnswerIds = new HashMap<>();
        questionDTOList.forEach(questionDTO -> {
            var answerOptionDTOList =  questionDTO.getAnswerOptionDTOs();
            List<Integer> selectedAnswerOptionIds = new ArrayList<>();
            answerOptionDTOList.forEach(answerOptionDTO -> {
                if (answerOptionDTO.getIsSelected()){
                    selectedAnswerOptionIds.add(answerOptionDTO.getAnswerId());
                }
            });
            questionAndSelectedAnswerIds.put(questionDTO.getQuestionId(), selectedAnswerOptionIds);
        });
    }

    public void syncToUserSelectedAnswers(){
        QuestionDTO questionDTO = null;
        for (int i =0; i< questionDTOList.size(); ++i) {
            questionDTO = questionDTOList.get(i);
            List<AnswerOptionDTO> answerOptionDTOList = questionDTO.getAnswerOptionDTOs();
            List<Integer> selectedAnswerOptionsIdsList = questionAndSelectedAnswerIds.get(questionDTO.getQuestionId());

            if (selectedAnswerOptionsIdsList != null) {
                selectedAnswerOptionsIdsList.forEach(selectedAnswerOptionsId -> {
                    answerOptionDTOList.forEach(answerOptionDTO -> {
                        if (answerOptionDTO.getAnswerId().equals(selectedAnswerOptionsId)) {
                            answerOptionDTO.setIsSelected(true);
                        }
                    });
                });
            }
        }
    }

    @JsonIgnore
    public List<Integer> extractQuestionIDList() {
        List<Integer> questionIdList = new ArrayList<>();

        if (questionDTOList != null) {
            questionDTOList.forEach(questionDTO -> questionIdList.add(questionDTO.getQuestionId()));
        }
        return questionIdList;
    }
}
