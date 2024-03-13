package presentation.View;

import javax.swing.*;
import java.awt.*;

public class MainFrameView   {
    JFrame frame1=new JFrame();
    private JLabel title=new JLabel("Orders Management");

    //Buttons
    private JButton clientButton=new JButton("Client Opperations");
    private JButton productButton=new JButton("Product Opperations");
    private JButton orderButton =new JButton("Product Orders");


    public MainFrameView(){
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(500, 450);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.getContentPane().setBackground(Color.orange);
        //Title
        title.setBounds(160,0,400,80);
        Font font=title.getFont();
        font=font.deriveFont(20.0f);
        title.setFont(font);

        //Buttons
        clientButton.setBounds(170,100,170,50);
        productButton.setBounds(170,200,170,50);
        orderButton.setBounds(170,300,170,50);

        frame1.add(title);
        frame1.add(clientButton);
        frame1.add(productButton);
        frame1.add(orderButton);

    }

    public JFrame getFrame1() {
        return frame1;
    }

    public void setFrame1(JFrame frame1) {
        this.frame1 = frame1;
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public JButton getClientButton() {
        return clientButton;
    }

    public void setClientButton(JButton clientButton) {
        this.clientButton = clientButton;
    }

    public JButton getProductButton() {
        return productButton;
    }

    public void setProductButton(JButton productButton) {
        this.productButton = productButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public void setOrderButton(JButton orderButton) {
        this.orderButton = orderButton;
    }
}
