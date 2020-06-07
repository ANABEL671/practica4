package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionbd {
    
     static String driver ="com.mysql.jdbc.Driver";
    static String url ="jdbc:mysql://localhost:3306/bd_blog";
    static String usuario ="root";
    static String password ="";
    
    public Connection conn = null;
    
    public  conexionbd(){
        try {
        Class.forName(driver);
            conn = DriverManager.getConnection (url,usuario,password);
            if (conn !=null){
                System.out.println("conexion ok");
            }
        }
           catch (SQLException ex){
                System.out.println("Error al abrir"+ex.getMessage());
}
        catch (ClassNotFoundException e ){
                System.out.println("Falta especificar driver"+e.getMessage());
        }
       
}
    public Connection conectar()
    {
        return conn;
    }
      public void desconectar (){
          try {
              System.out.println("cerrando la BD");
              conn.close();
          }catch (SQLException ex){
              
              System.out.println("Error sql al cerrar la BD"+ex.getMessage());
          }
      }
    }
