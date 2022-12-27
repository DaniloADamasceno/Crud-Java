package com.data_Access_Object;

import com.infrastructure.ConnectionFactory;
import com.models.Expenses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExpensesDAO implements Itf_Expenses{
    @Override
    public Expenses save(Expenses expenses) {
        try (Connection connection = ConnectionFactory.getConnection()) {

            String Sql = "INSERT INTO expenses (description, value, date, category) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Sql);                                         // Instrução SQL para inserir dados na tabela expenses
            preparedStatement.setString(1, expenses.getDescription());                                     // Descrição
            preparedStatement.setDouble(2, expenses.getValue());                                           // Valor
            preparedStatement.setDate(3, java.sql.Date.valueOf(expenses.getDate()));                       // Data
            preparedStatement.setString(4, expenses.getCategory().toString());                             // Categoria

            preparedStatement.executeUpdate();                                                                              // Executa a instrução SQL



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
