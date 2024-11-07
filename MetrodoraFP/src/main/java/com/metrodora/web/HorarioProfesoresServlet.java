package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iHorarioProfesorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HorarioProfesoresServlet")
public class HorarioProfesoresServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    @Inject
    private iAsignaturaService asignaturaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la lista de horarios del profesor con los IDs de profesor y asignatura
            List<Horarioprofesor> horarioProfesores = horarioProfesorService.listarHorarioProfesores();
            List<Asignatura> asignaturas = asignaturaService.listarAsignatura(); // Obtener todas las asignaturas

            // Cargar los nombres de los profesores y las asignaturas correspondientes a partir de los IDs
            Map<Integer, Map<String, Object>> profesorMap = new HashMap<>();
            Map<Integer, String> nombreAsignaturaMap = new HashMap<>();

            for (Horarioprofesor horario : horarioProfesores) {
                // Obtener el nombre completo del profesor si el ID no es nulo
                if (horario.getIDProfesor() != null) {
                    Integer profesorId = horario.getIDProfesor().getIDProfesor();
                    String nombreProfesor = horarioProfesorService.obtenerNombreCompletoProfesorPorId(profesorId);

                    Map<String, Object> profesorData = new HashMap<>();
                    profesorData.put("id", profesorId);
                    profesorData.put("nombre", nombreProfesor);

                    profesorMap.put(horario.getIDHorarioProfesor(), profesorData);
                } else {
                    // Establecer un valor predeterminado si el ID del profesor es nulo
                    Map<String, Object> profesorData = new HashMap<>();
                    profesorData.put("id", null);
                    profesorData.put("nombre", "Profesor no definido");

                    profesorMap.put(horario.getIDHorarioProfesor(), profesorData);
                }

                // Obtener el nombre de la asignatura si el ID no es nulo
                if (horario.getIDAsignatura() != null) {
                    String nombreAsignatura = horarioProfesorService.obtenerNombreAsignaturaPorId(horario.getIDAsignatura().getIDAsignatura());
                    nombreAsignaturaMap.put(horario.getIDHorarioProfesor(), nombreAsignatura);
                } else {
                    // Establecer un valor predeterminado si el ID de la asignatura es nulo
                    nombreAsignaturaMap.put(horario.getIDHorarioProfesor(), "Libre");
                }
            }

            // Establecer estos mapas en el ámbito de la solicitud para que estén disponibles para la página JSP
            request.setAttribute("profesorMap", profesorMap);
            request.setAttribute("nombreAsignaturaMap", nombreAsignaturaMap);
            request.setAttribute("asignaturas", asignaturas); // Pasar la lista de asignaturas

            // Crear un mapa para almacenar los horarios por profesor
            Map<Map<String, Object>, List<Horarioprofesor>> horariosPorProfesor = new HashMap<>();

            // Agrupar los horarios por profesor en el mapa
            for (Horarioprofesor horario : horarioProfesores) {
                Map<String, Object> profesorData = profesorMap.getOrDefault(horario.getIDHorarioProfesor(), null);
                List<Horarioprofesor> horariosDelProfesor = horariosPorProfesor.getOrDefault(profesorData, new ArrayList<>());
                horariosDelProfesor.add(horario);
                horariosPorProfesor.put(profesorData, horariosDelProfesor);
            }

            // Establecer el mapa de horarios por profesor en el ámbito de la solicitud
            request.setAttribute("horariosPorProfesor", horariosPorProfesor);

            request.getRequestDispatcher("listadohorarioprofesores.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
