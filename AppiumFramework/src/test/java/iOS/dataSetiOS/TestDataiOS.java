package iOS.dataSetiOS;

import java.sql.*;

public class TestDataiOS {

    public  ResultSet getScenarioIBE (int idScenario){
        DataBaseiOS db = new DataBaseiOS();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        connection = db.getConnection();

        try {

            // Llamar al procedimiento almacenado
            callableStatement = connection.prepareCall("{call getScenarioIBE(?)}");
            callableStatement.setInt(1,idScenario);

            // Ejecutar el procedimiento almacenado y asignar los resultados a un objeto ResultSet
            resultSet = callableStatement.executeQuery();
            resultSet.next();

            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
