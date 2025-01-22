package dataSet;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main (String[] args) throws SQLException {
        TestData test = new TestData();
        ResultSet resultSet;
        resultSet = test.getScenarioIBE(1);


        // Asignar los valores de las columnas a variables o a un objeto
        String userName = resultSet.getString("user"); //llamado de la columna que necesito
        System.out.println(userName);
    }
}
