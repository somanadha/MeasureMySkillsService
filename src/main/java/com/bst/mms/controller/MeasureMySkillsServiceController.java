package com.bst.mms.controller;

import com.bst.mms.entity.SkillTest;
import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.service.MeasureMySkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.Map;

@RequestMapping("mms")
@RestController
public class MeasureMySkillsServiceController {

    @Autowired
    private MeasureMySkillsService measureMySkillsService;

    @PostMapping("config/create")
    public ResponseEntity<Integer> createSkillTestConfiguration(@RequestBody SkillTestConfiguration
                                                                            skillTestConfiguration) {

        ResponseEntity<Integer> configCreateResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Integer configId = null;
        try{
            configId = measureMySkillsService.saveSkillTestConfiguration(skillTestConfiguration);
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            configCreateResponseEntity = new ResponseEntity<>(configId, httpStatus);
        }
        return configCreateResponseEntity;
    }

    @GetMapping("config/find/{configId}")
    public ResponseEntity<SkillTestConfiguration> findSkillTestConfiguration(@PathVariable Integer configId) {

        ResponseEntity<SkillTestConfiguration> configFindResponseEntity = null;
        SkillTestConfiguration skillTestConfiguration = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            skillTestConfiguration = measureMySkillsService.findConfiguration(configId);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            configFindResponseEntity = new ResponseEntity<>(skillTestConfiguration, httpStatus);
        }
        return configFindResponseEntity;
    }

    @PostMapping("test/create")
    public ResponseEntity<Map<Integer, Entry<String, List<Entry<Integer, String>>>>> createSkillTest(
            @RequestBody SkillTestConfiguration skillTestConfiguration ) {

        ResponseEntity<Map<Integer, Entry<String, List<Entry<Integer, String>>>>>  createResponseEntity = null;
        Map<Integer, Entry<String, List<Entry<Integer, String>>>> questionAndAnswersMap = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            if (skillTestConfiguration.getSkillTestId() == null ) {
                questionAndAnswersMap = measureMySkillsService.findRandomQuestionsByTopicIdAndDifficultyLevel(
                        skillTestConfiguration);
            }
            else{
                questionAndAnswersMap = measureMySkillsService.findConfiguredQuestions(skillTestConfiguration);
            }
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            createResponseEntity = new ResponseEntity<> (questionAndAnswersMap, httpStatus);
        }
        return createResponseEntity;
    }

    @PostMapping("test/save")
    public ResponseEntity<Integer> saveSkillTest(@RequestBody SkillTest skillTest) {

        ResponseEntity<Integer> testSaveResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Integer skillTestId = null;
        try{
            skillTestId = measureMySkillsService.saveSkillTest(skillTest);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            testSaveResponseEntity = new ResponseEntity<> (skillTestId, httpStatus);
        }
        return testSaveResponseEntity;
    }
}
