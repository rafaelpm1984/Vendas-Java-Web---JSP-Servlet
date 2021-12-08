<%-- 
    Document   : produtosLista
    Created on : 06/10/2021, 23:44:51
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page language="Java" import="Model.Produto"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de produtos</title>
    </head>
    <body>




        <div style="display: flex; justify-content:center; align-items: center;">
            <h1>Lista de produtos</h1>
        </div>

        <%
            ArrayList<Produto> lista = (ArrayList<Produto>) request.getAttribute("listaProdutos");
//            out.println(lista.size());
        %>
        <table width="60%" border="1" align="center" cellspacing="0">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Valor</th>
                    <th>Quantidade</th>
                    <th>Editar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < lista.size(); i++) {
                %>
                <tr>
                    <td>
                        <%=lista.get(i).getId()%>
                    </td>
                    <td>
                        <%=lista.get(i).getNome()%>
                    </td>
                    <td>
                        <%=lista.get(i).getValor()%>
                    </td>
                    <td>
                        <%=lista.get(i).getQuantidade()%>
                    </td>
                    <td>
                        <a href="produtoEditar?id=<%=lista.get(i).getId()%>">
                            <center>
                                <img src="imagens/edit.png" alt="Editar"/>
                            </center>
                        </a>
                    </td>
                    <td>
                        <a href="produtoExcluir?id=<%=lista.get(i).getId()%>">
                            <center>
                                <img src="imagens/delete.png" alt="Excluir"/>
                            </center>
                        </a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <div style="display: flex; justify-content:center; align-items: center;">
            <a href="ProdutoCadastrar.html" style="display: flex; align-items: center; margin:10px;">
                <img src="imagens/cadastrar.png" alt="cadastrar" width="40" height="40"/>Cadastrar novo produto
            </a>
        </div>
        <div style="display: flex; justify-content:center; align-items: center;">
            <a href="index.html" style="display: flex; align-items: center; margin:10px;">
                <img src="imagens/return.png" alt="início" width="40" height="40"/>Voltar ao início
            </a>
        </div>

    </body>
</html>
