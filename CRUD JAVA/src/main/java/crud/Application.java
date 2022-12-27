package crud;

import crud.data_Access_Object.ExpensesDAO;
import crud.models.Category;
import crud.models.Expenses;

import java.sql.SQLOutput;
import java.time.LocalDate;

public class Application    {
    public static void main(String[] args) {

        Expenses expenses = new Expenses();                     // Instancia um objeto do tipo Expenses
        expenses.setDescription("Teste-1.0.0");                 // Atribui uma descrição ao objeto expenses
        expenses.setValue(100.00);                              // Atribui um valor ao objeto expenses
        expenses.setDate(LocalDate.now());                      // Atribui uma data ao objeto expenses
        expenses.setCategory(Category.OTHERS);                  // Atribui uma categoria ao objeto expenses



        Expenses DespesaInserida;                             // Salva o objeto Despesas no banco de dados
        DespesaInserida = ExpensesDAO.save(expenses);
        System.out.println("A despesa foi inserida com sucesso! ID: " + DespesaInserida.getId());


    }
}
