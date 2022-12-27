package com.data_Access_Object;

import com.infrastructure.ConnectionFactory;
import com.models.Expenses;

import java.util.List;
import java.util.Optional;

public class ExpensesDAO implements Itf_Expenses{
    @Override
    public Expenses save(Expenses expenses) {
        try (Connection connection = ConnectionFactory.getConnection())

                connection.prepareStatement("INSERT INTO expenses (description, date, value, category) VALUES (?, ?, ?, ?)");
{
            String sql = "INSERT INTO expenses (description, date, value, category) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expenses.getDescription());
            preparedStatement.setDate(2, Date.valueOf(expenses.getDate()));
            preparedStatement.setDouble(3, expenses.getValue());
            preparedStatement.setString(4, expenses.getCategory().name());
            preparedStatement.execute();
            return expenses;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }




        return null;
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
