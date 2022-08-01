package com.ironhack.graphics;

import com.ironhack.ui.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class ProductsGraphics extends JFrame implements ActionListener{

    private JPanel screener;
    private JButton button;
    private JComboBox products;
    public GraphicsController contro;
    private String response;


    public ProductsGraphics(){
        setTitle("Stock Products");

        setBounds(200, 200, 300, 200);

        //PANE
        screener = new JPanel();
        screener.setLayout(null);
        setContentPane(screener);

        JLabel tag = new JLabel("Stock Products");

        tag.setBounds(40, 20, 100, 20);

        String values [] = {" ","Box", "Flatbed", "Hybrid"};

        products = new JComboBox(values);
        products.setBounds(150, 20, 120, 20);

        button = new JButton("Choice");
        button.setBounds(100, 100, 80, 20);

        button.addActionListener(this);
        screener.add(button);
        screener.add(tag);
        screener.add(products);


        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

    public GraphicsController getContro() {
        return contro;
    }

    public void setContro(GraphicsController contro) {
        this.contro = contro;
    }

    public static void main(String [] x){
       // ProductsGraphics prof = new ProductsGraphics();
        GraphicsController graph = new GraphicsController();

        graph.setProd(new ProductsGraphics());
        graph.showWindow();


        System.out.println(graph.getValue());
        String a = graph.getProd().getContro().getValue();

        System.out.println("CON GRAPG" + a);


        //graph.setProd(prof);

        //graph.setValue();
        //graph.showWindow();

       // System.out.println(prof.getResponse());
       // System.out.println(prof.getContro().getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            //JOptionPane.showMessageDialog(null, "Products = " + getResponse());
            if(e.getSource() == button){
                setResponse(products.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Products = " + getResponse());

                setContro(new GraphicsController());
                getContro().setValue(getResponse());

                System.out.println("desde contro"+ getContro().getValue());
            }

    }

}
