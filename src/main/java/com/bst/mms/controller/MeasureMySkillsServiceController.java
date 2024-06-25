package com.bst.mms.controller;

import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.service.MeasureMySkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("mms")
@RestController
public class MeasureMySkillsServiceController {

    @Autowired
    private MeasureMySkillsService measureMySkillsService;


    @PostMapping("test/create")
    public ResponseEntity<Map<Integer, SimpleEntry<String, List<String>>>> createSkillMeasuringTest(
            @RequestBody SkillTestConfiguration skillTestConfiguration) {

        ResponseEntity<Map<Integer, SimpleEntry<String, List<String>>>> testCreateResponseEntity = null;
        Map<Integer, SimpleEntry<String, List<String>>> questionAndAnswersMap = new HashMap<>();
        org.springframework.http.HttpStatus httpStatus = HttpStatus.OK;

        Integer testId = -1;
        try{
            testCreateResponseEntity = measureMySkillsService.findRandomQuestionsByTopicIdAndDifficultyLevel(
                    skillTestConfiguration);

            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            //testCreateResponseEntity = new ResponseEntity<> (questionAndAnswersMap, httpStatus);
        }
        return testCreateResponseEntity;
    }
}
