package presentation.View;

import businessLogic.ProductBLL;
import model.Product;
import start.ReflectionExample;
import start.Start;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

public class ProductFrameView extends JFrame {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    JFrame frame = new JFrame();
    public JLabel title = new JLabel("Select Product Operation:");

    //Buttons
    public JButton addProduct = new JButton("Add new Product:");
    public JButton editProduct = new JButton("Edit Product:");
    public JButton deleteProduct = new JButton("Delete Product:");
    public JButton viewProduct = new JButton("View all Products:");
    public JButton backToProduct = new JButton("Back");
    public JComboBox<String> dropdownButton;

    //Labels
    public JLabel addProductNameLabel = new JLabel("Name:");
    public JLabel addProductStockLabel = new JLabel("Stock:");
    public JLabel addProductPriceLabel = new JLabel("Price(RON):");

    public JLabel editProductIdLabel = new JLabel("ID:");
    public JLabel editProductNameLabel = new JLabel("Name:");
    public JLabel editProductStockLabel = new JLabel("Stock:");
    public JLabel editProductPriceLabel = new JLabel("Price(RON):");

    //Text Boxes
    public JTextField addProductName = new JTextField();
    public JTextField addProductStock = new JTextField();
    public JTextField addProductPrice = new JTextField();
    public JTextField editProductId = new JTextField();
    public JTextField editProductName = new JTextField();
    public JTextField editProductStock = new JTextField();
    public JTextField editProductPrice = new JTextField();


    //Tabel
    public String[] columnNames = {};
    public Object[][] data = {};
    public JTable productsTabel = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(productsTabel);

    public ProductFrameView() {
        repaintFrame();
    }

    public void repaintFrame() {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.pink);
        //Title
        title.setBounds(50, 0, 400, 80);
        Font font = title.getFont();
        font = font.deriveFont(20.0f);
        title.setFont(font);

        setBoundsInFrame();
        addInFrame();

        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }


    public void setBoundsInFrame() {   //Buttons
        addProduct.setBounds(115, 80, 170, 50);
        editProduct.setBounds(415, 80, 170, 50);
        deleteProduct.setBounds(700, 80, 170, 50);
        viewProduct.setBounds(115, 400, 170, 50);

        ProductBLL productBll = new ProductBLL();
        List<String> names = productBll.listNamesDelete();
        String[] options = names.toArray(new String[0]);
        dropdownButton = new JComboBox<>(options);
        dropdownButton.setBounds(700, 150, 150, 30);

        //Labels
        addProductNameLabel.setBounds(50, 150, 70, 30);
        addProductStockLabel.setBounds(50, 200, 70, 30);
        addProductPriceLabel.setBounds(50, 250, 70, 30);
        editProductIdLabel.setBounds(350, 150, 70, 30);
        editProductNameLabel.setBounds(350, 200, 70, 30);
        editProductStockLabel.setBounds(350, 250, 70, 30);
        editProductPriceLabel.setBounds(350, 300, 70, 30);

        //Text Boxes
        addProductName.setBounds(115, 150, 180, 30);
        addProductStock.setBounds(115, 200, 180, 30);
        addProductPrice.setBounds(115, 250, 180, 30);
        editProductId.setBounds(415, 150, 180, 30);
        editProductName.setBounds(415, 200, 180, 30);
        editProductStock.setBounds(415, 250, 180, 30);
        editProductPrice.setBounds(415, 300, 180, 30);
    }

    public void addInFrame() {
        frame.add(title);
        frame.add(addProduct);
        frame.add(editProduct);
        frame.add(deleteProduct);
        frame.add(viewProduct);

        frame.add(addProductName);
        frame.add(addProductStock);
        frame.add(addProductPrice);
        frame.add(addProductNameLabel);
        frame.add(addProductStockLabel);
        frame.add(addProductPriceLabel);

        frame.add(editProductName);
        frame.add(editProductStock);
        frame.add(editProductId);
        frame.add(editProductPrice);
        frame.add(editProductIdLabel);
        frame.add(editProductNameLabel);
        frame.add(editProductStockLabel);
        frame.add(editProductPriceLabel);
        frame.add(dropdownButton);

    }

    public void repaintFrameTabel() {
        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(950, 500);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.pink);
        //Button
        backToProduct.setBounds(850, 170, 70, 30);
        //Tabel
        productsTabel.setBounds(50, 50, 800, 300);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setSize(800, 300);

        ProductBLL product = new ProductBLL();
        List<Product> listOfProducts = product.findAllProducts();
        productsTabel = ReflectionExample.retrivePropertiesAndValuesForList(listOfProducts);
        scrollPane = new JScrollPane(productsTabel);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setSize(800, 300);

        frame.add(scrollPane);
        frame.add(backToProduct);

        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

}
