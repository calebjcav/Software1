package Classes.Model;

import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Inventory {
    static private ObservableList<Part> allParts;
    static private ObservableList<Product> allProducts;
//----------------------

    static public void addPart(Part newPart){
        allParts.add(newPart);
    }
    static public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    static public Part lookupPart(int partId){
        AtomicReference<Part> returnVal = null;
        AtomicBoolean isFound = new AtomicBoolean(false);
        allParts.forEach((part) -> {
            if (part.getID() == partId) {
                isFound.set(true);
                returnVal.set(part);
            }
        });
        if (isFound.get()) {
            return returnVal.get();
        } else {
            System.out.println("No parts found.");
            return null;
        }
    }

    static public Product lookupProduct(int productId){
        AtomicReference<Product> returnVal = null;
        AtomicBoolean isFound = new AtomicBoolean(false);
        allProducts.forEach((product) -> {
            if (product.getId() == productId) {
                isFound.set(true);
                returnVal.set(product);
            }
        });
        if (isFound.get()) {
            return returnVal.get();
        } else {
            System.out.println("No products found.");
            return null;
        }
    }

    static public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> returnList = null;
        AtomicBoolean isFound = new AtomicBoolean(false);
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                isFound.set(true);
                returnList.add(part);

            }
        }
        if (isFound.get()) {
            return returnList;
        } else {
            System.out.println("No products found.");
            return null;
        }
    }

    static public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> returnList = null;
        AtomicBoolean isFound = new AtomicBoolean(false);
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                isFound.set(true);
                returnList.add(product);

            }
        }
        if (isFound.get()) {
            return returnList;
        } else {
            System.out.println("No products found.");
            return null;
        }
    }

    static public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    static public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    static public boolean deletePart(Part selectedPart){
       return  allParts.remove(selectedPart);
    }
    static public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    static public ObservableList<Part> getAllParts(){
        return allParts;
    }
    static public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

