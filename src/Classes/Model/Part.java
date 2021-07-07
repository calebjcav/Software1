package Classes.Model;

import javafx.collections.ObservableList;

abstract public class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

//-------------------

    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min= min;
        this.max = max;
    }
    public void setID(int idNumber){
        id = idNumber;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
    this.price = price;
    }
    public void setStock(int stock){
    this.stock = stock;
    }
    public void setMin(int min){
    this.min = min;
    }
    public void setMax(int max){
    this.max = max;
    }
    public int getID(){
    return this.id;
    }
    public String getName(){
    return this.name;
    }
    public double getPrice(){
    return this.price;
    }
    public int getStock(){
    return this.stock;
    }
    public int getMin(){
    return this.min;
    }
    public int getMax(){
    return this.max;
    }

    public static String isValidPart(String name, int min, int max, int inv, double price, String error){
        if(name == null){
            error = error +"Name field is required.";
        }
        else if(inv < 1){
            error = error + "The inventory number cannot be less than 1.";
        }
        else if(price <= 0){
            error = error + "Price cannot be less than or equal to $0";
        }
        else if(min > max){
            error = error + "The minimum cannot be greater than the maximum";
        }
        else if(inv < min || inv > max){
            error = error + "The inventory must be between min and max";
        }
        return error ;
    }
}
