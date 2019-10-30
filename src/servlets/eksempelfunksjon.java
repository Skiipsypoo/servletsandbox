package servlets;

import classes.dbTool;
import classes.textfunksjon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "eksempelfunksjon", urlPatterns = "/servlets.eksempelfunksjon")
public class eksempelfunksjon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Printwriter trenger vi for å skrive ut html og liggnans til siden
        PrintWriter out = response.getWriter();
        //dbTool er database verktøyet som vi oppretter for å lage en kobling
        dbTool dbtool = new dbTool();
        //Connection conn er tilkobling til databasen, den er veldig viktig at man lukker når man er ferdig med bruken
        Connection conn = dbtool.logIn(out);
        //Her lager vi et objekt av klassen textfunksjon dette er for at vi skal hente funksjonaliteten
        textfunksjon txtFun = new textfunksjon();
        //String textFelt er textfelt input taggen i index, det er her textfelt blir lagret
        String textFelt = request.getParameter("textfelt");
        //String action henter knappens verdi den skal vi bruke for å lage en funksjon
        String action = request.getParameter("action");

        try {
            //Grunnen til at action skal innholde knappfunksjonalitet er for at nettopp den knappen vi vil skal bli brukt
            //Så den sjekker da verdien til input taggen som har action som navn, og sjekker om verdien er knappfunksjonalitet
            if(action.contains("knappfunksjonalitet")){
               //Under kaller vi på metoden text i klassen textfunksjon, den skal så skrive ut endringene som blir gjort i databasen
                txtFun.text(out,conn,textFelt);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
