package com.bst.mms.controller.proxy;

import com.bst.mms.dto.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-MANAGEMENT-SERVICE")
public interface QuestionManagementServiceControllerProxy {

    @GetMapping("qms/findRandom/{topicId}/{difficulty}/{count}")
    ResponseEntity<List<QuestionDTO>> findRandomQuestions(@PathVariable Integer topicId,
                                                                 @PathVariable Integer difficulty,
                                                                 @PathVariable Integer count);

    @GetMapping("qms/findByIds")
    ResponseEntity<List<QuestionDTO>> findQuestionsByQuestionIdList(@RequestBody List<Integer> questionIds);
}