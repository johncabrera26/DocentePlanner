<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Ciclos y Asignaturas</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Estilos adicionales */
            .add-btn {
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Listado de Ciclos y Asignaturas</h1>
            <!-- Botón para añadir -->
            <form action="registrarcicloAsignatura.jsp">
                <button type="submit" class="btn btn-primary add-btn">Añadir Ciclo/Asignatura</button>
            </form>

            <table class="table">
                <thead>
                    <tr>
                        <th>Ciclo</th>
                        <th>Asignaturas</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterar sobre el mapa de ciclos y asignaturas -->
                    <c:forEach var="entry" items="${cicloAsignaturasMap}">
                        <tr>
                            <td>${entry.key.nombre}</td>
                            <td>
                                <!-- Iterar sobre las asignaturas del ciclo -->
                                <ul>
                                    <c:forEach var="asignatura" items="${entry.value}">
                                        <li>${asignatura.nombre} -- ${asignatura.horasSemanales} H</li>
                                        </c:forEach>
                                </ul>
                            </td>
                            <td>
                                <!-- Verificar si hay profesores asociados a alguna asignatura del ciclo -->
                                <c:set var="profesoresAsociados" value="false"/>
                                <c:forEach var="asignatura" items="${entry.value}">
                                    <c:if test="${profesoresAsociados eq false}">
                                        <c:forEach var="profesorEntry" items="${profesorAsignaturasMap}">
                                            <c:if test="${profesorEntry.value.contains(asignatura)}">
                                                <c:set var="profesoresAsociados" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>

                                <!-- Mostrar botón de eliminación o mensaje de advertencia -->
                                <c:choose>
                                    <c:when test="${profesoresAsociados eq true}">
                                        <button type="button" class="btn btn-danger"
                                                onclick="alert('No se puede eliminar este ciclo porque hay profesores asociados a alguna asignatura Elimina Primero Los Profesores.');">
                                            Eliminar
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Formulario para eliminar el ciclo -->
                                        <form action="EliminarCiclo" method="post" style="margin-top:3%;">
                                            <input type="hidden" name="cicloId" value="${entry.key.getIDCiclo()}">
                                            <button type="submit" class="btn btn-danger"
                                                    onclick="return confirm('¿Estás seguro de que deseas eliminar este ciclo y todas sus asignaturas?')">
                                                Eliminar
                                            </button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>

                                <!-- Mostrar el botón correcto según si el ciclo es de tarde o no -->
                                <c:choose>
                                    <c:when test="${fn:contains(entry.key.nombre, '(TARDE)')}">
                                        <form action="${pageContext.request.contextPath}/CrearHorarioTardeServlet" method="GET"
                                              style="margin-top:3%;">
                                            <input type="hidden" name="idCiclo" value="${entry.key.getIDCiclo()}">
                                            <button type="submit" class="btn btn-secondary">Crear Horario Tarde</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/CrearHorarioServlet" method="GET"
                                              style="margin-top:3%;">
                                            <input type="hidden" name="idCiclo" value="${entry.key.getIDCiclo()}">
                                            <button type="submit" class="btn btn-secondary">Crear Horario</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Bootstrap JS (jQuery debe ser incluido antes) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
