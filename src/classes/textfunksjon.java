package classes;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Create database eksempel;
//use eksempel;
//Create table texts(
// textFelt varchar(50)
//);
public class textfunksjon {
    public void text(PrintWriter out, Connection conn, String textFelt) throws SQLException {
        PreparedStatement stmt = null;
        try {
            String sql = "Insert into eksempel.texts(text_felt) value(?) ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, textFelt);
            stmt.executeUpdate();
            out.println("<p>"+ textFelt + " er hva du har skrevet inn i databasen</p>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    };
}
