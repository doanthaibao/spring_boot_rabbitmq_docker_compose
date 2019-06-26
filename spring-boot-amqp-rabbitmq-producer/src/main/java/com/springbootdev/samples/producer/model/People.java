package com.springbootdev.samples.producer.model;

public class People {
    private  String name;
    private  Integer age;
    public  People(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public int getAge(){
        return  this.age;
    }
    public void setAge(Integer age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
