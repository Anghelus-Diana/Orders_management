package start;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectionExample {
    private static String[] columnNames;
    private static Object[][] rowData;

    /**

     Creează și returnează un obiect JTable pe baza datelor furnizate.
     @param objects O listă de obiecte care reprezintă datele pentru JTable.
     @return Un obiect JTable creat pe baza datelor furnizate.
     */
    public static JTable createTable(List<?> objects) {
        JTable table = new JTable(rowData, columnNames);
        return table;
    }

    /**

     Recuperează proprietățile și valorile corespunzătoare pentru o listă de obiecte și returnează un JTable.Realizeaza tabele de date prin reflexie

     @param objects O listă de obiecte din care se vor recupera proprietățile și valorile.

     @return Un JTable care conține proprietățile și valorile obiectelor.
     */

    public static JTable retrivePropertiesAndValuesForList(List<?> objects) {
        ArrayList<String> fields = new ArrayList<>();
        for (Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                fields.add(field.getName());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        int n = fields.size();
        columnNames = new String[n];
        for (int i = 0; i < n; i++) {
            columnNames[i] = fields.get(i);
        }
        rowData = new Object[objects.size()][n];
        for (int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);
            for (int j = 0; j < n; j++) {
                try {
                    Field field = object.getClass().getDeclaredField(fields.get(j));
                    field.setAccessible(true);
                    Object value = field.get(object);
                    rowData[i][j] = value;
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ReflectionExample.createTable(objects);
    }
}

