<%-- 
    Document   : ClienteEditar
    Created on : 18/10/2021, 20:11:37
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page language="Java" import="Model.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar produto</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <div style="display: flex; justify-content:center; align-items: center;">
        <h1>Editar Cliente</h1>
    </div>
    <%
            Cliente c = (Cliente) request.getAttribute("cliente");
        %>
    <div style="display: flex; justify-content:center; align-items: center;">
        <form name="edit" method="post" action="clienteAlterar">
            <label for="id">Id</label>
            <input type="text" name="id" readonly style="width:100%;" value="<%out.print(c.getId()); %>"><br> <br> 
            <label for="nome">Nome</label>
            <input type="text" name="nome" style="width:100%;" value="<%out.print(c.getNome()); %>"><br> <br> 
            <label for="nome">Telefone</label>
            <input type="text" name="telefone" style="width:100%;" value="<%out.print(c.getTelefone()); %>"><br> <br> 
            <input type="submit" name="botaoSalvar" value="Salvar" style="display: block; margin:auto;">           
        </form>    
    </div> 

    <div style="display: flex; justify-content: center; align-items:center; margin-top:10px;">
        <a href="index.html" >Voltar ao início</a>
    </div>

</html>
