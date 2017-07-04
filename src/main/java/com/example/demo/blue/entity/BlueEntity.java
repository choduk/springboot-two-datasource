package com.example.demo.blue.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author choduk88@sk.com
 * @since 2017. 7. 4..
 */
@Data
@Entity
@Table(name = "BLUE_USER")
@NoArgsConstructor
public class BlueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    public BlueEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
