package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
//I certify that this assignment is entirely my own work, performed independently and without any help from the sources which are not allowed.

public class Servlet1 extends HttpServlet {
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                   
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        try {
            Connection con = AppCommon.createConnection();
            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(
              "SELECT IMSI,ACTIVATION_DATE,CUSTOMER_NUMBER,NET_AMOUNT,GROSS_AMOUNT,CURRENT_USAGE FROM INVOICES");
          
            out.println("<table border=\"1\" style=\"width:100%\">\n");
            out.println("<tr><th>IMSI</th><th>ACTIVATION_DATE</th><th>CUSTOMER_NUMBER</th><th>NET_AMOUNT</th><th>GROSS_AMOUNT</th>"+
                        "<th>CURRENT_USAGE</th></tr>\n");
            while (rs.next()) {
                out.println("<tr> <td>" 
                            + rs.getString("IMSI") + " </td><td>" 
                            + rs.getString("ACTIVATION_DATE") + "</td><td>"
                            + rs.getString("CUSTOMER_NUMBER") + "</td><td>"
                            + rs.getString("NET_AMOUNT") + "</td><td>"
                            + rs.getString("GROSS_AMOUNT") + "</td><td>"
                            + rs.getString("CURRENT_USAGE") + "</td></tr>");
            }
            out.println("</table>");
            statement.close();
            rs.close();
            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
    }


}

