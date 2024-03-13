package start;
import java.sql.SQLException;
import java.util.logging.Logger;
import presentation.Controller.MainFrameController;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Metoda de intrare în aplicatia Java.
     *
     * @param args argumentele liniei de comanda. Aceasta metoda nu le foloseste.
     */
    public static void main(String[] args) throws SQLException {

        MainFrameController frame=new MainFrameController();
    }
}
