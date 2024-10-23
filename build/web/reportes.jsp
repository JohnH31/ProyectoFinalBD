<%-- 
    Document   : reportes
    Created on : 4/11/2022, 06:48:56 PM
    Author     : Usuario Local
--%>

<%@page import="java.util.Map"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Modelo.Conector"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>
    <body>
        <form action="Controlador?menu=reportes" method="POST">
            <h3 class="text-center">Reporte Por Fecha Con Detalles</h3>
        <div class="d d-flex justify-content-center">
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <label>Fecha de Inicio</label>
                            <input type="date" name="txtin" id="txtin" class="form-control"> 
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">

                            <label>Fecha de Fin</label>
                            <input type="date" name="txtout" id="txtout" class="form-control"> 
                        </div>
                    </div>
                </div>
            </div>
        </div>   
            <br>
        <div class="text-center align-items-center justify-content-center">
            <button name="accion" id="btn_pdf" value="PDF" class="btn bg-info">Generar PDF</button>
                </div>
            <br><br><h3 class="text-center">Reporte Por Fecha Sin Detalles</h3>
            <div class="d d-flex justify-content-center">
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <label>Fecha de Inicio</label>
                            <input type="date" name="txtinit" id="txtinit" class="form-control"> 
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">

                            <label>Fecha de Fin</label>
                            <input type="date" name="txtend" id="txtend" class="form-control"> 
                        </div>
                    </div>
                </div>
            </div>
        </div>   
            <br>
        <div class="text-center align-items-center justify-content-center">
            <button name="accion" id="btn_pdf2" value="PDF2" class="btn bg-info">Generar PDF</button>
                </div>
            </form>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    </body>
</html>

