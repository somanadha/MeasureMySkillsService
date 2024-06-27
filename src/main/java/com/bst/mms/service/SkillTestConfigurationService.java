package com.bst.mms.service;

import com.bst.mms.entity.SkillTestConfiguration;
import com.bst.mms.repository.MeasureMySkillsServiceRepository;
import com.bst.mms.repository.SkillTestConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillTestConfigurationService {

    @Autowired
    private SkillTestConfigurationRepository skillTestConfigurationRepository;

    public SkillTestConfiguration findConfiguration(Integer configId) {
        return skillTestConfigurationRepository.findById(configId).orElse(new SkillTestConfiguration() );
    }

    public SkillTestConfiguration saveSkillTestConfiguration(SkillTestConfiguration skillTestConfiguration) {
        return skillTestConfigurationRepository.save(skillTestConfiguration);
    }
}
