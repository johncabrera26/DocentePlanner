package com.metrodora.web;

import com.metrodora.service.iHorarioProfesorService;
import com.metrodora.service.iProfesorService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EliminarProfeAsignatura", urlPatterns = {"/EliminarProfeAsignatura"})
public class EliminarProfeAsignatura extends HttpServlet {

    @Inject
    private iProfesorService profesorService;

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del profesor a eliminar
        String profesorIdString = request.getParameter("profesorId");

        if (profesorIdString != null && !profesorIdString.isEmpty()) {
            int profesorId = Integer.parseInt(profesorIdString);

            // 1. Eliminar los horarios asociados al profesor
            horarioProfesorService.eliminarHorariosPorProfesorId(profesorId);

            // 2. Finalmente, eliminar al profesor
            profesorService.eliminarProfesorPorId(profesorId);

            response.sendRedirect("ListadoProfesoresAsignaturas");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
