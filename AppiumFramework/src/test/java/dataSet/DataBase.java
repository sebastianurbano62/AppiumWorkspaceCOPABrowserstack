package dataSet;

import java.sql.*;

/**
 * Administra las conexiones a la BD
 */
public class DataBase {
    //Obtiene la conexión a la bd
    public  Connection getConnection( ) {

        Connection connection = null;

        try {
            // Cargar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://158.101.23.243:3306/COPAAPP", "usrAutomationApp", "fSdm@G!k7UxhZ");
            System.out.println("Conectado a la base de datos con éxito ");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la BD ");
            e.printStackTrace();
            return null;
        }
    }
}
