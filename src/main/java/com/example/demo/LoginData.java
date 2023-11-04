package com.example.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="loginData", eager = true)
@SessionScoped
public class LoginData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getpassword(){
        return this.password;
    }
    public void setpassword(String password){
        this.password = password;
    }

}
