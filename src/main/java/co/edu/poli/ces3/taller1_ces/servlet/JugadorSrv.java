package co.edu.poli.ces3.taller1_ces.servlet;

import co.edu.poli.ces3.taller1_ces.dao.Jugador;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "jugadorSrv", value = "/jugador")
public class JugadorSrv extends HttpServlet {

    private ArrayList<Jugador> jugadores;

    @Override
    public void init() throws ServletException {
        System.out.println("Init!!!!!");
        Jugador j = new Jugador();
        this.jugadores = new ArrayList<>();

        this.jugadores.add(new Jugador(
                1,
                "Xu",
                "Xin",
                "marzo 1",
                "chino",
                "ataque",
                10,
                1,
                true
        ));

        this.jugadores.add(new Jugador(
                2,
                "Ma",
                "Long",
                "marzo 2",
                "chino",
                "ataque",
                1,
                1,
                true
        ));

        this.jugadores.add(new Jugador(
                3,
                "Hugo",
                "Calderano",
                "marzo 1",
                "Brazil",
                "ataque",
                10,
                2,
                true
        ));

        this.jugadores.add(new Jugador(
                4,
                "Zhang",
                "Jike",
                "marzo 1",
                "chino",
                "ataque",
                1,
                1,
                false
        ));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");


        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();

        if(req.getParameter("id") == null) {
            out.print(gson.toJson(jugadores));
        }else{
            int idStudent = Integer.parseInt(req.getParameter("id"));
            Jugador buscarJugador = null;
            for (Jugador x : jugadores) {
                if (x.getId() != null && x.getId().equals(idStudent)) {
                    buscarJugador = x;
                    break;
                }
            }

            if (buscarJugador != null) {
                out.print(gson.toJson(buscarJugador));
            } else {
                out.print("{\"error\":\"Estudiante no encontrado\"}");
            }
        }
        out.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject studentJson = this.getParamsFromBody(req);
    }



    private JsonObject getParamsFromBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }
}

/* comentario para probar git */