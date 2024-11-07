package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Horarioprofesor;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iHorarioProfesorService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarHorarioFormularioServlet", urlPatterns = {"/EditarHorarioFormularioServlet"})
public class EditarHorarioFormularioServlet extends HttpServlet {

    @Inject
    private iAsignaturaService asignaturaService;

    @Inject
    private iHorarioProfesorService horarioProfesorService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idHorario = Integer.parseInt(request.getParameter("idHorario"));

            // Obtener el horario y la lista de asignaturas
            Horarioprofesor horario = horarioProfesorService.findById(idHorario);
            List<Asignatura> asignaturas = asignaturaService.listarAsignatura();

            // Establecer atributos en la solicitud
            request.setAttribute("horario", horario);
            request.setAttribute("asignaturas", asignaturas);

            // Redirigir a la página de edición
            request.getRequestDispatcher("EditarHorario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
