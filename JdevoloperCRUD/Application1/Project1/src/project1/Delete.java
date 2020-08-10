package project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
//I certify that this assignment is entirely my own work, performed independently and without any help from the sources which are not allowed.
public class Delete extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        
        try{
            Connection con = AppCommon.createConnection();
            con.setAutoCommit(true);
            PreparedStatement ps =null;
            
            if (AppCommon.CONNECTION_SOURCE == AppCommon.SQL_SERVER_SOURCE) {
                ps = con.prepareStatement("DELETE FROM INVOICES WHERE IMSI = ?");
            }
            
            ps.setInt(1, AppCommon.parseInteger(request.getParameter("Imsi")));
           
            
            try{
                ps.executeUpdate();
            }catch(SQLException e){
                out.println("<p>Invoice couldn't deleted</p>");
                out.println("<p>" + e.getMessage() + "</p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<a href=\"/Application1-Project1-context-root/servlet1\"<p> Deleted row!</p></a>");
            
            
            con.commit();
            con.close();
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        out.println("</body></html>");
        out.close();
    }
}
