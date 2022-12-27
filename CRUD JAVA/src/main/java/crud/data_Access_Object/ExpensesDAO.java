package crud.data_Access_Object;

import crud.infrastructure.ConnectionFactory;
import crud.models.Category;
import crud.models.Expenses;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpensesDAO implements Itf_Expenses {
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
            expenses.setId(generetedID);                                                                                  // Atribui o id gerado pelo banco de dados ao objeto expenses


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
        String sql = "SELECT * FROM expenses";                                                                            // Instrução SQL

        List<Expenses> expensesList = new ArrayList<>();                                                                  // Lista de despesas

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rS = preparedStatement.executeQuery();

            while (rS.next()) {                                                                                          // Enquanto houver registros no banco de dados
                Long id = rS.getLong("Id");                                                                  // Retorna o id do registro
                String description = rS.getString("description");                                            // Retorna a descrição do registro
                LocalDate date = rS.getDate("date").toLocalDate();                                           // Retorna a data do registro
                Double value = rS.getDouble("value");                                                        // Retorna o valor do registro
                Category category = Category.valueOf(rS.getString("category"));                              // Retorna a categoria do registro

                Expenses expenses = new Expenses(id, description, date, value, category);                                // Instancia um objeto do tipo Expenses
                expensesList.add(expenses);                                                                              // Adiciona o objeto expenses na lista de despesas

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return expensesList;                                                                                             // Retorna a lista de despesas


        @Override
        public Optional<Expenses> findById (Long id){
            return Optional.empty();
        }

        @Override
        public List<Expenses> findByDescription (String description){
            return null;
        }
    }
}
