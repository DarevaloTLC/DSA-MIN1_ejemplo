package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashMap;


public class Order {
    private String UserID;
    private HashMap<String, Integer> pedido;
    public Order(String id){
        this.UserID=id;
        pedido = new HashMap<>();
    }
    public Order(){

    }
    public HashMap<String, Integer> getPedido(){
        return this.pedido;
    }

    public void setPedido(HashMap<String, Integer> h) {
        this.pedido=h;
    }

    public String getID(){
        return this.UserID;
    }
    public void setID(String i){
        this.UserID = i;
    }
    public void addLP(Integer num, String id){
        pedido.put(id, num);
    }



}
