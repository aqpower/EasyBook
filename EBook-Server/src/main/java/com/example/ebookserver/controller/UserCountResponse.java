package com.example.ebookserver.controller;

/**
 * @author wzb
 */
public class UserCountResponse {
    private int count;
    public UserCountResponse(int count) {
        this.count = count;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count = count;
    }
}
