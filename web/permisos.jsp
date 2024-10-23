<%-- 
    Document   : permisos
    Created on : 9/09/2022, 10:36:50 AM
    Author     : Usuario Local
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Modelo.PermisosDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Permisos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    </head>
    <body>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="limpiar()">
  Agregar Permisos
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <form action="Controlador?menu=permisos" method="POST">
                        <div class="form-group">
                            <label>ID</label>
                            <input type="text" value="0" name="txtid" id="txtid" class="form-control" readonly>
                        </div>
                        <div class="form-group">
                            <label>PERMISOS</label>
                            <input type="text" name="txtper" id="txtper" class="form-control"><br>
                        </div>
                        <input type="submit" name="accion" value="Guardar" class="btn bg-info"> 
                        <input type="submit" name="accion" value="Modificar" class="btn bg-success"> 
                        <input type="submit" name="accion" value="Eliminar" class="btn bg-danger">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </form>
      </div>
    </div>
  </div>
</div>
                  
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NOMBRE PERMISOS</th>
                        </tr>
                    </thead>
                    <tbody id="tbl_permisos">
                        <tr>
                    <%
                        PermisosDAO cli = new PermisosDAO();
                        DefaultTableModel tabla = new DefaultTableModel();
                        tabla = cli.leer();
                        for (int t = 0; t < tabla.getRowCount(); t++) {
                            out.print("<tr data-id=" + tabla.getValueAt(t, 0) + ">");
                            out.print("<td>" + tabla.getValueAt(t, 0) + "</td>");
                            out.print("<td>" + tabla.getValueAt(t, 1) + "</td>");
                            //out.print("<td>");
                            //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-warning\" href=\"Controlador?menu=usuarios&accion=Editar&id=" + tabla.getValueAt(t, 0) + "\">Editar</a>");
                            //out.print("<a style=\"margin-left: 10px; border: none\" class=\"btn btn-danger\" href=\"Controlador?menu=usuarios&accion=Delete&id=" + tabla.getValueAt(t, 0) + "\">Eliminar</a>");
                            //out.print("</td>");
                            out.print("</tr>");
                        }

                    %>
                </tr>
                    </tbody>
                </table>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script type="text/javascript">
            function limpiar() {
                $("#txtid").val(0);
                $("#txtper").val('');
            }
            $('#tbl_permisos').on('click', 'tr td', function (evt) {
                var target, id, nombre;
                target = $(event.target);
                id = target.parent().data('id');
                nombre = target.parent("tr").find("td").eq(1).html();
                $("#txtid").val(id);
                $("#txtper").val(nombre);
                $("#exampleModal").modal('show');
            });
        </script>
    </body>
</html>
