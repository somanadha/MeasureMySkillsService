package com.bst.mms.controller;

import com.bst.mms.dto.QuestionDTO;
import com.bst.mms.entity.SkillTest;
import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.service.MeasureMySkillsService;
import com.bst.mms.service.SkillTestConfigurationService;
import com.bst.mms.service.SkillTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("mms")
@RestController
public class MeasureMySkillsServiceController {

    @Autowired
    private MeasureMySkillsService measureMySkillsService;

    @Autowired
    private SkillTestConfigurationService skillTestConfigurationService;

    @Autowired
    private SkillTestService skillTestService;

    @PostMapping("config/create")
    public ResponseEntity<SkillTestConfiguration> createSkillTestConfiguration(@RequestBody SkillTestConfiguration
                                                                            skillTestConfiguration) {

        ResponseEntity<SkillTestConfiguration> configCreateResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            skillTestConfiguration = skillTestConfigurationService.saveSkillTestConfiguration(skillTestConfiguration);
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            configCreateResponseEntity = new ResponseEntity<>(skillTestConfiguration, httpStatus);
        }
        return configCreateResponseEntity;
    }

    @GetMapping("config/find/{configId}")
    public ResponseEntity<SkillTestConfiguration> findSkillTestConfiguration(@PathVariable Integer configId) {

        ResponseEntity<SkillTestConfiguration> configFindResponseEntity = null;
        SkillTestConfiguration skillTestConfiguration = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            skillTestConfiguration = skillTestConfigurationService.findConfiguration(configId);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            configFindResponseEntity = new ResponseEntity<>(skillTestConfiguration, httpStatus);
        }
        return configFindResponseEntity;
    }

    @PostMapping("test/create")
    public ResponseEntity<List<QuestionDTO>> createSkillTest(
            @RequestBody SkillTestConfiguration skillTestConfiguration) {

        ResponseEntity<List<QuestionDTO>>  createdResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            if (skillTestConfiguration.getSkillTestId() == null ) {
                createdResponseEntity = measureMySkillsService.findRandomQuestions(skillTestConfiguration);
            }
            else{
                createdResponseEntity = measureMySkillsService.findConfiguredSkillTestQuestions(skillTestConfiguration);
            }
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
        }
        return createdResponseEntity;
    }

    @PostMapping("test/save")
    public ResponseEntity<SkillTest> saveSkillTest(@RequestBody List<QuestionDTO> questionDTOsList) {

        ResponseEntity<SkillTest> testSaveResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        SkillTest skillTest = null;
        try{
            skillTest = skillTestService.saveSkillTest(questionDTOsList);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            testSaveResponseEntity = new ResponseEntity<> (skillTest, httpStatus);
        }
        return testSaveResponseEntity;
    }
}
