package presentation.View;

import businessLogic.ClientBLL;
import model.Client;
import start.ReflectionExample;
import start.Start;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

public class ClientFrameView {

    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    JFrame frame2 = new JFrame();
    public JLabel title = new JLabel("Select Client Operation:");

    public JButton addClient = new JButton("Add new client:");
    public JButton editClient = new JButton("Edit client:");
    public JButton deleteClient = new JButton("Delete client:");
    public JButton viewClients = new JButton("View all clients:");

    public JButton backToClients = new JButton("Back");

    //Labels
    public JLabel addClientNameLabel = new JLabel("Name:");
    public JLabel addClientAddressLabel = new JLabel("Address:");
    public JLabel addClientEmailLabel = new JLabel("Email:");
    public JLabel editClientNameLabel = new JLabel("Name:");
    public JLabel editClientAddressLabel = new JLabel("Address:");
    public JLabel editClientEmailLabel = new JLabel("Email:");
    public JLabel editClientIdLabel = new JLabel("ID:");

    //Text Boxes
    public JTextField addClientName = new JTextField();
    public JTextField addClientAddress = new JTextField();
    public JTextField addClientEmail = new JTextField();
    public JTextField editClientName = new JTextField();
    public JTextField editClientAddress = new JTextField();
    public JTextField editClientEmail = new JTextField();
    public JTextField editClientId = new JTextField();
    public JComboBox<String> dropdownButton;

    //Tabel
    String[] columnNames = {};
    Object[][] data = {};

    private JTable clientsTabel = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(clientsTabel);

    public ClientFrameView() {
        repaintFrame();

    }

    public void repaintFrame() {
        frame2.getContentPane().removeAll();
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setVisible(true);
        frame2.setSize(950, 500);
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(Color.cyan);

        //Title
        title.setBounds(50, 0, 400, 80);
        Font font = title.getFont();
        font = font.deriveFont(20.0f);
        title.setFont(font);

        //Buttons
        addClient.setBounds(100, 80, 170, 50);
        editClient.setBounds(400, 80, 170, 50);
        deleteClient.setBounds(700, 80, 170, 50);
        viewClients.setBounds(100, 300, 170, 50);

        ClientBLL clientBll = new ClientBLL();
        List<String> names = clientBll.listNamesDelete();
        String[] options = names.toArray(new String[0]);
        dropdownButton = new JComboBox<>(options);
        dropdownButton.setBounds(700, 150, 150, 30);

        setBoundsInClientFrame();
        addInClientFrame();

        frame2.getContentPane().revalidate();
        frame2.getContentPane().repaint();
    }

    public void setBoundsInClientFrame() {
        //Labels
        addClientNameLabel.setBounds(50, 150, 70, 30);
        addClientEmailLabel.setBounds(50, 200, 70, 30);
        addClientAddressLabel.setBounds(50, 250, 70, 30);

        editClientIdLabel.setBounds(350, 150, 70, 30);
        editClientNameLabel.setBounds(350, 200, 70, 30);
        editClientEmailLabel.setBounds(350, 250, 70, 30);
        editClientAddressLabel.setBounds(350, 300, 70, 30);

        //Text Boxes
        addClientName.setBounds(100, 150, 180, 30);
        addClientEmail.setBounds(100, 200, 180, 30);
        addClientAddress.setBounds(100, 250, 180, 30);

        editClientId.setBounds(400, 150, 180, 30);
        editClientName.setBounds(400, 200, 180, 30);
        editClientEmail.setBounds(400, 250, 180, 30);
        editClientAddress.setBounds(400, 300, 180, 30);
    }

    public void addInClientFrame() {
        frame2.add(title);
        frame2.add(addClient);
        frame2.add(editClient);
        frame2.add(deleteClient);
        frame2.add(viewClients);
        frame2.add(dropdownButton);

        frame2.add(addClientName);
        frame2.add(addClientAddress);
        frame2.add(addClientEmail);
        frame2.add(addClientNameLabel);
        frame2.add(addClientAddressLabel);
        frame2.add(addClientEmailLabel);

        frame2.add(editClientName);
        frame2.add(editClientAddress);
        frame2.add(editClientEmail);
        frame2.add(editClientNameLabel);
        frame2.add(editClientAddressLabel);
        frame2.add(editClientEmailLabel);
        frame2.add(editClientId);
        frame2.add(editClientIdLabel);
    }

    public void repaintFrameTabel() {
        frame2.getContentPane().removeAll();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
        frame2.setSize(950, 500);
        frame2.setLayout(null);
        frame2.getContentPane().setBackground(Color.pink);
        //Button
        backToClients.setBounds(850, 170, 70, 30);
        //Tabel
        clientsTabel.setBounds(50, 50, 800, 300);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setSize(800, 300);

        ClientBLL client = new ClientBLL();
        List<Client> listOfClients = client.findAllClients();
        clientsTabel = ReflectionExample.retrivePropertiesAndValuesForList(listOfClients);
        scrollPane = new JScrollPane(clientsTabel);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setSize(800, 300);

        frame2.add(scrollPane);
        frame2.add(backToClients);

        frame2.getContentPane().revalidate();
        frame2.getContentPane().repaint();
    }

}
