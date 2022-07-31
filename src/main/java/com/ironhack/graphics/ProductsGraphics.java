package com.ironhack.graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class ProductsGraphics extends JFrame {

    private JPanel screener;
    private JButton button;
    private JComboBox products;

    private String response;


    public ProductsGraphics(){
        setTitle("Stock Products");

        setBounds(300, 200, 200, 300);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        //PANE
        screener = new JPanel();
        screener.setLayout(null);
        setContentPane(screener);

        JLabel tag = new JLabel("Stock Products");

        tag.setBounds(100, 20, 100, 20);

        screener.add(tag);
        screener.add(products);
        String values [] = {"Box", "Flatbed", "Hybrid"};

        products = new JComboBox(values);
        products.setBounds(150, 20, 150, 20);
        products.addItemListener();

        button = new JButton("Choice");
        button.setBounds(160, 100, 30, 30);
        screener.add(button);

    }

    public JComboBox getProducts(){
        return products;
    }

    public void setProducts(JComboBox products){
        this.products = products;
    }

    public JButton getButton(){
        return button;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void itemGetValue(ItemEvent e){
        if(e.getSource() == products){
            setResponse(products.getSelectedItem().toString());
        }
    }

    ActionListener choices = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "eleccion = " + getResponse());
        }
    }
}
