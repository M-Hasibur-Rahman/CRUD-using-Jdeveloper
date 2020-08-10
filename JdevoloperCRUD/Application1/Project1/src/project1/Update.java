package project1;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.sql.DataSource;

//I certify that this assignment is entirely my own work, performed independently and without any help from the sources which are not allowed.

public class Update extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1250";

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
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement("Update Invoices SET  ACTIVATION_DATE = ?, CUSTOMER_NUMBER = ?, " +
                "NET_AMOUNT = ?, GROSS_AMOUNT = ?, CURRENT_USAGE = ? WHERE IMSI = ?");
            ps.setDate(1, AppCommon.parseDate(request.getParameter("ActivationDate")));
            ps.setInt(2, AppCommon.parseInteger(request.getParameter("CustomerNumber")));
            ps.setDouble(3, AppCommon.parseDouble(request.getParameter("NetAmount")));
            ps.setDouble(4, AppCommon.parseDouble(request.getParameter("GrossAmount")));
            ps.setInt(5, AppCommon.parseInteger(request.getParameter("CurrentUsage")));
            ps.setInt(6,AppCommon.parseInteger(request.getParameter("Imsi")));
            try {
            ps.executeUpdate();
                } catch (SQLException e) {
                out.println("<p> Invoice was not updated!  </p>");
                out.println("<p> " + e.getMessage() + " </p>");
                con.commit();
                con.close();
                return;
            }
            out.println("<a href=\"/Application1-Project1-context-root/servlet1\"<p> Updated!</p></a>");
            
            con.commit();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
        out.close();
        }
}
