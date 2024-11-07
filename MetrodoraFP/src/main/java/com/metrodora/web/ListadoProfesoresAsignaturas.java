package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.dominio.Profesor;
import com.metrodora.service.iHorarioProfesorService;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListadoProfesoresAsignaturas", urlPatterns = {"/ListadoProfesoresAsignaturas"})
public class ListadoProfesoresAsignaturas extends HttpServlet {

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener todos los horarios de profesores de la base de datos
        List<Horarioprofesor> horarios = horarioProfesorService.listarHorarioProfesores();

        // Utilizar un mapa para agrupar las asignaturas por profesor y evitar repeticiones
        Map<Profesor, Set<Asignatura>> profesorAsignaturasMap = new HashMap<>();
        for (Horarioprofesor horario : horarios) {
            Profesor profesor = horario.getIDProfesor();
            Asignatura asignatura = horario.getIDAsignatura();
            profesorAsignaturasMap.computeIfAbsent(profesor, k -> new HashSet<>()).add(asignatura);
        }

        // Almacenar los datos en el request como un atributo
        request.setAttribute("profesorAsignaturasMap", profesorAsignaturasMap);

        // Redirigir a un JSP para mostrar los datos
        RequestDispatcher dispatcher = request.getRequestDispatcher("listadoProfesoresAsignaturas.jsp");
        dispatcher.forward(request, response);
    }
}
