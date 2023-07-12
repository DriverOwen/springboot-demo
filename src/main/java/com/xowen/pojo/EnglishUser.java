package com.xowen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishUser {

    private Integer id;
    private String username;
    private String password;
    private String role;
    private short authrioty;
    private String email;
    private String phone;
    private LocalDateTime createTime;
}
