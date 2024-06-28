package com.bst.mms.controller;

import com.bst.mms.entity.SkillTest;
import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.service.SkillTestConfigurationService;
import com.bst.mms.service.SkillTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mms/skillTest")
@RestController
public class SkillTestServiceController {
    @Autowired
    private SkillTestService skillTestService;

    @Autowired
    private SkillTestConfigurationService skillTestConfigurationService;

    @PostMapping("create/configId/{configId}")
    public ResponseEntity<SkillTest> createSkillTest(@PathVariable Integer configId) {

        ResponseEntity<SkillTest>  skillTestResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        SkillTest skillTest = null;
        try{
            SkillTestConfiguration skillTestConfiguration = skillTestConfigurationService.findConfiguration(configId);
            if (skillTestConfiguration.getSkillTestId() == null ) {
                skillTest = skillTestService.createSkillTest(skillTestConfiguration);
            }
            else {
                skillTest = skillTestService.findSkillTest(skillTestConfiguration.getSkillTestId());
            }
            httpStatus = HttpStatus.CREATED;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            skillTestResponseEntity = new ResponseEntity<>(skillTest, httpStatus);
        }
        return skillTestResponseEntity;
    }

    @PostMapping("save")
    public ResponseEntity<SkillTest> saveSkillTest(@RequestBody SkillTest skillTest) {

        ResponseEntity<SkillTest> testSaveResponseEntity = null;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try{
            skillTest = skillTestService.saveSkillTest(skillTest);
            httpStatus = HttpStatus.OK;
        }catch (Exception exception) {
            System.out.println("Exception:"+exception.getMessage());
        }finally {
            testSaveResponseEntity = new ResponseEntity<> (skillTest, httpStatus);
        }
        return testSaveResponseEntity;
    }
}
