package presentation.Controller;

import businessLogic.ClientBLL;
import model.Client;
import presentation.View.ClientFrameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

public class ClientFrameController extends ClientFrameView implements ActionListener {
    public ClientFrameController()
    {
        addClient.addActionListener(this);
        editClient.addActionListener(this);
        deleteClient.addActionListener(this);
        viewClients.addActionListener(this);
        backToClients.addActionListener(this);
    }
    private void clearAddLabels()
    {
        addClientAddress.setText("");
        addClientName.setText("");
        addClientEmail.setText("");
    }

    private void clearEditLabels()
    {
        editClientAddress.setText("");
        editClientName.setText("");
        editClientEmail.setText("");
        editClientId.setText("");
    }

    private void addNewClients()
    {
        Client c1=new Client(addClientName.getText(),addClientAddress.getText(),addClientEmail.getText());
        ClientBLL clientBll = new ClientBLL();
        clientBll.insertStudent(c1);
        clearAddLabels();
        JOptionPane.showMessageDialog(null, "Clientul a fost introdus cu succes!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        repaintFrame();
    }
    private void editExistingClients()
    {
        int id=Integer.parseInt(editClientId.getText());
        ClientBLL clientBll = new ClientBLL();
        // Generate error
        try {
            Client c2=clientBll.findStudentById(id);
            Client c3=new Client(id,editClientName.getText(),editClientAddress.getText(),editClientEmail.getText());
            clientBll.editInsertStudent(c3);
            clearEditLabels();
            JOptionPane.showMessageDialog(null, "Clientul a fost editat cu succes!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            repaintFrame();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "The student with id="+id+" was not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteSelectedClients()
    {
        String clientNameToDelete= dropdownButton.getItemAt(dropdownButton.getSelectedIndex());
        try{
            ClientBLL clientToDelete=new ClientBLL();
            clientToDelete.deleteStudent(clientNameToDelete);
            repaintFrame();

        }catch (Exception ex){
            LOGGER.log(Level.INFO, ex.getMessage());
        }
    }

    private void viewClientsTabel()
    {
        repaintFrameTabel();
    }

    public void actionPerformed(ActionEvent e)
    {
        try{
            if(e.getSource()==addClient)
            {
                addNewClients();
            }

            if(e.getSource()==editClient)
            {
                editExistingClients();
            }

            if(e.getSource()==deleteClient)
            {
                deleteSelectedClients();
            }
            if(e.getSource()==viewClients)
            {
                viewClientsTabel();
            }
            if(e.getSource()==backToClients)
            {
                repaintFrame();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
