
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Editreturn")
public class Editreturn extends HttpServlet{
    Connection con;
    PreparedStatement pst;
    ResultSet rr;
    int row;  
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
         String eid=req.getParameter("id");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lba","root","");
            out.println("<h2 style='color:blue;'>Edit employee details</h2>");

            pst=con.prepareStatement("select * from employee where empid=?");
            //out.println("<p>hi</p>");
            pst.setString(1, eid);
            rr=pst.executeQuery();
            while(rr.next())
            {
                out.println("<form action='EditS' method='POST'>");
                out.println("<table>");
                out.println("<tr><td>Emp Id</td><td><input type='number' name='empid' id='empid' value='"+rr.getString("empid")+"'</td></tr>");
                out.println("<tr><td>Emp Name</td><td><input type='text' name='empname' id='empname' value='"+rr.getString("empname")+"'</td></tr>");
                out.println("<tr><td>Designation</td><td><input type='text' name='empdesg' id='empdesg' value='"+rr.getString("emp_desig")+"'</td></tr>");
                out.println("<tr><td>Date of joining</td><td><input type='text' name='empdj' id='empdj' value='"+rr.getString("emp_j_date")+"'</td></tr>");
                out.println("<tr><td>Emp Salary</td><td><input type='number' name='empsal' id='empsal' value='"+rr.getString("emp_sal")+"'</td></tr>");
                out.println("<tr><td colspan='2'><input type='submit' value='Edit'></td></tr>");
                out.println("</table>");
                out.println("</form>");
            }
         }
        catch(ClassNotFoundException ex)
        {
            
        }
        catch(SQLException ex){
            out.println("<h1 style='color:red'>Database Failed </h1>");
        }
    }
}
