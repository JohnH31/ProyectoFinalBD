/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.GruposPer;
import Modelo.PermisosVistaBotones;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario Local
 */
public class Validar extends HttpServlet {

    UsuarioDAO udao = new UsuarioDAO();
    Usuario us = new Usuario();
    GruposPer per = new GruposPer();
    PermisosVistaBotones bot = new PermisosVistaBotones();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
//        if (accion.equalsIgnoreCase("Ingresar")) {
//            String user = request.getParameter("txtuser");
//            String pass = request.getParameter("txtpass");
//            us=udao.validar(user, pass);
//            if (us.getUser()!=null) {
//                request.setAttribute("usuario", us);
//                request.getRequestDispatcher("Controlador?menu=principal").forward(request, response);
//            }else{
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            }
//        }else{
//                request.getRequestDispatcher("index.jsp").forward(request, response);            
//        }
//    }
        if (accion.equalsIgnoreCase("Ingresar")) {
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            us = udao.validar(user, pass);
            List<GruposPer> datos = udao.Validar2(us.getNombre());
            //System.out.println(datos);
            bot.setClientes("");
            bot.setUsuarios("");
            bot.setGruposper("");
            bot.setGrupos("");
            bot.setPermisos("");
            bot.setRecibos("");
            bot.setReportes("");
            bot.setError("");
            if (us.getUser() != null) {
                if (!datos.contains(1)) {
                    bot.setPermisos("none");
                }
                if (!datos.contains(2)) {
                    bot.setGrupos("none");
                }
                if (!datos.contains(3)) {
                    bot.setGruposper("none");
                }
                if (!datos.contains(4)) {
                    bot.setUsuarios("none");
                }
                if (!datos.contains(5)) {
                    bot.setClientes("none");
                }
                if (!datos.contains(6)) {
                    bot.setRecibos("none");
                }
                if (!datos.contains(7)) {
                    bot.setReportes("none");
                }
                request.setAttribute("usuario", us);
                request.setAttribute("perbot", bot);
                request.getRequestDispatcher("Controlador?menu=principal").forward(request, response);
            } else {
                bot.setError("<div class=\"alert alert-primary d-flex\" role=\"alert\" displey=\"none\">\n" +
"                              Usuario o Contraseña no validos!  \n" +
"                        </div>");
                request.setAttribute("perbot", bot);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
