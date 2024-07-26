package com.cogent.student_service.feignclients;

import com.cogent.student_service.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="book-service", path="/api/book")
public interface BookFeignClient {

    @GetMapping("/getById/{id}")
    public BookResponse getById(@PathVariable int id);

}
