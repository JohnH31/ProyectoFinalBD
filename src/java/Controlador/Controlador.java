/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Conector;
import Modelo.Grupos;
import Modelo.GruposDAO;
import Modelo.GruposPer;
import Modelo.GruposPerDAO;
import Modelo.Permisos;
import Modelo.PermisosDAO;
import Modelo.TipoCliente;
import Modelo.TipoClienteDAO;
import Modelo.Usuario;
import Modelo.UsuarioDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario Local
 */
public class Controlador extends HttpServlet {

    PermisosDAO dao = new PermisosDAO();
    Permisos p = new Permisos();
    GruposDAO gao = new GruposDAO();
    Grupos g = new Grupos();
    GruposPerDAO grao = new GruposPerDAO();
    GruposPer gr = new GruposPer();
    Usuario u = new Usuario();
    UsuarioDAO uao = new UsuarioDAO();
    Cliente c = new Cliente();
    ClienteDAO cao = new ClienteDAO();
    TipoCliente tc = new TipoCliente();
    TipoClienteDAO tcao = new TipoClienteDAO();
    Venta v = new Venta();
    VentaDAO vdao = new VentaDAO();
    UsuarioDAO udao = new UsuarioDAO();
    Usuario us = new Usuario();
    List<Venta> lista = new ArrayList<>();
    double totalv;
    String numSerie;
    Connection con;
    int conteo=0;

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
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");
        if (menu.equals("principal")) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
        if (menu.equals("home")) {
            request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
        }
        if (menu.equals("permisos")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("permisos.jsp").forward(request, response);
                    break;
                case "Guardar":
                    String id = request.getParameter("txtid");
                    String permisos = request.getParameter("txtper");
                    p.setId(Integer.parseInt(id));
                    p.setPermiso(permisos);
                    dao.agregar(p);
                    request.getRequestDispatcher("Controlador?menu=permisos&accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    String id1 = request.getParameter("txtid");
                    String permisos1 = request.getParameter("txtper");
                    p.setId(Integer.parseInt(id1));
                    p.setPermiso(permisos1);
                    System.out.println(id1);
                    System.out.println(permisos1);
                    dao.actualizar(p);
                    request.getRequestDispatcher("Controlador?menu=permisos&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    String idd = request.getParameter("txtid");
                    p.setId(Integer.parseInt(idd));
                    dao.eliminar(p);
                    request.getRequestDispatcher("Controlador?menu=permisos&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("permisos.jsp").forward(request, response);
        }
        if (menu.equals("grupos")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("grupos.jsp").forward(request, response);
                    break;
                case "Guardar":
                    String id = request.getParameter("txtid");
                    String grupos = request.getParameter("txtgru");
                    g.setId(Integer.parseInt(id));
                    g.setGrupo(grupos);
                    gao.agregar(g);
                    request.getRequestDispatcher("Controlador?menu=grupos&accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    String id1 = request.getParameter("txtid");
                    String grupo1 = request.getParameter("txtgru");
                    g.setId(Integer.parseInt(id1));
                    g.setGrupo(grupo1);
                    gao.actualizar(g);
                    request.getRequestDispatcher("Controlador?menu=grupos&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    String idd = request.getParameter("txtid");
                    g.setId(Integer.parseInt(idd));
                    gao.eliminar(g);
                    request.getRequestDispatcher("Controlador?menu=grupos&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("grupos.jsp").forward(request, response);
        }
        if (menu.equals("gruposPer")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("gruposPer.jsp").forward(request, response);
                    break;
                case "Guardar":
                    String id = request.getParameter("txtid");
                    String grupos = request.getParameter("txtgru");
                    String permisos = request.getParameter("txtper");
                    gr.setId(Integer.parseInt(id));
                    gr.setIdgrupo(Integer.parseInt(grupos));
                    gr.setIdper(Integer.parseInt(permisos));
                    grao.agregar(gr);
                    request.getRequestDispatcher("Controlador?menu=gruposPer&accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    String id1 = request.getParameter("txtid");
                    String grupo1 = request.getParameter("txtgru");
                    String permisos1 = request.getParameter("txtper");
                    gr.setId(Integer.parseInt(id1));
                    gr.setIdgrupo(Integer.parseInt(grupo1));
                    gr.setIdper(Integer.parseInt(permisos1));
                    grao.actualizar(gr);
                    request.getRequestDispatcher("Controlador?menu=gruposPer&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    String idd = request.getParameter("txtid");
                    gr.setId(Integer.parseInt(idd));
                    grao.eliminar(gr);
                    request.getRequestDispatcher("Controlador?menu=gruposPer&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("gruposPer.jsp").forward(request, response);
        }
        if (menu.equals("usuarios")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;
                case "Guardar":
                    String id = request.getParameter("txtid");
                    String nombres = request.getParameter("txtno");
                    String apellidos = request.getParameter("txtap");
                    String usuario = request.getParameter("txtus");
                    String password = request.getParameter("txtpas");
                    String estado = request.getParameter("txtes");
                    String idgrupo = request.getParameter("txtidg");
                    u.setId(Integer.parseInt(id));
                    u.setNombre(nombres);
                    u.setApellido(apellidos);
                    u.setUser(usuario);
                    u.setPass(password);
                    u.setEstado(Integer.parseInt(estado));
                    u.setIdgrupo(Integer.parseInt(idgrupo));
                    uao.agregar(u);
                    request.getRequestDispatcher("Controlador?menu=usuarios&accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    String id1 = request.getParameter("txtid");
                    String nombres1 = request.getParameter("txtno");
                    String apellidos1 = request.getParameter("txtap");
                    String usuario1 = request.getParameter("txtus");
                    String password1 = request.getParameter("txtpas");
                    String estado1 = request.getParameter("txtes");
                    String idgrupo1 = request.getParameter("txtidg");
                    u.setId(Integer.parseInt(id1));
                    u.setNombre(nombres1);
                    u.setApellido(apellidos1);
                    u.setUser(usuario1);
                    u.setPass(password1);
                    u.setEstado(Integer.parseInt(estado1));
                    u.setIdgrupo(Integer.parseInt(idgrupo1));
                    uao.actualizar(u);
                    request.getRequestDispatcher("Controlador?menu=usuarios&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    String idd = request.getParameter("txtid");
                    u.setId(Integer.parseInt(idd));
                    uao.eliminar(u);
                    request.getRequestDispatcher("Controlador?menu=usuarios&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        }
        if (menu.equals("clientes")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("clientes.jsp").forward(request, response);
                    break;
                case "Guardar":
                    String nit = request.getParameter("txtnit");
                    String nom = request.getParameter("txtno");
                    String ape = request.getParameter("txtap");
                    String dir = request.getParameter("txtdir");
                    String tel = request.getParameter("txttel");
                    String fec = request.getParameter("txtfec");
                    String idtipo = request.getParameter("txtidtipo");
                    c.setNit(nit);
                    c.setNombre(nom);
                    c.setApellido(ape);
                    c.setDireccion(dir);
                    c.setTelefono(tel);
                    c.setFecha_nacimiento(fec);
                    c.setIdTipo(Integer.parseInt(idtipo));
                    cao.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=clientes&accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    String id = request.getParameter("txtid");
                    String ni = request.getParameter("txtnit");
                    String no = request.getParameter("txtno");
                    String ap = request.getParameter("txtap");
                    String di = request.getParameter("txtdir");
                    String te = request.getParameter("txttel");
                    String fe = request.getParameter("txtfec");
                    String idtip = request.getParameter("txtidtipo");
                    c.setId(Integer.parseInt(id));
                    c.setNit(ni);
                    c.setNombre(no);
                    c.setApellido(ap);
                    c.setDireccion(di);
                    c.setTelefono(te);
                    c.setFecha_nacimiento(fe);
                    c.setIdTipo(Integer.parseInt(idtip));
                    cao.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=clientes&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    String idd = request.getParameter("txtid");
                    c.setId(Integer.parseInt(idd));
                    cao.eliminar(c);
                    request.getRequestDispatcher("Controlador?menu=clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        }
        if (menu.equals("recibos")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("recibos.jsp").forward(request, response);
                    break;
                case "Agregar":
                    totalv = 0.0;
                    String idc = request.getParameter("txtid");
                    String idv = request.getParameter("txtidven");
                    String noventa = request.getParameter("txtfac");
                    String fac = request.getParameter("txtfecv");
                    String des = request.getParameter("txtdes");
                    String mon = request.getParameter("txtmon");
                    v = new Venta();
                    v.setConteo(conteo);
                    v.setNo_venta(noventa);
                    v.setFecha(fac);
                    v.setDescripcion(des);
                    v.setVendedor(Integer.parseInt(idv));
                    v.setMonto(Double.parseDouble(mon));
                    v.setId_cliente(Integer.parseInt(idc));
                    lista.add(v);
                    
                    for (Venta venta : lista) {
                        totalv += venta.getMonto();
                    }
//                    for (int i = 0; i < lista.size(); i++) {
//                        totalv = totalv + lista.get(i).getMonto();
//                    }
                    Usuario us = uao.listarId(idv);
                    Cliente pe = cao.listarId(idc);
                    request.setAttribute("usuario", us);
                    request.setAttribute("cliente", pe);
                    request.setAttribute("totalv", totalv);
                    request.setAttribute("lista", lista);
                    conteo++;
//                vdao.agregar(v);
                    //response.sendRedirect("index.jsp");
                    //request.getRequestDispatcher("Controlador?menu=recibos&accion=Listar").forward(request, response);
                    break;
                case "Cancelar":
                    lista.clear();
                    request.getRequestDispatcher("recibos.jsp").forward(request, response);
                    break;
                case "Eliminar":                  
                    Usuario usa = uao.listarId(String.valueOf(v.getVendedor()));
                    Cliente pea = cao.listarId(String.valueOf(v.getId_cliente()));
                    request.setAttribute("usuario", usa);
                    request.setAttribute("cliente", pea);
//                    System.out.println("conteo: "+v.getConteo());
//                    lista.remove(v.getConteo());
//                    System.out.println("lista "+lista);
//                    request.setAttribute("lista", lista);
                    String idStr = request.getParameter("id");
                    int id = Integer.parseInt(idStr);
                    
                    
                    // Busca y elimina la venta con el ID especificado
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(lista.get(i).getConteo());
                        System.out.println(id);
                        if (lista.get(i).getConteo() == id) { // Compara por ID
                        lista.remove(i); // Elimina la venta
                        break; // Sale del bucle después de eliminar
                        }
                    }
                        // Recalcula el total después de eliminar
                    totalv = 0.0;
                    for (Venta venta : lista) {
                        totalv += venta.getMonto();
                    }
                    

                    // Actualiza la lista en la solicitud
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalv", totalv);                  
                    break;
                case "Generar":
                    //agregar venta
                    String idcc = request.getParameter("txtid");
                    String idvv = request.getParameter("txtidven");
                    String noventaa = request.getParameter("txtfac");
                    String facc = request.getParameter("txtfecv");
                    v.setNo_venta(noventaa);
                    v.setFecha(facc);
                    v.setTotal(totalv);
                    v.setVendedor(Integer.parseInt(idvv));
                    v.setId_cliente(Integer.parseInt(idcc));
                    vdao.agregarVenta(v);
                    //agregar detalles
                    int idvm = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idvm);
                        v.setDescripcion(lista.get(i).getDescripcion());
                        v.setMonto(lista.get(i).getMonto());
                        vdao.agregarDetalleVenta(v);
                    }
                    lista.clear();
                    //request.getRequestDispatcher("Controlador?menu=recibos&accion=Listar").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("recibos.jsp").forward(request, response);
            }
            request.getRequestDispatcher("recibos.jsp").forward(request, response);
        }
        if (menu.equals("reportes")) {
            switch (accion) {
                case "Listar":
                    request.getRequestDispatcher("reportes.jsp").forward(request, response);
                    break;
                case "PDF":
                    request.getRequestDispatcher("fechas.jsp").forward(request, response);
                    break;
                case "PDF2":
                    request.getRequestDispatcher("fechast.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("reportes.jsp").forward(request, response);
            }
            request.getRequestDispatcher("reportes.jsp").forward(request, response);
        }
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Controlador</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        processRequest(request, response);
//        String accion = request.getParameter("accion");
//        switch (accion) {
//            case "Listar":
//                List<Permisos> datos = dao.listar();
//                request.setAttribute("datos", datos);
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//                break;
//            case "Nuevo":
//                request.getRequestDispatcher("add.jsp").forward(request, response);
//                break;
//            case "Guardar":
//                String id = request.getParameter("txtid");
//                String permisos = request.getParameter("txtper");
//                p.setId(Integer.parseInt(id));
//                p.setPermiso(permisos);
//                dao.agregar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            case "Editar":
//                String ide=request.getParameter("id");
//                Permisos pe = dao.listarId(ide);
//                request.setAttribute("permisos", pe);
//                request.getRequestDispatcher("edit.jsp").forward(request, response);
//                break;
//            case "Actualizar":
//                String id1 = request.getParameter("txtid");
//                String permisos1 = request.getParameter("txtper");
//                p.setId(Integer.parseInt(id1));
//                System.out.println(id1);
//                System.out.println(permisos1);
//                p.setPermiso(permisos1);               
//                dao.actualizar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            case "Delete":
//                String ides=request.getParameter("id");
//                p.setId(Integer.parseInt(ides));              
//                dao.eliminar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            default:
//                throw new AssertionError();
//        }
//        String accion = request.getParameter("accion");
//        switch (accion) {
//            case "Listar":
//                List<Permisos> datos = dao.listar();
//                request.setAttribute("datos", datos);
//                request.getRequestDispatcher("permisos.jsp").forward(request, response);
//                break;
//            case "Nuevo":
//                request.getRequestDispatcher("permisos.jsp").forward(request, response);
//                break;
//            case "Guardar":
//                String id = request.getParameter("txtid");
//                String permisos = request.getParameter("txtper");
//                p.setId(Integer.parseInt(id));
//                p.setPermiso(permisos);
//                dao.agregar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            case "Editar":
//                String ide = request.getParameter("id");
//                Permisos pe = dao.listarId(ide);
//                request.setAttribute("permisos", pe);
//                request.getRequestDispatcher("permisos.jsp").forward(request, response);
//                break;
//            case "Actualizar":
//                String id1 = request.getParameter("txtid");
//                String permisos1 = request.getParameter("txtper");
//                p.setId(Integer.parseInt(id1));
//                System.out.println(id1);
//                System.out.println(permisos1);
//                p.setPermiso(permisos1);
//                dao.actualizar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            case "Delete":
//                String ides = request.getParameter("id");
//                p.setId(Integer.parseInt(ides));
//                dao.eliminar(p);
//                request.getRequestDispatcher("Controlador?accion=Listar").forward(request, response);
//                break;
//            default:
//                throw new AssertionError();
//        }
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
