package com.bst.mms.repository;

import com.bst.mms.entity.SkillTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureMySkillsServiceRepository extends JpaRepository<SkillTest, Integer> {
}
