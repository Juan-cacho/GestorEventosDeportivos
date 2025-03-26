package co.edu.poli.ces3.taller1_ces.servlet;

import co.edu.poli.ces3.taller1_ces.dao.Equipo;
import co.edu.poli.ces3.taller1_ces.dao.Evento;
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
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "eventoSrv", value = "/evento")
public class EventoSrv extends HttpServlet {


    private List<Evento> eventos;
    private EquipoSrv equipoSrv;

    @Override
    public void init() throws ServletException {
        eventos = new ArrayList<>();
        equipoSrv = (EquipoSrv) getServletContext().getAttribute("equipoSrv");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        if (req.getParameter("id") == null) {
            // 🔹 Si no se proporciona ID, devolver todos los eventos
            out.print(gson.toJson(eventos));
        } else {
            try {
                int idEvento = Integer.parseInt(req.getParameter("id"));
                Evento eventoEncontrado = eventos.stream()
                        .filter(e -> e.getId().equals(idEvento))
                        .findFirst()
                        .orElse(null);

                if (eventoEncontrado != null) {
                    // 🔹 Obtener los equipos que participan en el evento
                    List<Equipo> equiposDelEvento = equipoSrv.obtenerEquiposPorIds(eventoEncontrado.getEquipos());
                    Map<String, Object> respuesta = new HashMap<>();
                    respuesta.put("evento", eventoEncontrado);
                    respuesta.put("equipos", equiposDelEvento);

                    out.print(gson.toJson(respuesta));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Evento no encontrado\"}");
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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        // Leer el JSON del cuerpo de la solicitud
        BufferedReader reader = req.getReader();
        Evento nuevoEvento = gson.fromJson(reader, Evento.class);


        List<Equipo> equiposDelEvento = equipoSrv.obtenerEquiposPorDeporte(nuevoEvento.getDeporte());


        if (equiposDelEvento.size() < 2) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Se necesitan al menos 2 equipos para crear un evento\"}");
            out.flush();
            out.close();
            return;
        }

        // 🔹 Agregar IDs de los equipos participantes
        List<Integer> idsEquipos = new ArrayList<>();
        for (Equipo equipo : equiposDelEvento) {
            idsEquipos.add(equipo.getId());
        }
        nuevoEvento.setEquipos(idsEquipos);

        eventos.add(nuevoEvento);

        out.print(gson.toJson(nuevoEvento));
        out.flush();
        out.close();

    }




}

/* comentario para probar git */