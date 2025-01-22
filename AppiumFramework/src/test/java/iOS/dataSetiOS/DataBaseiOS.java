package iOS.dataSetiOS;

import java.sql.*;

/**
 * Administra las conexiones a la BD
 */
public class DataBaseiOS {
    //Obtiene la conexión a la bd
    public  Connection getConnection( ) {

        Connection connection = null;

        try {
            // Cargar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión con la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://158.101.23.243:3306/COPAAPP", "usrAutomationApp", "fSdm@G!k7UxhZ");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la BD ");
            e.printStackTrace();
            return null;
        }
    }
}
