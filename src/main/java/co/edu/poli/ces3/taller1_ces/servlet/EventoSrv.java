package co.edu.poli.ces3.taller1_ces.servlet;

import co.edu.poli.ces3.taller1_ces.dao.Equipo;
import co.edu.poli.ces3.taller1_ces.dao.Evento;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "eventoSrv", value = "/evento")
public class EventoSrv extends HttpServlet {


    private List<Evento> eventos;
    private EquipoSrv equipoSrv;

    @Override
    public void init() throws ServletException {
        eventos = new ArrayList<>();
        equipoSrv = (EquipoSrv) getServletContext().getAttribute("equipoSrv");
        getServletContext().setAttribute("eventoSrv", this);
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

        req.setAttribute("listaEventos", eventos);

        out.flush();
        out.close();

    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
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

        // Agrega los Id de los equipos que hacen parte del evento
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






    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();


        String eventoIdParam = req.getParameter("eventoId");
        String cantidadParam = req.getParameter("cantidad");


        if (eventoIdParam == null || cantidadParam == null) {
            out.print("{\"error\": \"Se requieren los parámetros eventoId y cantidad\"}");
            out.flush();
            return;
        }

        try {
            int eventoId = Integer.parseInt(eventoIdParam);
            int cantidad = Integer.parseInt(cantidadParam);


            Evento eventoEncontrado = null;
            for (Evento evento : eventos) {
                if (evento.getId().equals(eventoId)) {
                    eventoEncontrado = evento;
                    break;
                }
            }


            if (eventoEncontrado == null) {
                out.print("{\"error\": \"Evento no encontrado\"}");
                out.flush();
                return;
            }


            int disponibles = eventoEncontrado.getCapacidad() - eventoEncontrado.getEntradasVendidas();
            if (cantidad > disponibles) {
                out.print("{\"error\": \"No hay suficientes entradas disponibles. Disponibles: " + disponibles + "\"}");
                out.flush();
                return;
            }

            // Actualizar el número de entradas vendidas
            eventoEncontrado.setEntradasVendidas(eventoEncontrado.getEntradasVendidas() + cantidad);

            // Responder con el evento actualizado
            resp.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(eventoEncontrado));

        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Los parámetros eventoId y cantidad deben ser números válidos\"}");
        }

        out.flush();
    }



}

/* comentario para probar git */