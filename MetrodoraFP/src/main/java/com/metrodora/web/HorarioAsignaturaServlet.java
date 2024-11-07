package com.metrodora.web;

import com.metrodora.dominio.Horarioasignatura;
import com.metrodora.service.iHorarioAsignaturaService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HorarioAsignaturaServlet", urlPatterns = {"/HorarioAsignaturaServlet"})
public class HorarioAsignaturaServlet extends HttpServlet {

    @Inject

    iHorarioAsignaturaService horarioAsignaturaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Horarioasignatura> horarioasignaturas = horarioAsignaturaService.listarHorarioAsignaturas();

        System.out.println("horarioasignatura" + horarioasignaturas);

        request.setAttribute("horarioasignatura", horarioasignaturas);

        request.getRequestDispatcher("listadohorarioasignaturas.jsp").forward(request, response);

    }
}
