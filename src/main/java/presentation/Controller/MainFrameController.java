package presentation.Controller;

import presentation.View.MainFrameView;
import presentation.View.OrdersFrameView;
import presentation.View.ProductFrameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController  implements ActionListener {

    MainFrameView mainFrame = new MainFrameView();

    public MainFrameController()
    {
        mainFrame.getClientButton().addActionListener(this);
        mainFrame.getProductButton().addActionListener(this);
        mainFrame.getOrderButton().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==mainFrame.getClientButton())
        {
            ClientFrameController frame1=new ClientFrameController();
        }
        if(e.getSource()==mainFrame.getProductButton())
        {
            ProductFrameController frame2=new ProductFrameController();
        }
        if(e.getSource()==mainFrame.getOrderButton())
        {
            OrdersFrameController frame3=new OrdersFrameController();
        }
    }

}
