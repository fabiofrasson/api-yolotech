package br.com.yolotech.api_spring.dao;

import br.com.yolotech.api_spring.entities.PerfilAdmin;
import br.com.yolotech.api_spring.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PerfilAdminDAO {
    private Connection connection;

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
                "dataCadastroAdmin, isContaAtivaAdmin) VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            java.sql.Date dataParaGravar = new java.sql.Date(
                    Calendar.getInstance().getTimeInMillis());

            statement.setString(1, perfilAdmin.getNomeAdmin());
            statement.setString(2, perfilAdmin.getSobrenomeAdmin());
            statement.setString(3, perfilAdmin.getNumCelularAdmin());
            statement.setString(4, perfilAdmin.getDataCadastroAdmin());
            statement.setBoolean(5, perfilAdmin.isContaAtivaAdmin());

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

    public void alteraPerfilAdmin(PerfilAdmin perfilAdmin) {
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

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePerfilAdmin(PerfilAdmin perfilAdmin) {
        String sql = "DELETE FROM perfiladmin WHERE idAdmin = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, perfilAdmin.getIdAdmin());

            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
