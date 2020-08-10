package project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
//I certify that this assignment is entirely my own work, performed independently and without any help from the sources which are not allowed.
public class Servlet3 extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(AppCommon.CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
               out.println("<head><title>Insert</title></head>");
               out.println("<body>");
               out.println("<p><u>INSERTdataform</u></p>");
               out.println("<form name=\"InsertSimilarForm\" action=\"insertdata\" method=\"get\">");
               out.println("<table cellspacing=\"2\" cellpadding=\"3\" border=\"1\" width=\"50%\">");
               
               out.println("</tr><tr><td width=\"50%\">DATE</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"ActivationDate\" value=\"" 
                           + request.getParameter("ActivationDate").substring(0, 10) + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">Customer Number</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"CustomerNumber\" value=\"" 
                           + request.getParameter("CustomerNumber") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">NET AMOUNT</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"NetAmount\" value=\"" 
                           + request.getParameter("NetAmount") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">GROSS AMOUNT</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"GrossAmount\" value=\"" 
                           + request.getParameter("GrossAmount") + "\"/></td>");
               out.println("</tr><tr><td width=\"50%\">Current Usage</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"CurrentUsage\" value=\"" 
                           + request.getParameter("CurrentUsage")  + "\"/></td>");
        out.println("</tr><tr><td width=\"50%\">IMSI</td><td width=\"50%\"><input style=\"width: 100%;\" type=\"text\" name=\"Imsi\" value=\"" 
                    + request.getParameter("Imsi")  + "\"/></td>");

               out.println("</tr><tr><td width=50%>&nbsp;</td><td width=\"50%\"><input type=\"submit\" name=\"Submit\"/></td></tr></table></form>");
               
               out.println("</body></html>");
               out.close();
        
    }
}
