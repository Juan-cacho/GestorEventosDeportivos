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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "jugadorSrv", value = "/jugador")
public class JugadorSrv extends HttpServlet {

    private ArrayList<Jugador> jugadores;

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("jugadorSrv", this);
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
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        Gson gson = new Gson();

        if(req.getParameter("id") == null) {
            out.print(gson.toJson(jugadores));
        }else{
            int idjugador = Integer.parseInt(req.getParameter("id"));
            Jugador buscarJugador = null;
            for (Jugador x : jugadores) {
                if (x.getId() != null && x.getId().equals(idjugador)) {
                    buscarJugador = x;
                    break;
                }
            }

            if (buscarJugador != null) {
                out.print(gson.toJson(buscarJugador));
            } else {
                out.print("{\"error\":\"No hay jugador\"}");
            }
        }
        out.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader reader = req.getReader();
        Gson gson = new Gson();
        Jugador nuevoJugador = gson.fromJson(reader, Jugador.class);

        // busca si existe el jugador dentro de los jugadores
        boolean existe = jugadores.stream().anyMatch(j -> j.getId() == nuevoJugador.getId());

        PrintWriter out = resp.getWriter();

        if (existe) {
            out.print("{\"error\":\"El jugador con ID " + nuevoJugador.getId() + " ya existe\"}");
        } else {
            jugadores.add(nuevoJugador);
            out.print(gson.toJson(nuevoJugador));
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

        // Obtener el ID del jugador desde de la URL
        String idParam = req.getParameter("id");

        if (idParam == null) {

            out.print("{\"error\":\"Debe proporcionar un ID\"}");
            out.flush();
            out.close();
            return;
        }

        int idJugador;

        try {
            idJugador = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {

            out.print("{\"error\":\"ID inválido\"}");
            out.flush();
            out.close();
            return;
        }

        // Buscar al jugador en la lista
        Jugador jugadorExistente = null;
        for (Jugador j : jugadores) {
            if (j.getId() == idJugador) {
                jugadorExistente = j;
                break;
            }
        }

        if (jugadorExistente == null) {
            out.print("{\"error\":\"Jugador no encontrado\"}");
            out.flush();
            out.close();
            return;
        }

        //Lee la peticion
        BufferedReader reader = req.getReader();
        Jugador jugadorActualizado = gson.fromJson(reader, Jugador.class);


        jugadorExistente.setNombre(jugadorActualizado.getNombre());
        jugadorExistente.setApellido(jugadorActualizado.getApellido());
        jugadorExistente.setFechaNacimiento(jugadorActualizado.getFechaNacimiento());
        jugadorExistente.setNacionalidad(jugadorActualizado.getNacionalidad());
        jugadorExistente.setPosicion(jugadorActualizado.getPosicion());
        jugadorExistente.setPeto(jugadorActualizado.getPeto());
        jugadorExistente.setEquipoId(jugadorActualizado.getEquipoId());
        jugadorExistente.setEstado(jugadorActualizado.isEstado());



        out.print(gson.toJson(jugadorExistente));

        out.flush();
        out.close();
    }






    public List<Jugador> obtenerJugadoresPorEquipo(int equipoId) {
        return jugadores.stream()
                .filter(j -> j.getEquipoId() == equipoId)
                .collect(Collectors.toList());
    }

}



/* comentario para probar git */