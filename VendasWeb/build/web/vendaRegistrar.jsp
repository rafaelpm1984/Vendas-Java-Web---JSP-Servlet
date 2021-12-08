<%-- 
    Document   : vendaRegistrar
    Created on : 19/10/2021, 00:20:19
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page language="Java" import="Model.Produto"%>
<%@page language="Java" import="Model.Cliente"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Venda</title>
    </head>
    <body>
        <div style="display: flex; justify-content:center; align-items: center;">
            <h1>Venda</h1>
        </div>

        <%
            ArrayList<Produto> listaProdutos = (ArrayList<Produto>) request.getAttribute("listaProdutos");
            ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) request.getAttribute("listaClientes");
        %>

        <div style="display: flex; justify-content:center; align-items: center;">
            <form name="venda" method="post" action="vendaCadastrar">
                <label for="cliente">Cliente</label>
                <select name="cliente">
                    <%
                        for (int i = 0; i < listaClientes.size(); i++) {
                    %> 
                    <option value="<%=listaClientes.get(i).getId()%>"><%=listaClientes.get(i).getNome()%></option>
                    <%
                        }
                    %>    
                </select> <br> <br> 

                <label for="produto">Produto</label>
                <select name="produto">
                    <%
                        for (int i = 0; i < listaProdutos.size(); i++) {
                    %>
                    <option value="<%=listaProdutos.get(i).getId()%>"><%=listaProdutos.get(i).getNome()%></option>
                    <%
                        }
                    %>    
                </select> <br> <br> 
                <label for="quantidade">Quantidade</label>
                <input type="number" name="quantidade" min="1" style="width:100%;"><br> <br> 
                <input type="submit" name="botaoSalvar" value="Salvar" style="display: block; margin:auto;"> 
            </form>    
        </div> 








    </body>
</html>
