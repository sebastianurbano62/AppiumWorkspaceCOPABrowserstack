package iOS.dataSetiOS;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestiOS {

    public static void main (String[] args) throws SQLException {
        iOS.dataSetiOS.TestDataiOS test = new iOS.dataSetiOS.TestDataiOS();
        ResultSet resultSet;
        resultSet = test.getScenarioIBE(1);


        // Asignar los valores de las columnas a variables o a un objeto
        String userName = resultSet.getString("user");
        System.out.println(userName);
    }
}
