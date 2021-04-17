package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.models.PerfilAdmin;
import br.com.yolotech.api_spring.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PerfilAdminDAO {
    public Connection connection;

    public PerfilAdminDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void criaTabelaAdmin() {
        String sql = "CREATE TABLE IF NOT EXISTS perfiladmin (" +
                "idAdmin INT PRIMARY KEY AUTO_INCREMENT, nomeAdmin VARCHAR(50) NOT NULL, " +
                "sobrenomeAdmin VARCHAR(50) NOT NULL, numCelularAdmin VARCHAR(13) NOT NULL, "+
                "dataCadastroAdmin VARCHAR(10), isContaAtivaAdmin BOOLEAN DEFAULT true);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionaAdmin(PerfilAdmin perfilAdmin) {
        String sql = "INSERT INTO perfiladmin " +
                "(nomeAdmin, sobrenomeAdmin, numCelularAdmin, " +
                "dataCadastroAdmin) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            java.sql.Date dataParaGravar = new java.sql.Date(
                    Calendar.getInstance().getTimeInMillis());

            statement.setString(1, perfilAdmin.getNomeAdmin());
            statement.setString(2, perfilAdmin.getSobrenomeAdmin());
            statement.setString(3, perfilAdmin.getNumCelularAdmin());
            statement.setString(4, perfilAdmin.getDataCadastroAdmin());

            statement.execute();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                int idAdmin = result.getInt(1);
                perfilAdmin.setIdAdmin(idAdmin);
            }

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PerfilAdmin alteraPerfilAdmin(int id, PerfilAdmin perfilAdmin) {
        if (id != 0 && perfilAdmin != null) {
            String sql = "UPDATE perfiladmin SET nomeAdmin = ?, sobrenomeAdmin = ?, numCelularAdmin = ?, " +
                    "dataCadastroAdmin = ?, isContaAtivaAdmin = ? WHERE idAdmin = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1, perfilAdmin.getNomeAdmin());
                statement.setString(2, perfilAdmin.getSobrenomeAdmin());
                statement.setString(3, perfilAdmin.getNumCelularAdmin());
                statement.setString(4, perfilAdmin.getDataCadastroAdmin());
                statement.setBoolean(5, perfilAdmin.isContaAtivaAdmin());
                statement.setInt(6, perfilAdmin.getIdAdmin());

                int executeSuccess = statement.executeUpdate();

                if (executeSuccess > 0) {
                    System.out.println("Usuário atualizado: " + perfilAdmin.getNomeAdmin() + " " +
                            perfilAdmin.getSobrenomeAdmin());
                    System.out.println(executeSuccess);
                    return perfilAdmin;
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Usuário não encontrado");
        }
        return null;
    }

    public void removePerfilAdmin(int idAdmin) {
        if (idAdmin != 0) {
            String sql = "DELETE FROM perfiladmin WHERE idAdmin = ?;";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, idAdmin);

                int executeSuccess = statement.executeUpdate();

                if (executeSuccess > 0) {
                    System.out.println("Usuário removido!");
                    System.out.println(executeSuccess);
                }
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    public PerfilAdmin procuraPorId(int idAdmin) {
        try {
            String sql = "SELECT * FROM perfiladmin WHERE idAdmin = ?;";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setInt(1, idAdmin);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PerfilAdmin perfilAdmin = new PerfilAdmin();
                perfilAdmin.setIdAdmin(resultSet.getInt("idAdmin"));
                perfilAdmin.setNomeAdmin(resultSet.getString("nomeAdmin"));
                perfilAdmin.setSobrenomeAdmin(resultSet.getString("sobrenomeAdmin"));
                perfilAdmin.setNumCelularAdmin(resultSet.getString("numCelularAdmin"));
                perfilAdmin.setDataCadastroAdmin(resultSet.getString("dataCadastroAdmin"));
                perfilAdmin.setContaAtivaAdmin(resultSet.getBoolean("isContaAtivaAdmin"));
                return perfilAdmin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<PerfilAdmin> listaPerfisAdmin() {
        try {
            List<PerfilAdmin> perfisAdmin = new ArrayList<PerfilAdmin>();

            PreparedStatement statement = this.connection.prepareStatement(
                    "SELECT * FROM perfiladmin");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PerfilAdmin perfilAdmin = new PerfilAdmin();
                perfilAdmin.setIdAdmin(resultSet.getInt("idAdmin"));
                perfilAdmin.setNomeAdmin(resultSet.getString("nomeAdmin"));
                perfilAdmin.setSobrenomeAdmin(resultSet.getString("sobrenomeAdmin"));
                perfilAdmin.setNumCelularAdmin(resultSet.getString("numCelularAdmin"));
                perfilAdmin.setDataCadastroAdmin(resultSet.getString("dataCadastroAdmin"));
                perfilAdmin.setContaAtivaAdmin(resultSet.getBoolean("isContaAtivaAdmin"));

                perfisAdmin.add(perfilAdmin);
            }

            resultSet.close();
            statement.close();

            return perfisAdmin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
