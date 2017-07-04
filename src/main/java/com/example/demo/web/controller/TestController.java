package com.example.demo.web.controller;

import com.example.demo.blue.entity.BlueEntityRepository;
import com.example.demo.green.entity.GreenEntityRepository;
import com.example.demo.web.controller.dto.EntityResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author choduk88@sk.com
 * @since 2017. 7. 4..
 */
@RestController(value = "/")
@AllArgsConstructor
public class TestController {

    // 데이터 확인용 테스트 컨트롤러
    @NotNull
    private BlueEntityRepository blueEntityRepository;

    @NotNull
    private GreenEntityRepository greenEntityRepository;

    @GetMapping(value = "blue")
    public List<EntityResult> blue() {
        return blueEntityRepository.findAll().stream()
                .map(EntityResult::new)
                .collect(toList());
    }

    @GetMapping(value = "green")
    public List<EntityResult> green() {
        return greenEntityRepository.findAll().stream()
                .map(EntityResult::new)
                .collect(toList());
    }
}
