/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController", "/clienteCadastrar", "/clienteExcluir", "/clienteAlterar", "/clienteEditar", "/clienteVisualizar"})
public class ClienteController extends HttpServlet {

    Cliente c = new Cliente();
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
            String action = request.getServletPath();
            if (action.equals("/clienteVisualizar")) {
                listagem(request, response);
            } else if (action.equals("/clienteEditar")) {
                edicao(request, response);
            } else if (action.equals("/clienteAlterar")) {
                alteracao(request, response);
            } else if (action.equals("/clienteCadastrar")) {
                cadastro(request, response);
            } else if (action.equals("/clienteExcluir")) {
                exclusao(request, response);
            } else {
                response.sendRedirect("index.html");
            }
        }
    }
    
    protected void listagem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaClientes", c.getAllClientes());
        RequestDispatcher rd = request.getRequestDispatcher("/ClienteLista.jsp");
        rd.forward(request, response);
    }

    protected void edicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        p.buscar(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("cliente", c.buscar(Integer.parseInt(request.getParameter("id"))));
        RequestDispatcher rd = request.getRequestDispatcher("ClienteEditar.jsp");
        rd.forward(request, response);
    }
    
    protected void alteracao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        c.setId(Integer.parseInt(request.getParameter("id")));
        c.setNome(request.getParameter("nome"));
        c.setTelefone(request.getParameter("telefone"));
        c.editar();
        response.sendRedirect("clienteVisualizar");
    }
    
    protected void cadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        c.setNome(request.getParameter("nome"));
        c.setTelefone(request.getParameter("telefone"));
        c.gravar();
        response.sendRedirect("clienteVisualizar");
    }
    
    protected void exclusao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        c.excluir(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("clienteVisualizar");
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
