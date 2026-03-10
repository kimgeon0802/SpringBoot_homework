package com.example.Myspring_homework.runner;

//import com.example.Myspring_homework.config.CustomerVO;
import com.example.Myspring_homework.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyPropRunner implements ApplicationRunner {
    @Value("${myprop.username}")
    private String name;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private Environment environment;

    @Autowired
    private MyPropProperties properties;

    //Logger 객체생성
    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Logger 구현체 클래스명 = " + logger.getClass().getName());
        logger.info("MyBootProperties getName() = {}", properties.getUsername());
        logger.info("MyBootProperties getPort() = {}", properties.getPort());

        logger.info("${myprop.username}  = {}", name);
        logger.info("${myprop.port}  = {}", port);

        logger.debug("DEBUG 레벨");
        logger.debug(">>> ${myprop.userName}  = {}", environment.getProperty("myprop.username"));
        logger.debug("VM 아규먼트 foo : {}", args.containsOption("foo"));
        logger.debug("Program 아규먼트 bar : {}", args.containsOption("bar"));

    }
}