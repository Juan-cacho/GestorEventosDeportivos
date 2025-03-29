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

        equipos.add(new Equipo(3,
                "Alemania",
                "tenis de mesa",
                "Frankfurt",
                "1965-01-01",
                "https://logo3.png",
                new ArrayList<>()));

        getServletContext().setAttribute("equipoSrv", this);
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

                    List<Jugador> jugadoresEquipo = jugadorSrv.obtenerJugadoresPorEquipo(idEquipo);
                    equipoEncontrado.setJugadores(jugadoresEquipo);

                    out.print(gson.toJson(equipoEncontrado));

                } else {

                    out.print("{\"error\":\"Equipo no encontrado\"}");
                }
            } catch (NumberFormatException e) {

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

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Equipo NuevoEquipo = gson.fromJson(reader, Equipo.class);

        // busca si existe el Equipo
        boolean existe = equipos.stream().anyMatch(e -> e.getId() == NuevoEquipo.getId());

        PrintWriter out = resp.getWriter();

        if (existe) {
            out.print("{\"error\":\"El equipo con ID " + NuevoEquipo.getId() + " ya existe\"}");
        } else {
            equipos.add(NuevoEquipo);
            out.print(gson.toJson(NuevoEquipo));
        }

        out.flush();
        out.close();

    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();

        // Obtener el ID del Equipo desde de la URL
        String idParam = req.getParameter("id");

        if (idParam == null) {

            out.print("{\"error\":\"Debe proporcionar un ID\"}");
            out.flush();
            out.close();
            return;
        }

        int idEqui;

        try {
            idEqui = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {

            out.print("{\"error\":\"ID inválido\"}");
            out.flush();
            out.close();
            return;
        }

        // Buscar al jugador en la lista
        Equipo EquipoExistente = null;
        for (Equipo e : equipos) {
            if (e.getId() == idEqui) {
                EquipoExistente = e;
                break;
            }
        }

        if (EquipoExistente == null) {
            out.print("{\"error\":\"Jugador no encontrado\"}");
            out.flush();
            out.close();
            return;
        }

        //Lee la peticion
        BufferedReader reader = req.getReader();
        Equipo EquipoActualizado = gson.fromJson(reader, Equipo.class);


        EquipoExistente.setNombre(EquipoActualizado.getNombre());
        EquipoExistente.setDeporte(EquipoActualizado.getDeporte());
        EquipoExistente.setCiudad(EquipoActualizado.getCiudad());
        EquipoExistente.setFechaFun(EquipoActualizado.getFechaFun());
        EquipoExistente.setLogo(EquipoActualizado.getLogo());
        EquipoExistente.setJugadores(EquipoActualizado.getJugadores());


        out.print(gson.toJson(EquipoExistente));

        out.flush();
        out.close();
    }








    public List<Equipo> obtenerEquiposPorDeporte(String deporte) {
        return equipos.stream()
                .filter(e -> e.getDeporte().equalsIgnoreCase(deporte))
                .collect(Collectors.toList());
    }

    public List<Equipo> obtenerEquiposPorIds(List<Integer> ids) {
        return equipos.stream()
                .filter(e -> ids.contains(e.getId()))
                .collect(Collectors.toList());
    }


}

/* comentario para probar git */