<%-- 
    Document   : editar
    Created on : 07/10/2021, 16:03:00
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page language="Java" import="Model.Produto"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar produto</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <div style="display: flex; justify-content:center; align-items: center;">
        <h1>Editar Produto</h1>
    </div>
    <%
            Produto p = (Produto) request.getAttribute("produto");
        %>
    <div style="display: flex; justify-content:center; align-items: center;">
        <form name="edit" method="post" action="produtoAlterar">
            <label for="id">Id</label>
            <input type="text" name="id" readonly style="width:100%;" value="<%out.print(p.getId()); %>"><br> <br> 
            <label for="nome">Nome</label>
            <input type="text" name="nome" style="width:100%;" value="<%out.print(p.getNome()); %>"><br> <br> 
            <label for="quantidade">Quantidade</label>
            <input type="number" name="quantidade" style="width:100%;" value="<%out.print(p.getQuantidade()); %>"><br> <br> 
            <label for="valor">Valor</label>
            <input type="number" name="valor" step="0.01" style="width:100%;" value="<%out.print(p.getValor()); %>"><br> <br> 
            <input type="submit" name="botaoSalvar" value="Salvar" style="display: block; margin:auto;">           
        </form>    
    </div> 

    <div style="display: flex; justify-content: center; align-items:center; margin-top:10px;">
        <a href="index.html" >Voltar ao início</a>
    </div>

</html>
