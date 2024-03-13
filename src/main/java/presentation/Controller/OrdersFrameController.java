package presentation.Controller;

import businessLogic.BillBLL;
import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import dataAccess.BillDAO;
import dataAccess.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;
import presentation.View.OrdersFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersFrameController extends OrdersFrameView implements ActionListener {

    public OrdersFrameController()
    {
      addOrder.addActionListener(this);
      viewAllOrders.addActionListener(this);
      backToOrders.addActionListener(this);
      viewAllBills.addActionListener(this);
    }

    private void addNewOrders()
    {
        Product product;
        ProductDAO selectedProduct=new ProductDAO();
        product=selectedProduct.findByName(dropdownButtonProducts.getItemAt(dropdownButtonProducts.getSelectedIndex()));
        int wantedStock=Integer.parseInt(quantityText.getText());
        if(product.getStock()>wantedStock)
        {   int newStock= product.getStock()-wantedStock;
            Product productUpdated=new Product(product.getId(), dropdownButtonProducts.getItemAt(dropdownButtonProducts.getSelectedIndex()),newStock, product.getPrice());
            System.out.println(productUpdated);
            selectedProduct.editInsert(productUpdated);
        String clientName= dropdownButtonClients.getItemAt(dropdownButtonClients.getSelectedIndex());
        String productName= dropdownButtonProducts.getItemAt(dropdownButtonProducts.getSelectedIndex());
        Order o1=new Order(clientName,productName,Integer.parseInt(quantityText.getText()));
        OrderBLL orderBll = new OrderBLL();
        orderBll.insertOrder(o1);
        generateBill(o1,product);
        repaintOrderFrame();}
        else
            JOptionPane.showMessageDialog(null, "Nu sunt produse suficiente in stock.", "ERROR", JOptionPane.ERROR_MESSAGE);

    }

    private void generateBill(Order order,Product product)
    {
        Bill bill=new Bill(order.getId(),order.getClientName(), order.getProductName(),product.getPrice(),order.getQuantity());
        BillBLL billBLL=new BillBLL();
        billBLL.insert(bill);
        JOptionPane.showMessageDialog(null, "Comanda a fost plasata cu succes!\n"+"Nume destinatar:"+order.getClientName()+"\n"+"Produs comandat:"+order.getProductName()+"\n"+"Pret:"+product.getPrice()+"\n"+"Cantitate:"+order.getQuantity()+"\n", "Bill", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewOrdersTabel()
    {
        repaintFrameTabel();
    }
    private void viewBillsTabel(){repaintBillTabelFrame();}

    public void actionPerformed(ActionEvent e)
    {
        try{
            if(e.getSource()==addOrder)
            {
                addNewOrders();
            }
            if(e.getSource()==viewAllOrders)
            {
                viewOrdersTabel();
            }
            if(e.getSource()==backToOrders)
            {
                repaintOrderFrame();
            }
            if(e.getSource()==viewAllBills)
            {
                viewBillsTabel();
            }

        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
