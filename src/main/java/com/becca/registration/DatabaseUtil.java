
package com.becca.registration; 

 

import java.sql.Connection; 

import java.sql.DriverManager; 

import java.sql.PreparedStatement; 

import java.sql.ResultSet; 

import java.sql.SQLException; 

 

public class DatabaseUtil { 

    private static final String URL = "jdbc:mysql://localhost:3306/capstone"; 

    private static final String USER = "root"; 

    private static final String PASSWORD = "root"; 

 

    static { 

        try { 

            Class.forName("com.mysql.cj.jdbc.Driver"); 

        } catch (ClassNotFoundException e) { 

            e.printStackTrace(); 

        } 

    } 

 

    public static Connection getConnection() throws SQLException { 

        return DriverManager.getConnection(URL, USER, PASSWORD); 

    } 

 

    public static void close(Connection con, PreparedStatement pst, ResultSet rs) { 

        try { 

            if (rs != null) { 

                rs.close(); 

            } 

            if (pst != null) { 

                pst.close(); 

            } 

            if (con != null) { 

                con.close(); 

            } 

        } catch (SQLException e) { 

            e.printStackTrace(); 

        } 

    } 

 

    public static void close(Connection con, PreparedStatement pst) { 

        close(con, pst, null); 

    } 

} 
