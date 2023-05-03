package com.example.javaspringbootstudy.model;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient(){
        System.out.println("[NetworkClient] 생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("[NetworkClient] connect: " + url);
    }

    public void call(String message){
        System.out.println("[NetworkClient] call: " + url + " message = " + message);
    }

    public void disConnect() {
        System.out.println("[NetworkClient] close + " + url);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("[NetworkClient] 초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        disConnect();
    }
}
