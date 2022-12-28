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
            preparedStatement.setString(1, expenses.getDescription());                                 // Descrição
            preparedStatement.setDouble(2, expenses.getValue());                                       // Valor
            preparedStatement.setDate(3, java.sql.Date.valueOf(expenses.getDate()));                   // Data
            preparedStatement.setString(4, expenses.getCategory().toString());                         // Categoria

            preparedStatement.executeUpdate();                                                                           // Executa a instrução SQL

            ResultSet resultSet = preparedStatement.getGeneratedKeys();                                                  // Retorna o id gerado pelo banco de dados
            resultSet.next();                                                                                            // Retorna o próximo registro no ResultSet

            Long generetedID = resultSet.getLong("id");                                                      // Retorna o id gerado pelo banco de dados
            expenses.setId(generetedID);                                                                                 // Atribui o id gerado pelo banco de dados ao objeto expenses


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
        return null;
    }

    //--------------------------
    //  DELETE
    //--------------------------
    @Override
    public void delete(Long id) {

    }

    //--------------------------
    //  Busca Completa
    //--------------------------
    @Override
    public List<Expenses> findAll() {                                                                                    // Retorna uma lista de todos os registros da tabela DESPESAS
        String sql = "SELECT * FROM expenses";                                                                           // Instrução SQL

        List<Expenses> expensesList = new ArrayList<>();                                                                 // Lista de despesas

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rS = preparedStatement.executeQuery();

            while (rS.next()) {                                                                                          // Enquanto houver registros no banco de dados
                Long id = rS.getLong("Id");                                                                  // Retorna o id do registro
                String description = rS.getString("description");                                            // Retorna a descrição do registro
                LocalDate date = rS.getDate("date").toLocalDate();                                           // Retorna a data do registro
                Double value = rS.getDouble("value");                                                        // Retorna o valor do registro
                Category category = Category.valueOf(rS.getString("category"));                        // Retorna a categoria do registro

                Expenses expenses = new Expenses(id, description, date, value, category);                            // Instancia um objeto do tipo Expenses
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
            preparedStatement.setLong(1, id);                                                          // Substitui o primeiro parâmetro da instrução SQL pelo id

            ResultSet rS_ID = preparedStatement.executeQuery();

            while (rS_ID.next()) {                                                                                          // Enquanto houver registros no banco de dados
                Long id_FindByID = rS_ID.getLong("Id");                                                         // Retorna o id do registro
                String description = rS_ID.getString("description");                                            // Retorna a descrição do registro
                LocalDate date = rS_ID.getDate("date").toLocalDate();                                           // Retorna a data do registro
                Double value = rS_ID.getDouble("value");                                                        // Retorna o valor do registro
                Category category = Category.valueOf(rS_ID.getString("category"));                        // Retorna a categoria do registro

                expenses = new Expenses(id_FindByID, description, date, value, category);                            // Instancia um objeto do tipo Expenses

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(expenses);                                                                     // Retorna o objeto expenses
    }

    //------------------------------------
    //  Busca por Categorias
    //------------------------------------
    @Override
    public Category findByCategory(Category category) {
        String sqlID = "SELECT * FROM expenses WHERE Category = ?";                                                      // Instrução SQL
        List<Expenses> expensesList = new ArrayList<>();                                                                 // Lista de despesas


        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlID);
            preparedStatement.setString(1, category.toString());                                       // Substitui o primeiro parâmetro da instrução SQL pela CATEGORIA

            ResultSet rS_CATEGORY = preparedStatement.executeQuery();

            while (rS_CATEGORY.next()) {                                                                                 // Enquanto houver registros no banco de dados
                Long id_FindByID = rS_CATEGORY.getLong("Id");                                                // Retorna o id do registro
                String description = rS_CATEGORY.getString("description");                                   // Retorna a descrição do registro
                LocalDate date = rS_CATEGORY.getDate("date").toLocalDate();                                  // Retorna a data do registro
                Double value = rS_CATEGORY.getDouble("value");                                               // Retorna o valor do registro
                Category categoryFinBY = Category.valueOf(rS_CATEGORY.getString("category"));          // Retorna a categoria do registro

                Expenses expenses = new Expenses(id_FindByID, description, date, value, categoryFinBY);              // Instancia um objeto do tipo Expenses
                expensesList.add(expenses);                                                                              // Adiciona o objeto expenses na lista de despesas
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(expenses);                                                                     // Retorna o objeto expenses

    }

}