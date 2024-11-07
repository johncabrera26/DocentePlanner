<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Listado de Profesores y Asignaturas</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .profesor {
                margin-bottom: 20px;
            }
            .asignatura {
                margin-left: 20px;
            }
            .profesor-section {
                border-color: black;
            }
            #buscador {
                width: 100%;
                padding: 12px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>
        <script>
            function buscarProfesor() {
                const input = document.getElementById('buscador').value.toLowerCase();
                const profesorSections = document.getElementsByClassName('profesor-section');

                for (let section of profesorSections) {
                    const nombreProfesor = section.getElementsByTagName('h2')[0].innerText.toLowerCase();
                    if (nombreProfesor.includes(input)) {
                        section.style.display = '';
                    } else {
                        section.style.display = 'none';
                    }
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Profesores y Asignaturas</h1>

            <input type="text" id="buscador" onkeyup="buscarProfesor()" placeholder="Buscar profesor...">

            <div class="btn-group mt-2" role="group">
                <form action="AsignaturaServlet">
                    <button type="submit" class="btn btn-primary add-btn">Añadir Profe/Asignatura</button>
                </form>
            </div>

            <c:forEach var="profesorEntry" items="${profesorAsignaturasMap}">
                <div class="card profesor profesor-section">
                    <div class="card-body">
                        <h2 class="card-title">${profesorEntry.key.nombre} ${profesorEntry.key.apellido}</h2>
                        <ul class="list-group list-group-flush">
                            <c:forEach var="asignatura" items="${profesorEntry.value}">
                                <c:if test="${not empty asignatura.nombre}">
                                    <li class="list-group-item asignatura">${asignatura.nombre}</li>
                                </c:if>
                            </c:forEach>
                        </ul>
                        <!-- Formulario para eliminar el ciclo -->
                        <form action="EliminarProfeAsignatura" method="post" style="display:inline;">
                            <input type="hidden" name="profesorId" value="${profesorEntry.key.getIDProfesor()}">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este Profe y todas sus asignaturas?')">Eliminar</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Bootstrap JS (opcional) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
