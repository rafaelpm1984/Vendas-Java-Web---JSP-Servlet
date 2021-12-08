/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Cliente {
    private int id;
    private String nome;
    private String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public boolean gravar() {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "insert into cliente(nome,telefone)values(?,?)";
                ps = conexao.prepareStatement(sql);
                ps.setString(1, getNome());
                ps.setString(2, getTelefone());

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
    
    public boolean editar() {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "UPDATE cliente SET nome=? ,telefone=? WHERE id=?";
                ps = conexao.prepareStatement(sql);
                ps.setString(1, getNome());
                ps.setString(2, getTelefone());
                ps.setInt(3, getId());
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

    public Cliente buscar(int codigo) {
        Cliente c = new Cliente();
        try {
            Connection conexao = new Conexao().conectar();
            String comando = ("SELECT id, nome,telefone FROM cliente WHERE id=" + codigo);
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
            } 
                conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> listaDeClientes = new ArrayList<Cliente>();
        try {
            Connection conexao = new Conexao().conectar();
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement("select * from cliente");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                listaDeClientes.add(c);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeClientes;
    }

    public boolean excluir(int codigo) {
        try {
            Connection c = new Conexao().conectar();
            if (c != null) {
                PreparedStatement ps;
                String sql = "DELETE FROM cliente WHERE id = ? ";
                ps = c.prepareStatement(sql);
                ps.setInt(1, codigo);
                ps.executeUpdate();
                c.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar comando SQL: " + e.getMessage());
            return false;
        }
    }
}
