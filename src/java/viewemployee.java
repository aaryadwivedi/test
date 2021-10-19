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

@WebServlet("/viewemployee")
public class viewemployee extends HttpServlet{
    Connection con;
    PreparedStatement pst;
    ResultSet rr;
    int row;  
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lba","root","");
            String sql;
            sql="select * from employee";
            Statement stmt=con.createStatement();
            rr=stmt.executeQuery(sql);
            out.println("<h2 style='color:blue;'>Employee details</h2>");
            out.println("<table cellspacing='0' width='250px' border='1'>");
            out.println("<tr>");
            out.println("<td>Employee ID</td>");
            out.println("<td>Employee Name</td>");
            out.println("<td>Designation</td>");
            out.println("<td>Joining date</td>");
            out.println("<td>Salary</td>");
            out.println("<td>Edit</td>");
            out.println("<td>Delete</td>");
            out.println("</tr>");
            
            while(rr.next())
            {
                out.println("<tr>");
                out.println("<td>"+rr.getString("empid")+"</td>");
                out.println("<td>"+rr.getString("empname")+"</td>");
                out.println("<td>"+rr.getString("emp_desig")+"</td>");
                out.println("<td>"+rr.getString("emp_j_date")+"</td>");
                out.println("<td>"+rr.getString("emp_sal")+"</td>");
                out.println("<td> <a href='Editreturn?id="+rr.getString("empid")+"'>Edit</a></td>");
                out.println("<td> <a href='Delete?id="+rr.getString("empid")+"'>Delete</a></td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
         }
        catch(ClassNotFoundException ex)
        {
            
        }
        catch(SQLException ex){
                out.println("<h1 style='color:red'>Database Failed </h1>");
        }
    }
}