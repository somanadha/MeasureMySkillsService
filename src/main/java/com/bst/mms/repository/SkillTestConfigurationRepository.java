package com.bst.mms.repository;

import com.bst.mms.entity.SkillTestConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTestConfigurationRepository extends JpaRepository<SkillTestConfiguration, Integer> {
}
