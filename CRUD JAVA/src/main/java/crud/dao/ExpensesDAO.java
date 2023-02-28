package crud.dao;

import crud.infrastructure.ConnectionFactory;
import crud.models.Category;
import crud.models.Expenses;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpensesDAO implements InterfaceExpenses {

    //--------------------------
    //  Salvar
    //--------------------------
    @Override
    public Expenses save(Expenses expenses) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO expenses (description, date, value, category) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, expenses.getDescription());                                    // Descrição
            preparedStatement.setDate(2, java.sql.Date.valueOf(expenses.getDate()));                      // Data
            preparedStatement.setDouble(3, expenses.getValue());                                          // Valor
            preparedStatement.setString(4, expenses.getCategory().toString());                            // Categoria
            preparedStatement.setLong(5, expenses.getId());                                               // ID

            preparedStatement.executeUpdate();                                                                           // Executa a instrução SQL

            ResultSet resultSet = preparedStatement.getGeneratedKeys();                                                  // Retorna o ‘id’ gerado pelo banco de dados
            resultSet.next();                                                                                            // Retorna o próximo registro no ResultSet

            Long generetedID = resultSet.getLong("id");                                                      // Retorna o ‘id’ gerado pelo banco de dados
            expenses.setId(generetedID);                                                                                 // Atribui o ‘id’ gerado pelo banco de dados ao objeto expenses

        } catch (SQLException ex) {

            throw new RuntimeException(ex);
        }
        return expenses;
    }

    //--------------------------
    //  Atualização
    //--------------------------
    @Override
    public Expenses update(Expenses expenses) {

        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE expenses SET description = ?, date = ?, value = ?, category = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, expenses.getDescription());                                    // Descrição
            preparedStatement.setDate(2, java.sql.Date.valueOf(expenses.getDate()));                      // Data
            preparedStatement.setDouble(3, expenses.getValue());                                          // Valor
            preparedStatement.setString(4, expenses.getCategory().toString());                            // Categoria

            preparedStatement.executeUpdate();                                                                           // Executa a instrução SQL

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return expenses;
    }

    //--------------------------
    //  DELETE
    //--------------------------
    @Override
    public void delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM expenses WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);                                                             // ID

            preparedStatement.executeUpdate();                                                                           // Executa a instrução SQL

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    //--------------------------
    //  Busca Completa
    //--------------------------
    @Override
    public static List<Expenses> findAll() {                                                                                    // Retorna uma lista de todos os registros da tabela DESPESAS
        String sql = "SELECT * FROM expenses";                                                                           // Instrução SQL

        List<Expenses> expensesList = new ArrayList<>();                                                                 // Lista de despesas

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {                                                                                   // Enquanto houver registros no banco de dados
                Long id = resultSet.getLong("Id");                                                           // Retorna o “id” do registro
                String description = resultSet.getString("description");                                     // Retorna a descrição do registro
                LocalDate date = resultSet.getDate("date").toLocalDate();                                    // Retorna a data do registro
                Double value = resultSet.getDouble("value");                                                 // Retorna o valor do registro
                Category category = Category.valueOf(resultSet.getString("category"));                       // Retorna a categoria do registro

                Expenses expenses = new Expenses(id, description, date, value, category);                                // Instancia um objeto do tipo Expenses
                expensesList.add(expenses);                                                                              // Adiciona o objeto expenses na lista de despesas
            }

        } catch (SQLException exFindAll) {
            throw new RuntimeException(exFindAll);
        }
        return expensesList;                                                                                             // Retorna a lista de despesas
    }

    //--------------------------------
    //  Busca por ID
    //--------------------------------
    @Override
    public Optional<Expenses> findById(Long id) {                                                                        // Retorna um registro da tabela DESPESAS pelo ID
        String sqlID = "SELECT * FROM expenses WHERE id = ?";                                                            // Instrução SQL
        Expenses expenses = null;                                                                                        // Objeto expenses


        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlID);
            preparedStatement.setLong(1, id);                                                             // Substitui o primeiro parâmetro da instrução SQL pelo “id”

            ResultSet resultSetID = preparedStatement.executeQuery();

            while (resultSetID.next()) {                                                                                 // Enquanto houver registros no banco de dados
                Long idFindByID = resultSetID.getLong("Id");                                                 // Retorna o “id” do registro
                String description = resultSetID.getString("description");                                   // Retorna a descrição do registro
                LocalDate date = resultSetID.getDate("date").toLocalDate();                                  // Retorna a data do registro
                Double value = resultSetID.getDouble("value");                                               // Retorna o valor do registro
                Category category = Category.valueOf(resultSetID.getString("category"));                     // Retorna a categoria do registro

                expenses = new Expenses(idFindByID, description, date, value, category);                                 // Instancia um objeto do tipo Expenses

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(expenses);                                                                            // Retorna o objeto expenses
    }

    //------------------------------------
    //  Busca por Categorias
    //------------------------------------
    @Override
    public List<Expenses> findByCategory(Category category) {
        String sqlID = "SELECT * FROM expenses WHERE Category = ?";                                                      // Instrução SQL
        List<Expenses> expensesCategory = new ArrayList<>();                                                             // Lista de despesas


        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlID);
            preparedStatement.setString(1, category.toString());                                          // Substitui o primeiro parâmetro da instrução SQL pela CATEGORIA

            ResultSet resultSetCategory = preparedStatement.executeQuery();

            while (resultSetCategory.next()) {                                                                           // Enquanto houver registros no banco de dados
                Long id_FindByID = resultSetCategory.getLong("Id");                                          // Retorna o “id” do registro
                String description = resultSetCategory.getString("description");                             // Retorna a descrição do registro
                LocalDate date = resultSetCategory.getDate("date").toLocalDate();                            // Retorna a data do registro
                Double value = resultSetCategory.getDouble("value");                                         // Retorna o valor do registro
                Category categoryFinBY = Category.valueOf(resultSetCategory.getString("category"));          // Retorna a categoria do registro

                Expenses expenses = new Expenses(id_FindByID, description, date, value, categoryFinBY);                  // Instancia um objeto do tipo Expenses
                expensesCategory.add(expenses);                                                                          // Adiciona o objeto expenses na lista de despesas
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return expensesCategory;                                                                                         // Retorna o objeto expenses

    }

}