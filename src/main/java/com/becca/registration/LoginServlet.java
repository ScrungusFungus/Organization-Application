package com.becca.registration; 

 

import jakarta.servlet.RequestDispatcher; 

import jakarta.servlet.ServletException; 

import jakarta.servlet.annotation.WebServlet; 

import jakarta.servlet.http.HttpServlet; 

import jakarta.servlet.http.HttpServletRequest; 

import jakarta.servlet.http.HttpServletResponse; 

import jakarta.servlet.http.HttpSession; 

 

import java.io.IOException; 

import java.sql.Connection; 

import java.sql.PreparedStatement; 

import java.sql.ResultSet; 

import java.sql.SQLException; 

 

@WebServlet("/login") 

public class LoginServlet extends HttpServlet { 

    private static final long serialVersionUID = 1L; 

 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

        String uemail = request.getParameter("username"); 

        String upwd = request.getParameter("password"); 

        HttpSession session = request.getSession(); 

        RequestDispatcher dispatcher = null; 

 

        Connection con = null; 

        PreparedStatement pst = null; 

        ResultSet rs = null; 

 

        try { 

            con = DatabaseUtil.getConnection(); 

            pst = con.prepareStatement("SELECT * FROM users WHERE uemail = ? AND upwd = ?"); 

            pst.setString(1, uemail); 

            pst.setString(2, upwd); 

 

            rs = pst.executeQuery(); 

            // if user already exists in database 

            if (rs.next()) { 

                session.setAttribute("name", rs.getString("uname")); 

                dispatcher = request.getRequestDispatcher("index.jsp"); 

            } else { 

                request.setAttribute("status", "failed"); 

                dispatcher = request.getRequestDispatcher("login.jsp"); 

            } 

            dispatcher.forward(request, response); 

 

        } catch (SQLException e) { 

            e.printStackTrace(); 

        } finally { 

            DatabaseUtil.close(con, pst, rs); 

        } 

    } 

} 
