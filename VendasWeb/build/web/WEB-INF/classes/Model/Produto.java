package Model;

import Model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produto {

    private int id;
    private String nome;
    private int quantidade;
    private double valor;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean gravar() {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "insert into produto(nome,valor,quantidade)values(?,?,?)";
                ps = conexao.prepareStatement(sql);
                ps.setString(1, getNome());
                ps.setDouble(2, getValor());
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

    public boolean editar() {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "UPDATE produto SET nome=? ,valor=?, quantidade=? WHERE id=?";
                ps = conexao.prepareStatement(sql);
                ps.setString(1, getNome());
                ps.setDouble(2, getValor());
                ps.setInt(3, getQuantidade());
                ps.setInt(4, getId());
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

    public Produto buscar(int codigo) {
        Produto p = new Produto();
        try {
            Connection conexao = new Conexao().conectar();
            String comando = ("SELECT id,nome,valor, quantidade FROM produto WHERE id=" + codigo);
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setValor(rs.getDouble("valor"));
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<Produto> getAllProdutos() {
        List<Produto> listaDeProdutos = new ArrayList<Produto>();
        try {
            Connection conexao = new Conexao().conectar();
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement("select * from produto");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setValor(rs.getDouble("valor"));
                listaDeProdutos.add(p);
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeProdutos;
    }

    public boolean excluir(int codigo) {
        try {
            Connection c = new Conexao().conectar();
            if (c != null) {
                PreparedStatement ps;
                String sql = "DELETE FROM produto WHERE id = ? ";
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

    public int estoque(int id) {
            int qtd=0;
        try {
            Connection conexao = new Conexao().conectar();
            String comando = ("SELECT quantidade FROM produto WHERE id=" + id);
            PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(comando);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                qtd = rs.getInt("quantidade");
            }
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qtd;
    }

    public boolean venda(int id, int quantidade) {
        try {
            Connection conexao = new Conexao().conectar();
            if (conexao != null) {
                PreparedStatement ps;
                String sql = "UPDATE produto SET quantidade=? WHERE id=?";
                ps = conexao.prepareStatement(sql);
                ps.setInt(1, quantidade);
                ps.setInt(2, id);
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


