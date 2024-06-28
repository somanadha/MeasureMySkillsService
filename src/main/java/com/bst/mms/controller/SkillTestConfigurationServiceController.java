package com.bst.mms.controller;

import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.service.SkillTestConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mms/config")
@RestController
public class SkillTestConfigurationServiceController {

    @Autowired
    private SkillTestConfigurationService skillTestConfigurationService;

    @PostMapping("create")
    public ResponseEntity<SkillTestConfiguration> saveSkillTestConfiguration(
            @RequestBody SkillTestConfiguration skillTestConfiguration) {

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

    @GetMapping("find/{configId}")
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
}
