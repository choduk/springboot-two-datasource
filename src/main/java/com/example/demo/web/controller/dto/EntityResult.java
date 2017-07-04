package com.example.demo.web.controller.dto;

import com.example.demo.blue.entity.BlueEntity;
import com.example.demo.green.entity.GreenEntity;
import lombok.Getter;

/**
 * @author choduk88@sk.com
 * @since 2017. 7. 4..
 */
@Getter
public class EntityResult {

    private Long id;
    private String username;
    private String password;

    public EntityResult(BlueEntity entity) {
        id = entity.getId();
        username = entity.getUsername();
        password = entity.getPassword();
    }

    public EntityResult(GreenEntity entity) {
        id = entity.getId();
        username = entity.getUsername();
        password = entity.getPassword();
    }
}
