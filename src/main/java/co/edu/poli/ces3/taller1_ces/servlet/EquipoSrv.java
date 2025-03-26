package co.edu.poli.ces3.taller1_ces.servlet;


import co.edu.poli.ces3.taller1_ces.dao.Equipo;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "equipoSrv", value = "/equipo")
public class EquipoSrv extends HttpServlet {

    private ArrayList<Equipo> equipos;
    private JugadorSrv jugadorSrv;


    @Override
    public void init() throws ServletException {
        equipos = new ArrayList<>();
        jugadorSrv = (JugadorSrv) getServletContext().getAttribute("jugadorSrv");

        equipos.add(new Equipo(1,
                "China",
                "Tenis de mesa",
                "No se sabe",
                "1902-03-06",
                "https://logo.png",
                new ArrayList<>()));

        equipos.add(new Equipo(2,
                "Brazil",
                "tenis de mesa",
                "Rio",
                "1947-01-01",
                "https://logo2.png",
                new ArrayList<>()));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        if (req.getParameter("id") == null) {

            out.print(gson.toJson(equipos));

        } else {
            try {
                int idEquipo = Integer.parseInt(req.getParameter("id"));
                Equipo equipoEncontrado = null;

                for (Equipo e : equipos) {
                    if (e.getId().equals(idEquipo)) {
                        equipoEncontrado = e;
                        break;
                    }
                }

                if (equipoEncontrado != null) {
                    // 🔹 Obtener los jugadores del equipo desde el JugadorServlet
                    List<Jugador> jugadoresEquipo = jugadorSrv.obtenerJugadoresPorEquipo(idEquipo);
                    equipoEncontrado.setJugadores(jugadoresEquipo);

                    // 🔹 Enviar respuesta con el equipo y su lista de jugadores
                    out.print(gson.toJson(equipoEncontrado));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Equipo no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"ID inválido\"}");
            }
        }

        out.flush();
        out.close();

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