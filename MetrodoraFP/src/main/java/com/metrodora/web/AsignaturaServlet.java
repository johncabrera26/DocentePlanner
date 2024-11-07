package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.service.iAsignaturaService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsignaturaServlet", urlPatterns = {"/AsignaturaServlet"})
public class AsignaturaServlet extends HttpServlet {

    @Inject

    iAsignaturaService asignaturaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Asignatura> asignaturas = asignaturaService.listarAsignatura();

        System.out.println("asignatura" + asignaturas);

        request.setAttribute("asignatura", asignaturas);

        request.getRequestDispatcher("registrarProfeAsignatura.jsp").forward(request, response);

    }
}
