package com.bst.mms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

@FeignClient("QUESTION-MANAGEMENT-SERVICE")
public interface QuestionManagementServiceControllerProxy {
    @GetMapping("findRandom/{topicId}/{difficulty}/{count}")
    public ResponseEntity<Map<Integer, AbstractMap.SimpleEntry<String, List<String>>>> findRandomQuestions(
            @PathVariable Integer topicId, @PathVariable Integer difficulty, @PathVariable Integer count);
}