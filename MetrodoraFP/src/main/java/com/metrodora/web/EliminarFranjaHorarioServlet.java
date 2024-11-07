package com.metrodora.web;

import com.metrodora.service.iHorarioProfesorService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EliminarFranjaHorarioServlet", urlPatterns = {"/EliminarFranjaHorarioServlet"})
public class EliminarFranjaHorarioServlet extends HttpServlet {

    @Inject
    iHorarioProfesorService horarioprofesorService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID del horario a eliminar
        int idHorario = Integer.parseInt(request.getParameter("idHorario"));

        // Eliminar el horario usando el servicio
        horarioprofesorService.eliminarHorarioPorId(idHorario);

        // Redirigir de vuelta a la página de horarios de profesores
        response.sendRedirect("HorarioProfesoresServlet");
    }
}
