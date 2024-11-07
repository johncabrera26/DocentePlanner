package com.metrodora.web;

import com.metrodora.dominio.Aula;
import com.metrodora.service.iAulaService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AulaServlet", urlPatterns = {"/AulaServlet"})
public class AulaServlet extends HttpServlet {

    @Inject

    iAulaService aulaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Aula> aulas = aulaService.listarAulas();

        System.out.println("aula" + aulas);

        request.setAttribute("aula", aulas);

        request.getRequestDispatcher("listadoaulas.jsp").forward(request, response);

    }
}
