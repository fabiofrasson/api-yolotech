package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.factory.ConnectionFactory;
import br.com.yolotech.api_spring.models.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContaDao {
    public Connection connection;
    public String sql = null;

    public ContaDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void addConta(Conta conta) {
        sql = "INSERT INTO conta (nome, sobrenome, titulo, contato, username, biografia, github, linkedin, senha, " +
                "role, dataCad, isAtiva) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date dataCad = new java.sql.Date(
                    Calendar.getInstance().getTimeInMillis());

            preparedStatement.setString(1, conta.getNome());
            preparedStatement.setString(2, conta.getSobrenome());
            preparedStatement.setString(3, conta.getTitulo());
            preparedStatement.setString(4, conta.getContato());
            preparedStatement.setString(5, conta.getUsername());
            preparedStatement.setString(6, conta.getBiografia());
            preparedStatement.setString(7, conta.getGithub());
            preparedStatement.setString(8, conta.getLinkedIn());
            preparedStatement.setString(9, conta.getSenha());
            preparedStatement.setInt(10, conta.getRole());
            preparedStatement.setDate(11, dataCad);
            preparedStatement.setBoolean(12, conta.isAtiva());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int idConta = resultSet.getInt(1);
                conta.setId(idConta);
            }
            preparedStatement.close();
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void editConta(Conta conta) {
        sql = "UPDATE conta SET nome=?, sobrenome=?, titulo=?, contato=?, username=?, biografia=?, github=?, " +
                "linkedin=?, senha=?,role=?, isAtiva=? WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, conta.getNome());
            preparedStatement.setString(2, conta.getSobrenome());
            preparedStatement.setString(3, conta.getTitulo());
            preparedStatement.setString(4, conta.getContato());
            preparedStatement.setString(5, conta.getUsername());
            preparedStatement.setString(6, conta.getBiografia());
            preparedStatement.setString(7, conta.getGithub());
            preparedStatement.setString(8, conta.getLinkedIn());
            preparedStatement.setString(9, conta.getSenha());
            preparedStatement.setInt(10, conta.getRole());
            preparedStatement.setBoolean(11, conta.isAtiva());
            preparedStatement.setInt(12, conta.getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado > 0) {
                preparedStatement.close();
            }
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }

    public void deleteConta(int id) {
        if (id != 0) {
            sql = "DELETE FROM conta WHERE id = ?;";

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

    public Conta findById(int id) {
        try {
            sql = "SELECT * FROM conta WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Conta conta = new Conta();
                conta.setId(resultSet.getInt("id"));
                conta.setNome(resultSet.getString("nome"));
                conta.setSobrenome(resultSet.getString("sobrenome"));
                conta.setTitulo(resultSet.getString("titulo"));
                conta.setContato(resultSet.getString("contato"));
                conta.setUsername(resultSet.getString("username"));
                conta.setBiografia(resultSet.getString("biografia"));
                conta.setGithub(resultSet.getString("github"));
                conta.setLinkedIn(resultSet.getString("linkedIn"));
                conta.setSenha(resultSet.getString("senha"));
                conta.setRole(resultSet.getInt("role"));
                conta.setDataCad(resultSet.getDate("dataCad"));
                conta.setAtiva(resultSet.getBoolean("isAtiva"));
                return conta;
            } else {
                System.out.println("Conta não encontrada!");
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

    public List<Conta> getContas() {
        try {
            List<Conta> contas = new ArrayList<Conta>();
            sql = "SELECT * FROM conta;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Conta conta = new Conta();
                conta.setId(resultSet.getInt("id"));
                conta.setNome(resultSet.getString("nome"));
                conta.setSobrenome(resultSet.getString("sobrenome"));
                conta.setTitulo(resultSet.getString("titulo"));
                conta.setContato(resultSet.getString("contato"));
                conta.setUsername(resultSet.getString("username"));
                conta.setBiografia(resultSet.getString("biografia"));
                conta.setGithub(resultSet.getString("github"));
                conta.setLinkedIn(resultSet.getString("linkedIn"));
                conta.setSenha(resultSet.getString("senha"));
                conta.setRole(resultSet.getInt("role"));
                conta.setDataCad(resultSet.getDate("dataCad"));
                conta.setAtiva(resultSet.getBoolean("isAtiva"));
                contas.add(conta);
            }
            resultSet.close();
            preparedStatement.close();
            return contas;
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
