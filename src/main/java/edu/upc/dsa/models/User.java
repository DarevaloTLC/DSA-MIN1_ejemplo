package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String ID;
    private String name;
    private String lastname;
    private List<Order> orderList;
    public User(String i, String n, String ln){
        this.ID = i;
        this.name = n;
        this.lastname = ln;
        this.orderList = new ArrayList<>();
    }
    public User()
    {
    }
    public String getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    public String getLastName(){
        return this.lastname;
    }
    public void setID(String id){
        this.ID=id;
    }
    public List<Order> getOrderList(){
        return orderList;
    }
    public void setName(String n){
        this.name=n;
    }
    public void setLastName(String ls){
        this.lastname=ls;
    }
    public void addComanda(Order o){
        this.orderList.add(o);
    }
    public void setOrderList(List<Order> orderList){
        this.orderList = orderList;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
