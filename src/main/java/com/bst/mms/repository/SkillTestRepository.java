package com.bst.mms.repository;

import com.bst.mms.entity.SkillTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillTestRepository extends JpaRepository<SkillTest, Integer> {
}
