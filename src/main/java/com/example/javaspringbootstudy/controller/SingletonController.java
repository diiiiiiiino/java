package com.example.javaspringbootstudy.controller;

import com.example.javaspringbootstudy.service.LifeCycleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@RestController
public class SingletonController {
    private final List<LifeCycleService> lifeCycleService;

    public SingletonController(List<LifeCycleService> lifeCycleService) {
        this.lifeCycleService = lifeCycleService;
        System.out.println("SingletonController constructor : " + this);
    }

    @GetMapping("/singleton")
    public String singleton(){

        return "ok";
    }

    @PostConstruct
    public void init(){
        System.out.println("SingletonController init()!!" + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("SingletonController destroy()!!" + this);
    }
}
