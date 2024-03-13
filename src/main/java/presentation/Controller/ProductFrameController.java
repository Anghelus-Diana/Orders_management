package presentation.Controller;

import businessLogic.ProductBLL;
import model.Product;
import presentation.View.ProductFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class ProductFrameController extends ProductFrameView implements ActionListener {

    public ProductFrameController()
    {
        addProduct.addActionListener(this);
        editProduct.addActionListener(this);
        deleteProduct.addActionListener(this);
        viewProduct.addActionListener(this);
        backToProduct.addActionListener(this);
    }

    private void clearAddLabels()
    {
        addProductStock.setText("");
        addProductName.setText("");
        addProductPrice.setText("");
    }

    private void clearEditLabels()
    {
        editProductStock.setText("");
        editProductName.setText("");
        editProductPrice.setText("");
        editProductId.setText("");
    }

    private void addNewProducts()
    {
        Product c1=new Product(addProductName.getText(),Integer.parseInt(addProductStock.getText()),Integer.parseInt(addProductPrice.getText()));
        System.out.println("Pretul este:"+Integer.parseInt(addProductPrice.getText())+"\n");
        ProductBLL productBll = new ProductBLL();
        productBll.insertProduct(c1);
        clearAddLabels();
        JOptionPane.showMessageDialog(null, "Produsul a fost introdus cu succes!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        repaintFrame();
    }
    private void editExistingProducts()
    {
        int id=Integer.parseInt(editProductId.getText());
        ProductBLL productBll = new ProductBLL();
        // Generate error
        try {
            Product c2=productBll.findProductById(id);
            Product c3=new Product(id,editProductName.getText(),Integer.parseInt(editProductStock.getText()),Integer.parseInt(editProductPrice.getText()));
            productBll.editInsertStudent(c3);
            clearEditLabels();
            JOptionPane.showMessageDialog(null, "Produsul a fost editat cu succes!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            repaintFrame();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "The produs with id="+id+" was not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteSelectedProducts()
    {
        String productNameToDelete= dropdownButton.getItemAt(dropdownButton.getSelectedIndex());
        try{
            ProductBLL productToDelete=new ProductBLL();
            productToDelete.deleteStudent(productNameToDelete);
            repaintFrame();

        }catch (Exception ex){
            LOGGER.log(Level.INFO, ex.getMessage());
        }
    }
    private void viewProductsTabel()
    {
        repaintFrameTabel();
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            if(e.getSource()==addProduct)
            {
                addNewProducts();
            }

            if(e.getSource()==editProduct)
            {
                editExistingProducts();
            }

            if(e.getSource()==deleteProduct)
            {
                deleteSelectedProducts();
            }
            if(e.getSource()==viewProduct)
            {
                viewProductsTabel();
            }
            if(e.getSource()==backToProduct)
            {
                repaintFrame();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
