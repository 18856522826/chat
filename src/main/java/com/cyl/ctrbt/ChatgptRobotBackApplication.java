package com.cyl.ctrbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.cyl.ctrbt.mapper"})
public class ChatgptRobotBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatgptRobotBackApplication.class, args);
    }

}
