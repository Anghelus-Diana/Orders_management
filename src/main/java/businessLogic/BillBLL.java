package businessLogic;

import dataAccess.BillDAO;
import dataAccess.OrderDAO;
import model.Bill;
import model.Order;

import java.util.List;

public class BillBLL {

    public BillBLL() {
    }

    public int insert(Bill bill) {
        return BillDAO.insert(bill);
    }

    public List<Bill> findAllBills() {return BillDAO.findAllBills();}
}
