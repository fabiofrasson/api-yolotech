package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.factory.ConnectionFactory;
import br.com.yolotech.api_spring.models.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    public Connection connection;
    public String sql;

    public CategoriaDao() { this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaCategoria() {
        sql = "CREATE TABLE IF NOT EXISTS categoria(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "nome VARCHAR(20) NOT NULL," +
                "PRIMARY KEY(id)" +
                ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Tabela Categoria criada com sucesso!");
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void addCategoria (Categoria categoria) {
        sql = "INSERT INTO categoria (nome) VALUES (?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, categoria.getNome());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idCategoria = resultSet.getInt(1);
                categoria.setId(idCategoria);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void editCategoria (Categoria categoria) {
        sql = "UPDATE categoria SET nome=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, categoria.getNome());
            preparedStatement.setInt(2, categoria.getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0) {
                preparedStatement.close();
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void deleteCategoria(int id) {
        if (id != 0) {
            sql = "DELETE FROM categoria WHERE id=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, id);

                int executeSuccess = preparedStatement.executeUpdate();

                if (executeSuccess > 0) {
                    System.out.println("Categoria Removida!");
                    System.out.println(executeSuccess);
                }
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        } else {
            System.out.println("Categoria não encontrada!");
        }
    }

    public Categoria findById(int id) {
        sql = "SELECT * FROM categoria WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                return categoria;
            } else {
                System.out.println("Categoria não encontrada!");
                return null;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            throw new RuntimeException(error);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException error) {
                    error.printStackTrace();
                }
            }
        }
    }

    public List<Categoria> getCategorias() {
        sql = "SELECT * FROM categoria;";

        try {
            List<Categoria> categorias = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNome(resultSet.getString("nome"));
                categorias.add(categoria);
            }
            resultSet.close();
            preparedStatement.close();
            return categorias;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
