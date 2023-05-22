package com.example.javaspringbootstudy.controller;

import com.example.javaspringbootstudy.service.LifeCycleService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Scope("prototype")
@RestController
public class PrototypeController {
    private final LifeCycleService lifeCycleService;

    public PrototypeController(LifeCycleService prototypeServiceImpl) {
        this.lifeCycleService = prototypeServiceImpl;
        System.out.println("PrototypeController constructor" + this);
    }

    @GetMapping("/prototype")
    public String prototype(){

        return "ok";
    }

    @PostConstruct
    public void init(){
        System.out.println("PrototypeController init()!! : " + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("PrototypeController destroy()!! : " + this);
    }
}
