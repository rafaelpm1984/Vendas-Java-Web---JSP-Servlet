package Controller;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import Model.Produto;

@WebServlet(name = "ProdutoController", urlPatterns = {"/ProdutoController", "/produtoCadastrar", "/produtoExcluir", "/produtoAlterar", "/produtoEditar", "/produtoVisualizar"})
public class ProdutoController extends HttpServlet {

    Produto p = new Produto();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getServletPath();
            if (action.equals("/produtoVisualizar")) {
                listagem(request, response);
            } else if (action.equals("/produtoEditar")) {
                edicao(request, response);
            } else if (action.equals("/produtoAlterar")) {
                alteracao(request, response);
            } else if (action.equals("/produtoCadastrar")) {
                cadastro(request, response);
            } else if (action.equals("/produtoExcluir")) {
                exclusao(request, response);
            } else {
                response.sendRedirect("index.html");
            }
        }
    }

    protected void listagem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaProdutos", p.getAllProdutos());
        RequestDispatcher rd = request.getRequestDispatcher("/ProdutoLista.jsp");
        rd.forward(request, response);
    }

    protected void edicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        p.buscar(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("produto", p.buscar(Integer.parseInt(request.getParameter("id"))));
        RequestDispatcher rd = request.getRequestDispatcher("ProdutoEditar.jsp");
        rd.forward(request, response);
    }
    
    protected void alteracao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setNome(request.getParameter("nome"));
        p.setValor(Double.parseDouble(request.getParameter("valor")));
        p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        p.editar();
        response.sendRedirect("produtoVisualizar");
    }
    
    protected void cadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        p.setNome(request.getParameter("nome"));
        p.setValor(Double.parseDouble(request.getParameter("valor")));
        p.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
        p.gravar();
        response.sendRedirect("produtoVisualizar");
    }
    
    protected void exclusao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        p.excluir(Integer.parseInt(request.getParameter("id")));
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
