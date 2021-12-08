/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.Produto;
import Model.Venda;
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
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController", "/vendaBuscar", "/vendaCadastrar"})
public class VendaController extends HttpServlet {
    
    Cliente c = new Cliente();
    Produto p = new Produto();
    Venda v = new Venda();
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
            if (action.equals("/vendaBuscar")) {
                listagem(request, response);
            }else if (action.equals("/vendaCadastrar")) {
                gravar(request, response);
            } else {
                response.sendRedirect("index.html");
            }
        }
    }
    
    protected void listagem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaClientes", c.getAllClientes());
        request.setAttribute("listaProdutos", p.getAllProdutos());
        RequestDispatcher rd = request.getRequestDispatcher("/vendaRegistrar.jsp");
        rd.forward(request, response);
    }
    
    protected void gravar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        v.setFkcliente(Integer.parseInt(request.getParameter("cliente")));
        v.setFkproduto(Integer.parseInt(request.getParameter("produto")));
        v.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        v.gravar();
        
        int qtdVenda = (Integer.parseInt(request.getParameter("quantidade")));
        int estoque = p.estoque(Integer.parseInt(request.getParameter("produto")));
        estoque = estoque - qtdVenda;
        p.venda(Integer.parseInt(request.getParameter("produto")), estoque);
        response.sendRedirect("produtoVisualizar");
        
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
