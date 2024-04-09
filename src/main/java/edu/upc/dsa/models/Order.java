package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.HashMap;


public class Order {
    private String UserID;
    private HashMap<String, Integer> pedido; //CONVERTIR HASHMAP A UN VECTOR UTILIZANDO UNA CLASE LINEA DE PRODUCTOS
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


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }
}
