package com.bst.mms.controller.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map.Entry;
import java.util.List;
import java.util.Map;

@FeignClient("QUESTION-MANAGEMENT-SERVICE")
public interface QuestionManagementServiceControllerProxy {
    @GetMapping("qms/findRandom/{topicId}/{difficulty}/{count}")
    public ResponseEntity<Map<Integer, Entry<String, List<Entry<Integer, String>>>>> findRandomQuestions(
            @PathVariable Integer topicId, @PathVariable Integer difficulty, @PathVariable Integer count);
}