package com.example.demo;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "helloWorld" , eager = true)
@RequestScoped
public class HelloWorld {

    @ManagedProperty(value = "#{message}")
    private Message messageBean;
    private String message;
    public HelloWorld(){
        System.out.println("Hellowrold started!");
    }

    public String getMessage() {

        if(messageBean != null) {
            message = messageBean.getMessage();
        }
        return message;
    }

    public void setMessageBean(Message message) {
        this.messageBean = message;
    }

    public String secondMessage(){
        return "second messaage";
    }
}
