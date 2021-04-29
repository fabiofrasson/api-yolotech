package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.factory.ConnectionFactory;
import br.com.yolotech.api_spring.models.Categoria;
import br.com.yolotech.api_spring.models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {
    public Connection connection;
    public String sql = null;

    public CursoDao() { this.connection = new ConnectionFactory().getConnection(); }

    public void criaTabelaCurso() {
        sql = "CREATE TABLE IF NOT EXISTS curso(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "nome VARCHAR(50) NOT NULL," +
                "descricao VARCHAR(50) NOT NULL," +
                "instrutor VARCHAR(50) NOT NULL," +
                "categoria INT," +
                "site VARCHAR(500) NOT NULL," +
                "duracao DOUBLE(2,1) NOT NULL," +
                "slug VARCHAR(50) NOT NULL," +
                "dataCad DATE NOT NULL DEFAULT NOW()," +
                "isEditado boolean DEFAULT FALSE," +
                "isAtivo boolean DEFAULT TRUE," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (categoria) REFERENCES categoria(id)" +
                ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Tabela Curso criada com sucesso!");
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void addCurso(Curso curso) {
        sql = "INSERT INTO curso (nome, descricao, instrutor, categoria, site, duracao, slug) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setString(3, curso.getInstrutor());
            preparedStatement.setInt(4, curso.getCategoria().getId());
            preparedStatement.setString(5, curso.getSite());
            preparedStatement.setDouble(6, curso.getDuracao());
            preparedStatement.setString(7, curso.getSlug());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idCurso = resultSet.getInt(1);
                curso.setId(idCurso);
            }
            preparedStatement.close();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void editCurso(Curso curso) {
        sql = "UPDATE curso SET nome=?, descricao=?, instrutor=?, categoria=?, site=?, duracao=?, slug=?, isEditado=?, " +
                "isAtivo=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getDescricao());
            preparedStatement.setString(3, curso.getInstrutor());
            preparedStatement.setInt(4, curso.getCategoria().getId());
            preparedStatement.setString(5, curso.getSite());
            preparedStatement.setDouble(6, curso.getDuracao());
            preparedStatement.setString(7, curso.getSlug());
            preparedStatement.setBoolean(8, curso.isEditado());
            preparedStatement.setBoolean(9, curso.isAtivo());
            preparedStatement.setInt(10, curso.getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0) {
                preparedStatement.close();
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void deleteCurso(int id) {
        if (id != 0) {
            sql = "DELETE FROM curso WHERE id=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int executeSuccess = preparedStatement.executeUpdate();

                if (executeSuccess > 0) {
                    System.out.println("Curso removido!");
                    System.out.println(executeSuccess);
                }
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        } else {
            System.out.println("Curso não encontrado!");
        }
    }

    public Curso findById(int id) {
        sql = "SELECT * FROM curso WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Curso curso = new Curso();
                curso.setId(resultSet.getInt("id"));
                curso.setNome(resultSet.getString("nome"));
                curso.setDescricao(resultSet.getString("descricao"));
                curso.setInstrutor(resultSet.getString("instrutor"));
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria categoria = categoriaDao.findById(resultSet.getInt("id"));
                curso.setCategoria(categoria);
                curso.setSite(resultSet.getString("site"));
                curso.setDuracao(resultSet.getDouble("duracao"));
                curso.setSlug(resultSet.getString("slug"));
                curso.setDataCad(resultSet.getDate("dataCad"));
                curso.setEditado(resultSet.getBoolean("isEditado"));
                curso.setAtivo(resultSet.getBoolean("isAtivo"));
                return curso;
            } else {
                System.out.println("Curso não encontrado!");
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

    public List<Curso> getCursos() {
        sql = "SELECT * FROM curso;";

        try {
            List<Curso> cursos = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setId(resultSet.getInt("id"));
                curso.setNome(resultSet.getString("nome"));
                curso.setDescricao(resultSet.getString("descricao"));
                curso.setInstrutor(resultSet.getString("instrutor"));
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria categoria = categoriaDao.findById(resultSet.getInt("id"));
                curso.setCategoria(categoria);
                curso.setSite(resultSet.getString("site"));
                curso.setDuracao(resultSet.getDouble("duracao"));
                curso.setSlug(resultSet.getString("slug"));
                curso.setDataCad(resultSet.getDate("dataCad"));
                curso.setEditado(resultSet.getBoolean("isEditado"));
                curso.setAtivo(resultSet.getBoolean("isAtivo"));
                cursos.add(curso);
            }
            resultSet.close();
            preparedStatement.close();
            return cursos;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
