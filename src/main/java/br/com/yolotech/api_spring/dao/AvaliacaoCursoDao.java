package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.factory.ConnectionFactory;
import br.com.yolotech.api_spring.models.AvaliacaoCurso;
import br.com.yolotech.api_spring.models.Conta;
import br.com.yolotech.api_spring.models.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AvaliacaoCursoDao {
    public Connection connection;
    public String sql = null;

    public AvaliacaoCursoDao() { this.connection = new ConnectionFactory().getConnection(); }

    public void criaTabelaAvaliacao() {
        sql = "CREATE TABLE IF NOT EXISTS avaliacao(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "curso INT," +
                "usuario INT," +
                "nota DOUBLE (4,2)," +
                "comentario VARCHAR (255)," +
                "dataCad DATE NOT NULL DEFAULT NOW()," +
                "isEditada boolean DEFAULT FALSE," +
                "isAtiva boolean DEFAULT true," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (curso) REFERENCES curso(id)," +
                "FOREIGN KEY (usuario) REFERENCES conta(id)" +
                ");";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Tabela Avaliação criada com sucesso!");;
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void addAvaliacaoCurso(AvaliacaoCurso avaliacaoCurso) {
        sql = "INSERT INTO avaliacao (curso, usuario, nota, comentario) " +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, avaliacaoCurso.getCurso().getId());
            preparedStatement.setInt(2, avaliacaoCurso.getUsuario().getId());
            preparedStatement.setDouble(3, avaliacaoCurso.getNota());
            preparedStatement.setString(4, avaliacaoCurso.getComentario());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idAvaliacaoCurso = resultSet.getInt(1);
                avaliacaoCurso.setId(idAvaliacaoCurso);
            }
            preparedStatement.close();
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void editAvaliacaoCurso(AvaliacaoCurso avaliacaoCurso) {
        sql = "UPDATE avaliacao SET nota=?, comentario=?, isEditada=?, isAtiva=? WHERE id=?;";

        try {
            PreparedStatement preparedstatement = connection.prepareStatement(sql);

            preparedstatement.setDouble(1, avaliacaoCurso.getNota());
            preparedstatement.setString(2, avaliacaoCurso.getComentario());
            preparedstatement.setBoolean(3, avaliacaoCurso.isEditada());
            preparedstatement.setBoolean(4, avaliacaoCurso.isAtiva());
            preparedstatement.setInt(5, avaliacaoCurso.getId());

            int resultado = preparedstatement.executeUpdate();

            if (resultado > 0) {
                preparedstatement.close();
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void deleteAvaliacaoCurso(int id) {
        if (id != 0) {
            sql = "DELETE FROM avaliacao WHERE id=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int executeSuccess = preparedStatement.executeUpdate();

                if (executeSuccess > 0) {
                    System.out.println("Conta removida!");
                    System.out.println(executeSuccess);
                }
                preparedStatement.close();
            } catch (SQLException error) {
                throw new RuntimeException(error);
            }
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public AvaliacaoCurso findById(int id) {
        try {
            sql = "SELECT * FROM avaliacao WHERE id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                AvaliacaoCurso avaliacaoCurso = new AvaliacaoCurso();
                avaliacaoCurso.setId(resultSet.getInt("id"));
                CursoDao cursoDao = new CursoDao();
                Curso curso = cursoDao.findById(resultSet.getInt("id"));
                avaliacaoCurso.setCurso(curso);
                ContaDao contaDao = new ContaDao();
                Conta conta = contaDao.findById(resultSet.getInt("id"));
                avaliacaoCurso.setUsuario(conta);
                avaliacaoCurso.setNota(resultSet.getDouble("nota"));
                avaliacaoCurso.setComentario(resultSet.getString("comentario"));
                avaliacaoCurso.setDataCad(resultSet.getDate("dataCad"));
                avaliacaoCurso.setEditada(resultSet.getBoolean("isEditada"));
                avaliacaoCurso.setAtiva(resultSet.getBoolean("isAtiva"));
                return avaliacaoCurso;
            } else {
                System.out.println("Avaliação não encontrada!");
                return null;
            }
        } catch (SQLException error) {
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

    public List<AvaliacaoCurso> getAvaliacoesCursos() {
        try {
            List<AvaliacaoCurso> avaliacoesCursos = new ArrayList<AvaliacaoCurso>();
            sql = "SELECT * FROM avaliacao;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AvaliacaoCurso avaliacaoCurso = new AvaliacaoCurso();
                avaliacaoCurso.setId(resultSet.getInt("id"));
                CursoDao cursoDao = new CursoDao();
                Curso curso = cursoDao.findById(resultSet.getInt("id"));
                avaliacaoCurso.setCurso(curso);
                ContaDao contaDao = new ContaDao();
                Conta conta = contaDao.findById(resultSet.getInt("id"));
                avaliacaoCurso.setUsuario(conta);
                avaliacaoCurso.setNota(resultSet.getDouble("nota"));
                avaliacaoCurso.setComentario(resultSet.getString("comentario"));
                avaliacaoCurso.setDataCad(resultSet.getDate("dataCad"));
                avaliacaoCurso.setEditada(resultSet.getBoolean("isEditada"));
                avaliacaoCurso.setAtiva(resultSet.getBoolean("isAtiva"));
                avaliacoesCursos.add(avaliacaoCurso);
            }
            resultSet.close();
            preparedStatement.close();
            return avaliacoesCursos;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
