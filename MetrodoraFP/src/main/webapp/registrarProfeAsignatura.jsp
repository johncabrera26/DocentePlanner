<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Inserción de Profesores</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos adicionales */
        body{
            font-family: Arial, sans-serif;
        }
        .form-container {
            max-width: 500px;
            margin: 0 auto;
        }
        .form-title {
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-group input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ced4da;
        }
        .form-group input[type="submit"] {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .asignatura-inputs {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container form-container">
    <h1 class="form-title">Formulario de Inserción de Profesores</h1>
    <form action="InsertarProfesorAsignaturasServlet" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="nombreProfesor">Nombre del Profesor:</label>
            <input type="text" id="nombreProfesor" name="nombreProfesor">
            <span id="nombreProfesorError" class="error-message"></span>
        </div>
        <div class="form-group">
            <label for="apellidoProfesor">Apellido del Profesor:</label>
            <input type="text" id="apellidoProfesor" name="apellidoProfesor">
            <span id="apellidoProfesorError" class="error-message"></span>
        </div>

        <div class="form-group">
            <label>Asignaturas:</label>
            <c:forEach var="asignatura" items="${asignatura}">
                <c:if test="${not empty asignatura.nombre}">
                    <div class="form-check">
                        <input type="checkbox" id="asignatura${asignatura.getIDAsignatura()}" name="asignatura" value="${asignatura.getIDAsignatura()}">
                        <label class="form-check-label" for="asignatura${asignatura.getIDAsignatura()}">${asignatura.nombre}</label>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div id="asignaturasSeleccionadas"></div>

        
        <div class="form-group">
            <input type="submit" value="Enviar">
        </div>
    </form>
</div>

<!-- Bootstrap JS (jQuery debe ser incluido antes) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    function validateForm() {
        var nombreProfesor = document.getElementById("nombreProfesor").value;
        var apellidoProfesor = document.getElementById("apellidoProfesor").value;
        var checkboxes = document.querySelectorAll('input[name="asignatura"]:checked');
        var nombreProfesorError = document.getElementById("nombreProfesorError");
        var apellidoProfesorError = document.getElementById("apellidoProfesorError");

        nombreProfesorError.innerHTML = "";
        apellidoProfesorError.innerHTML = "";

        if (nombreProfesor.trim() == "") {
            nombreProfesorError.innerHTML = "Por favor, ingrese el nombre del profesor";
            return false;
        }
        if (apellidoProfesor.trim() == "") {
            apellidoProfesorError.innerHTML = "Por favor, ingrese el apellido del profesor";
            return false;
        }

        // Verificar si al menos una asignatura está seleccionada
        if (checkboxes.length === 0) {
            alert("Por favor, seleccione al menos una asignatura");
            return false;
        }

        return true;
    }
</script>
</body>
</html>
