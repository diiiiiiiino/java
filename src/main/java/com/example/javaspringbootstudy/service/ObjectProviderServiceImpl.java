package com.example.javaspringbootstudy.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Scope("prototype")
@Service
public class ObjectProviderServiceImpl implements ObjectProviderService{
    public ObjectProviderServiceImpl() {
        System.out.println("ObjectProviderServiceImpl constructor : " + this);
    }

    @Override
    public void print() {
        System.out.println();
    }

    @PostConstruct
    public void init(){
        System.out.println("ObjectProviderServiceImpl init()!! : " + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println("ObjectProviderServiceImpl destroy()!! : " + this);
    }
}
