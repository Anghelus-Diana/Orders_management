package presentation.View;

import businessLogic.BillBLL;
import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import model.Bill;
import model.Order;
import start.ReflectionExample;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrdersFrameView {
    JFrame frame4 = new JFrame();

    //Label
    public JLabel title = new JLabel("Create a product order:");

    public JLabel selectProductLabel = new JLabel("Select an existing product:");
    public JLabel selectClientlabel = new JLabel("Select an existing client:");
    public JLabel quantityLabel = new JLabel("Insert desired quantity:");
    public JTextField quantityText = new JTextField();

    //Buttons
    public JButton addOrder = new JButton("Add order");
    public JButton viewAllOrders = new JButton("View all orders");
    public JButton viewAllBills = new JButton("View all bills");

    public JButton backToOrders = new JButton("Back");
    public JComboBox<String> dropdownButtonClients;
    public JComboBox<String> dropdownButtonProducts;

    //Tabel Orders
    String[] columnNames = {};
    Object[][] data = {};
    private JTable ordersTabel = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(ordersTabel);

    //Tabel Bill
    String[] columnNamesBill = {};
    Object[][] dataBill = {};
    private JTable billTabel = new JTable(dataBill, columnNamesBill);
    JScrollPane scrollPaneBill = new JScrollPane(billTabel);


    public OrdersFrameView() {
        repaintOrderFrame();
    }

    public void repaintOrderFrame() {
        frame4.getContentPane().removeAll();
        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame4.setSize(900, 500);
        frame4.setLayout(null);
        frame4.setVisible(true);
        frame4.getContentPane().setBackground(Color.orange);

        //DropdownButtons
        ClientBLL clientBll = new ClientBLL();
        List<String> clientsNames = clientBll.listNamesDelete();
        String[] options = clientsNames.toArray(new String[0]);
        dropdownButtonClients = new JComboBox<>(options);
        dropdownButtonClients.setBounds(50, 150, 150, 30);

        ProductBLL productBll = new ProductBLL();
        List<String> productNames = productBll.listNamesDelete();
        String[] options2 = productNames.toArray(new String[0]);
        dropdownButtonProducts = new JComboBox<>(options2);
        dropdownButtonProducts.setBounds(350, 150, 150, 30);
        quantityText.setBounds(650, 150, 150, 30);

        setBoundsInOrdersFrame();
        addInOrderFrame();

        frame4.getContentPane().revalidate();
        frame4.getContentPane().repaint();
    }

    public void addInOrderFrame() {
        frame4.add(selectClientlabel);
        frame4.add(selectProductLabel);
        frame4.add(quantityLabel);
        frame4.add(title);
        frame4.add(addOrder);
        frame4.add(viewAllOrders);
        frame4.add(dropdownButtonClients);
        frame4.add(dropdownButtonProducts);
        frame4.add(quantityText);
        frame4.add(viewAllBills);
    }

    public void setBoundsInOrdersFrame() {
        //Title
        title.setBounds(50, 0, 400, 80);
        Font font = title.getFont();
        font = font.deriveFont(20.0f);
        title.setFont(font);

        //Labels
        selectClientlabel.setBounds(50, 80, 300, 50);
        font = selectClientlabel.getFont();
        font = font.deriveFont(20.0f);
        selectClientlabel.setFont(font);
        selectProductLabel.setBounds(350, 80, 300, 50);
        font = selectProductLabel.getFont();
        font = font.deriveFont(20.0f);
        selectProductLabel.setFont(font);
        quantityLabel.setBounds(650, 80, 300, 50);
        font = quantityLabel.getFont();
        font = font.deriveFont(20.0f);
        quantityLabel.setFont(font);
        //Buttons
        addOrder.setBounds(50, 420, 150, 30);
        viewAllOrders.setBounds(200, 420, 150, 30);
        viewAllBills.setBounds(350, 420, 150, 30);

    }

    public void repaintFrameTabel() {
        frame4.getContentPane().removeAll();
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setVisible(true);
        frame4.setSize(950, 500);
        frame4.setLayout(null);
        frame4.getContentPane().setBackground(Color.pink);
        //Button
        backToOrders.setBounds(850, 170, 70, 30);

        //Tabel
        ordersTabel.setBounds(50, 50, 800, 450);
        scrollPane.setPreferredSize(new Dimension(800, 450));
        scrollPane.setSize(800, 450);

        OrderBLL order = new OrderBLL();
        List<Order> listOfOrders = order.findAllOrders();
        ordersTabel = ReflectionExample.retrivePropertiesAndValuesForList(listOfOrders);
        scrollPane = new JScrollPane(ordersTabel);
        scrollPane.setPreferredSize(new Dimension(800, 450));
        scrollPane.setSize(800, 450);

        frame4.add(scrollPane);
        frame4.add(backToOrders);

        frame4.getContentPane().revalidate();
        frame4.getContentPane().repaint();
    }

    public void repaintBillTabelFrame() {
        frame4.getContentPane().removeAll();
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setVisible(true);
        frame4.setSize(950, 500);
        frame4.setLayout(null);
        frame4.getContentPane().setBackground(Color.pink);
        //Button
        backToOrders.setBounds(850, 170, 70, 30);

        //Tabel
        billTabel.setBounds(50, 50, 800, 300);
        scrollPaneBill.setPreferredSize(new Dimension(800, 300));
        scrollPaneBill.setSize(800, 300);

        BillBLL bill = new BillBLL();
        List<Bill> listOfBill = bill.findAllBills();
        billTabel = ReflectionExample.retrivePropertiesAndValuesForList(listOfBill);
        scrollPaneBill = new JScrollPane(billTabel);
        scrollPaneBill.setPreferredSize(new Dimension(800, 300));
        scrollPaneBill.setSize(800, 300);

        frame4.add(scrollPaneBill);
        frame4.add(backToOrders);


        frame4.getContentPane().revalidate();
        frame4.getContentPane().repaint();
    }

}
