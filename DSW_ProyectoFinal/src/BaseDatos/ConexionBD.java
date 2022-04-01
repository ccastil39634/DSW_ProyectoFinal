/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;
import java.sql.*;

/**
 *
 * @author user
 */
public class ConexionBD {
    final static String USER = "root";
    final static String PASSWORD = "pibHBHH2TA";
    final static String URL = "jdbc:mysql://localhost:3306/casosabiertos";
    
    public static Connection getConnection() throws SQLException{
        
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
