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

@WebServlet("/Delete")
public class Delete extends HttpServlet{
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
                   
            pst = con.prepareStatement("delete from employee where empid=?");
            pst.setString(1, eid);
            row=pst.executeUpdate();
            out.println("<h1 style='color:green'>Delete Successful </h1>");
         }
        catch(ClassNotFoundException ex)
        {
            
        }
        catch(SQLException ex){
            out.println("<h1 style='color:red'>Delete Failed </h1>");
        }
    }
}