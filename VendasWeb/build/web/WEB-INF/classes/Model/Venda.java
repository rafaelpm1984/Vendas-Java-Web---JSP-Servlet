/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class Venda {
    private int id;
    private int fkcliente;
    private int fkproduto;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkcliente() {
        return fkcliente;
    }

    public void setFkcliente(int fkcliente) {
        this.fkcliente = fkcliente;
    }

    public int getFkproduto() {
        return fkproduto;
    }

    public void setFkproduto(int fkproduto) {
        this.fkproduto = fkproduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public boolean gravar() {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "insert into venda(fkcliente,fkproduto,quantidade)values(?,?,?)";
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, getFkcliente());
                ps.setInt(2, getFkproduto());
                ps.setInt(3, getQuantidade());
                ps.executeUpdate();
                ps.close();
                conexao.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
