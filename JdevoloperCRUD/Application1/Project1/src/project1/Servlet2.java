package project1;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.DataSource;
//I certify that this assignment is entirely my own work, performed independently and without any help from the sources which are not allowed.
public class Servlet2 extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Servlet2</title></head>");
        try {
            Connection con = AppCommon.createConnection();
            Statement statement = con.createStatement();

            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM INVOICES WHERE ACTIVATION_DATE >= ? AND ACTIVATION_DATE <= ? AND NET_AMOUNT >= ? AND NET_AMOUNT <= ?");
            ps.setDate(1, AppCommon.parseDate(request.getParameter("invoicedatefrom")));
            ps.setDate(2, AppCommon.parseDate(request.getParameter("invoicedateto")));
            ps.setInt(3, AppCommon.parseInteger(request.getParameter("minnetamount")));
            ps.setInt(4, AppCommon.parseInteger(request.getParameter("maxnetamount")));

            ResultSet rs = ps.executeQuery();

            out.println("<p><u>FILTER ROWS</u></p>");
            out.println("<table border=\"1\" style=\"width:100%;\">\n");
            out.println("<tr><th>IMSI</th><th>ACTIVATION_DATE</th><th>CUSTOMER_NUMBER</th><th>NET_AMOUNT</th><th>GROSS_AMOUNT</th>" +
                        "<th>CURRENT_USAGE</th></tr>\n");
            while (rs.next()) {
                out.println("<tr> <td>" + rs.getString("IMSI") + " </td><td>" + rs.getString("ACTIVATION_DATE") +
                            "</td><td>" + rs.getString("CUSTOMER_NUMBER") + "</td><td>" + rs.getString("NET_AMOUNT") +
                            "</td><td>" + rs.getString("GROSS_AMOUNT") + "</td><td>" + rs.getString("CURRENT_USAGE") + "" 
                                           + "</td><td> <a href=\"/Application1-Project1-context-root/servlet3" 
                                           + "?Imsi=" + rs.getString("IMSI")
                                           + "&ActivationDate=" + rs.getString("ACTIVATION_DATE")
                                           + "&CustomerNumber=" + rs.getString("CUSTOMER_NUMBER")
                                           + "&NetAmount=" + rs.getString("NET_AMOUNT")
                                           + "&GrossAmount=" + rs.getString("GROSS_AMOUNT")
                                           + "&CurrentUsage=" + rs.getString("CURRENT_USAGE")
                                           + "\">INSERT</a>"
                                           + " | "
                                           + "<a href=\"/Application1-Project1-context-root/servlet5" 
                                           + "?Imsi=" + rs.getString("IMSI")
                                           + "&ActivationDate=" + rs.getString("ACTIVATION_DATE")
                                           + "&CustomerNumber=" + rs.getString("CUSTOMER_NUMBER")
                                           + "&NetAmount=" + rs.getString("NET_AMOUNT")
                                           + "&GrossAmount=" + rs.getString("GROSS_AMOUNT")
                                           + "&CurrentUsage=" + rs.getString("CURRENT_USAGE")
                                           + "\">UPDATE</a>"+ " | "
                                           + "<a href=\"/Application1-Project1-context-root/delete" 
                                           + "?Imsi=" + rs.getString("IMSI")
                                           + "&ActivationDate=" + rs.getString("ACTIVATION_DATE")
                                           + "&CustomerNumber=" + rs.getString("CUSTOMER_NUMBER")
                                           + "&NetAmount=" + rs.getString("NET_AMOUNT")
                                           + "&GrossAmount=" + rs.getString("GROSS_AMOUNT")
                                           + "&CurrentUsage=" + rs.getString("CURRENT_USAGE")
                                           + "\">DELETE</a>"
                            + "</td></tr>"
                            );

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
