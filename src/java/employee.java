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
@WebServlet("/employee")
public class employee extends HttpServlet{
    Connection con;
    PreparedStatement pst;
    int row;  
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException
    {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lba","root","");
            String empid=req.getParameter("empid");
            String empname=req.getParameter("empname");
            String empdesg=req.getParameter("empdesg");
            String empjd=req.getParameter("empjd");
            String empsal=req.getParameter("empsal");
            pst=con.prepareStatement("INSERT INTO employee(empid,empname,emp_desig,emp_j_date,emp_sal)VALUES(?,?,?,?,?)");
            pst.setString(1,empid);
            pst.setString(2,empname);
            pst.setString(3,empdesg);
            pst.setString(4,empjd);
            pst.setString(5,empsal);
            row=pst.executeUpdate();
            
            out.println("<h1 style='color:green'>Record added </h1>");
         }
        catch(ClassNotFoundException ex)
        {
            
        }
        catch(SQLException ex){
            out.println("<h1 style='color:red'>Record Failed </h1>");
        }
    }
}
