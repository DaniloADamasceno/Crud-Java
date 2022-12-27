package crud.data_Access_Object;

import crud.infrastructure.ConnectionFactory;
import crud.models.Expenses;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ExpensesDAO implements Itf_Expenses{
    @Override
    public Expenses save(Expenses expenses) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO expenses (description, date, value, category) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, expenses.getDescription());                                     // Descrição
            preparedStatement.setDouble(2, expenses.getValue());                                           // Valor
            preparedStatement.setDate(3, java.sql.Date.valueOf(expenses.getDate()));                       // Data
            preparedStatement.setString(4, expenses.getCategory().toString());                             // Categoria

            preparedStatement.executeUpdate();                                                                            // Executa a instrução SQL

            ResultSet resultSet = preparedStatement.getGeneratedKeys();                                                   // Retorna o id gerado pelo banco de dados
            resultSet.next();                                                                                             // Retorna o próximo registro no ResultSet

            Long generetedID = resultSet.getLong("id");                                                       // Retorna o id gerado pelo banco de dados
            expenses.setId(generetedID);                                                                      // Atribui o id gerado pelo banco de dados ao objeto expenses


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return expenses;
    }

    @Override
    public Expenses update(Expenses expenses) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expenses> findAll() {
        return null;
    }

    @Override
    public Optional<Expenses> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Expenses> findByDescription(String description) {
        return null;
    }
}
