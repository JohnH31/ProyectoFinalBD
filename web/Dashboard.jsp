<%-- 
    Document   : Dashboard
    Created on : 22/10/2024, 01:40:41 PM
    Author     : jona9
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.VentaDAO"%>
<%@page import="Modelo.Venta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard de Ventas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
        .chart-row {
            display: flex;
            justify-content: center; /* Centrar los gráficos en el contenedor */
            width: 100%;
        }

        .chart-container {
            width: 55%; /* Ajusta el ancho de los gráficos según sea necesario */
            height: 400px;
            margin: 0 20px; /* Ajusta el espacio entre los gráficos aquí */
        }

        .center-chart {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .chart-container-full {
            width: 55%;
            height: 400px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Dashboard de Ventas</h2>
<br>
    <!-- Gráficos de Barras y Líneas uno al lado del otro -->
    <div class="chart-row">
        <div class="chart-container">
            <canvas id="ventasPorVendedor"></canvas>
        </div>
        <div class="chart-container">
            <canvas id="ventasLineas"></canvas>
        </div>
    </div>

    <!-- Gráfico Circular centrado y del mismo tamaño que los otros -->
    <div class="center-chart">
        <div class="chart-container-full">
            <canvas id="ventasCircular"></canvas>
        </div>
    </div>
</div>

<%
    VentaDAO ventasDAO = new VentaDAO();
    List<Venta> listaVentas = ventasDAO.listarVenta();
    List<Venta> listaDetalleVentas = ventasDAO.listarDetalleVenta();

    // Gráfico Circular: Ventas por Artículo
    HashMap<String, Integer> ventasPorArticulo = new HashMap<>();
    for (Venta detalle : listaDetalleVentas) {
        String descripcion = detalle.getDescripcion();
        ventasPorArticulo.put(descripcion, ventasPorArticulo.getOrDefault(descripcion, 0) + 1);
    }

    // Datos para gráfico circular
    List<String> articulos = new ArrayList<>(ventasPorArticulo.keySet());
    List<Integer> cantidadVentasPorArticulo = new ArrayList<>(ventasPorArticulo.values());

    // Gráfico de Líneas: Ventas por Fecha
    HashMap<String, Double> ventasPorFecha = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Venta venta : listaVentas) {
        String fecha = venta.getFecha();
        ventasPorFecha.put(fecha, ventasPorFecha.getOrDefault(fecha, 0.0) + venta.getTotal());
    }

    // Datos para gráfico de líneas
    List<String> fechas = new ArrayList<>(ventasPorFecha.keySet());
    List<Double> totalVentasPorFecha = new ArrayList<>(ventasPorFecha.values());
    
    // Obtener ventas por vendedor
    HashMap<String, Integer> ventasPorVendedor = ventasDAO.ventasPorVendedor();
    
    // Datos para gráfico de barras
    List<String> nombresVendedores = new ArrayList<>(ventasPorVendedor.keySet());
    List<Integer> cantidadVentasPorVendedor = new ArrayList<>(ventasPorVendedor.values());
%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript">
// Gráfico de Ventas por Vendedor
const nombresVendedores = <%= new com.google.gson.Gson().toJson(nombresVendedores) %>;
const cantidadVentasPorVendedor = <%= new com.google.gson.Gson().toJson(cantidadVentasPorVendedor) %>;

const ctxVendedores = document.getElementById('ventasPorVendedor').getContext('2d');
const ventasVendedores = new Chart(ctxVendedores, {
    type: 'bar',
    data: {
        labels: nombresVendedores, // Usar nombres de vendedores
        datasets: [{
            label: 'Cantidad de Ventas por Vendedor',
            data: cantidadVentasPorVendedor,
            backgroundColor: 'rgba(75, 192, 192, 0.2)',
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Gráfico Circular (Ventas por Artículo)
const ctxCircular = document.getElementById('ventasCircular').getContext('2d');
const ventasCircular = new Chart(ctxCircular, {
    type: 'pie',
    data: {
        labels: <%= new com.google.gson.Gson().toJson(articulos) %>,
        datasets: [{
            data: <%= new com.google.gson.Gson().toJson(cantidadVentasPorArticulo) %>,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
    }
});

// Gráfico de Líneas
const ctxLineas = document.getElementById('ventasLineas').getContext('2d');
const ventasLineas = new Chart(ctxLineas, {
    type: 'line',
    data: {
        labels: <%= new com.google.gson.Gson().toJson(fechas) %>,
        datasets: [{
            label: 'Ventas Totales por Fecha',
            data: <%= new com.google.gson.Gson().toJson(totalVentasPorFecha) %>,
            fill: false,
            borderColor: 'rgba(75, 192, 192, 1)',
            tension: 0.1
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
    }
});
</script>
</body>
</html>

