package com.example.javaspringbootstudy.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClientWithAnnotation {
    private String url;

    public NetworkClientWithAnnotation(){
        System.out.println("[NetworkClientWithAnnotation] 생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("[NetworkClientWithAnnotation] connect: " + url);
    }

    public void call(String message){
        System.out.println("[NetworkClientWithAnnotation] call: " + url + " message = " + message);
    }

    public void disConnect() {
        System.out.println("[NetworkClientWithAnnotation] close + " + url);
    }
    
    @PostConstruct
    public void init(){
        connect();
        call("[NetworkClientWithAnnotation] 초기화 연결 메시지");
    }
    
    @PreDestroy
    public void close() {
        disConnect();
    }
}
