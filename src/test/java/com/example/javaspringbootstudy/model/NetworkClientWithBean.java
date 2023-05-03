package com.example.javaspringbootstudy.model;

public class NetworkClientWithBean {
    private String url;

    public NetworkClientWithBean(){
        System.out.println("[NetworkClientWithBean] 생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("[NetworkClientWithBean] connect: " + url);
    }

    public void call(String message){
        System.out.println("[NetworkClientWithBean] call: " + url + " message = " + message);
    }

    public void disConnect() {
        System.out.println("[NetworkClientWithBean] close + " + url);
    }

    public void init(){
        connect();
        call("[NetworkClientWithBean] 초기화 연결 메시지");
    }

    public void close() {
        disConnect();
    }
}
