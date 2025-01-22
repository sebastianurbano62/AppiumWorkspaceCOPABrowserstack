package dataSet;

import java.sql.*;

public class TestData {

    public  ResultSet getScenarioIBE (int idScenario){
        DataBase db = new DataBase();
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

    public  ResultSet getScenarioReservasJMeter (int testID){
        DataBase db = new DataBase();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        connection = db.getConnection();

        try {

            // Llamar al procedimiento almacenado
            callableStatement = connection.prepareCall("{call getTestID(?)}");
            callableStatement.setInt(1,testID);

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
