package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.factory.ConnectionFactory;
import br.com.yolotech.api_spring.models.AvaliacaoCurso;
import br.com.yolotech.api_spring.models.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AvaliacaoCursoDao {
    public Connection connection;
    public String sql = null;

    public AvaliacaoCursoDao() { this.connection = new ConnectionFactory().getConnection(); }

    public void addAvaliacaoCurso(AvaliacaoCurso avaliacaoCurso) {
        Conta conta = new Conta();
        sql = "INSERT INTO avaliacao (curso, usuario, nota, comentario, dataCad, isEditado, isAtiva) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            java.sql.Date dataCad = new java.sql.Date(
                    Calendar.getInstance().getTimeInMillis());

            preparedStatement.setInt(1, curso.getId());
            preparedStatement.setInt(2, conta.getId());
            preparedStatement.setDouble(3, avaliacaoCurso.getNota());
            preparedStatement.setString(4, avaliacaoCurso.getComentario());
            preparedStatement.setDate(5, dataCad);
            preparedStatement.setBoolean(6, avaliacaoCurso.isEditada());
            preparedStatement.setBoolean(7, avaliacaoCurso.isAtiva());

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
        sql = "UPDATE avaliacao SET nota=?, comentario=?, isEditado=?, isAtiva=? WHERE id=?;";

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
                avaliacaoCurso.setCurso(resultSet.getInt("curso"));
                avaliacaoCurso.setUsuario(resultSet.getInt("usuario"));
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
                avaliacaoCurso.setCurso(resultSet.getInt("curso"));
                avaliacaoCurso.setUsuario(resultSet.getInt("usuario"));
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
