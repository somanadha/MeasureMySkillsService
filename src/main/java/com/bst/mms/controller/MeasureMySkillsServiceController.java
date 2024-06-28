package com.bst.mms.controller;

import com.bst.mms.service.MeasureMySkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("mms")
@RestController
public class MeasureMySkillsServiceController {

    @Autowired
    private MeasureMySkillsService measureMySkillsService;

}
