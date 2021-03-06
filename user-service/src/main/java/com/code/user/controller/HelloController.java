package com.code.user.controller;

import com.code.user.entity.MicroTest;
import com.code.user.feign.OrderClient;
import com.code.user.service.MicroTestService;
import com.code.user.util.MicroUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class HelloController {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private MicroTestService microTestService;

    @Value("${crawler.test:}")
    private String crawler;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        System.out.println("入参：" + name);
        log.info("current:{}", MicroUserUtil.getCurrentUser());
        return "hello " + name + "=====" + orderClient.order(name);
    }


    @GetMapping("/crawler")
    public String crawler(){
        return this.crawler;
    }


    @GetMapping("/micro/i18n/test")
    public MicroTest getTest(){
      return   microTestService.getTest();
    }


    @PutMapping("/test/lcn")
    public Boolean testLcn(){
       return microTestService.testLcn();
    }

}
