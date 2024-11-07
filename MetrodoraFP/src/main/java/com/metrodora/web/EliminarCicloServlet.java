package com.metrodora.web;

import com.metrodora.dominio.Asignatura;
import com.metrodora.service.iAsignaturaService;
import com.metrodora.service.iCicloService;
import com.metrodora.service.iHorarioAsignaturaService;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@WebServlet(name = "EliminarCicloServlet", urlPatterns = {"/EliminarCiclo"})
public class EliminarCicloServlet extends HttpServlet {

    @Inject
    private iCicloService cicloService;

    @Inject
    private iAsignaturaService asignaturaService;

    @Inject
    private iHorarioAsignaturaService horarioAsignaturaService;

      // Logger para registrar información y errores
 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cicloIdString = request.getParameter("cicloId");

        if (cicloIdString != null && !cicloIdString.isEmpty()) {
            int cicloId = Integer.parseInt(cicloIdString);

            try {
                // 1. Obtener las asignaturas asociadas al ciclo
                List<Asignatura> asignaturas = asignaturaService.obtenerAsignaturasPorCicloId(cicloId);
                LOGGER.log(Level.INFO, "Asignaturas obtenidas para el cicloId: " + cicloId);

                // 2. Eliminar los horarios de asignaturas asociados al ciclo
                horarioAsignaturaService.eliminarHorariosPorCicloId(cicloId);
                LOGGER.log(Level.INFO, "Horarios eliminados para el cicloId: " + cicloId);

                // 3. Eliminar las asignaturas obtenidas
                for (Asignatura asignatura : asignaturas) {
                    asignaturaService.eliminarAsignaturaPorId(asignatura.getIDAsignatura());
                    LOGGER.log(Level.INFO, "Asignatura eliminada con ID: " + asignatura.getIDAsignatura());
                }

                // 4. Finalmente, eliminar el ciclo
                cicloService.eliminarCicloPorId(cicloId);
                LOGGER.log(Level.INFO, "Ciclo eliminado con ID: " + cicloId);

                response.sendRedirect("ListadoCiclosAsignaturas");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al eliminar el ciclo con ID: " + cicloId, e);
                response.sendRedirect("error.jsp");
            }
        } else {
            LOGGER.log(Level.WARNING, "ID del ciclo no proporcionado o vacío.");
            response.sendRedirect("error.jsp");
        }
    }
}