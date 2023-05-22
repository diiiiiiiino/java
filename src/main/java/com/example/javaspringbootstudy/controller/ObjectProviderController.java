package com.example.javaspringbootstudy.controller;

import com.example.javaspringbootstudy.service.ObjectProviderService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RestController
public class ObjectProviderController {
    private final ObjectProvider<ObjectProviderService> objectProviderService;

    public ObjectProviderController(ObjectProvider<ObjectProviderService> objectProviderService) {
        this.objectProviderService = objectProviderService;
        System.out.println("ObjectProviderController constructor" + this);
    }

    @GetMapping("/provider")
    public String prototype(){
        ObjectProviderService service = objectProviderService.getObject();
        return "ok";
    }

    @PostConstruct
    public void init(){
        System.out.println("ObjectProviderController init()!! : " + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("ObjectProviderController destroy()!! : " + this);
    }
}
