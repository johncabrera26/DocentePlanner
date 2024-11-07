package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.dominio.Ciclo;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iCicloService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CicloServlet", urlPatterns = {"/CicloServlet"})
public class CicloServlet extends HttpServlet {

    @Inject
    private iCicloService cicloService;

    @Inject
    private iAsignaturaService asignaturaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ciclo> ciclos = cicloService.listarCiclos();
        request.setAttribute("ciclos", ciclos);

        List<Asignatura> asignaturas = asignaturaService.listarAsignatura();
        request.setAttribute("asignaturas", asignaturas);

        request.getRequestDispatcher("CrearHorario.jsp").forward(request, response);
    }
}
