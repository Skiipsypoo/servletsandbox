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
        //PreparedStatment er hvor vi gjør en sql query klar, viktig å bruke PreparedStatement for sikkerhet
        PreparedStatement stmt = null;
        try {
            //Stringen er en sql query som legger til en verdi av textFelt inn i eksempeldatabasen,
            //koden til databasen finner du over textfunksjon classen
            String sql = "Insert into eksempel.texts(text_felt) value(?) ";
            // Her setter vi opp en tilkobling til databasen, den lagrer vi en Prepared statment og er den som kommer
            //til og utføre koden senere
            stmt = conn.prepareStatement(sql);
            //Her sett man verdien på hvilket felt som skal bli brukt, nå har vi bare et felt i koden,
            //men jo flere man har jo mer linjer med setString/Int må du skrive
            stmt.setString(1, textFelt);
            //Her utfører man queryen
            stmt.executeUpdate();
            //Her skriver vi ut hva som er blitt skrevet inn
            out.println("<p>"+ textFelt + " er hva du har skrevet inn i databasen</p>");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Under lukker vi både PrintWriter og Connection slik at vi ikke er koblet til databasen
            if(out != null){
                out.close();
            }
            if(conn != null){
                conn.close();
            }
        }

    };
}
