package com.ironhack.graphics;

public class GraphicsController {

    private ProductsGraphics prod;
    private String value;

    public ProductsGraphics getProd() {
        return prod;
    }

    public void setProd(ProductsGraphics prod) {
        this.prod = prod;
    }


    public void showWindow(){
        prod.setVisible(true);
    }

    public void closeWindow(){
        prod.dispose();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String a) {
        this.value = a;
        System.out.println(this.value);
    }

    public void getResult(){

    }
}
