
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditS")
public class EditS extends HttpServlet{
    Connection con;
    PreparedStatement pst;
    ResultSet rr;
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
            String empdj=req.getParameter("empdj");
            String empsal=req.getParameter("empsal");
            
            pst = con.prepareStatement("update employee set empname=?,emp_desig=?,emp_j_date=?,emp_sal=? where empid=?");
            pst.setString(1, empname);
            pst.setString(2, empdesg);
            pst.setString(3, empdj);
            pst.setString(4, empsal);
            pst.setString(5, empid);
            row=pst.executeUpdate();
            out.println("<h1 style='color:green'>Edit Successful </h1>");
         }
        catch(ClassNotFoundException ex)
        {
            
        }
        catch(SQLException ex){
            out.println("<h1 style='color:red'>Edit Failed </h1>");
        }
    }
}
