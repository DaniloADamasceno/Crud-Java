package crud;

import crud.dataAccessObject.ExpensesDAO;
import crud.models.Category;
import crud.models.Expenses;

import java.time.LocalDate;
import java.util.List;

public class Application    {
    public static void main(String[] args) {

        Expenses expenses = new Expenses(1L, "Teste-1.0.0", LocalDate.now(), 100.00, Category.OTHERS);                     // Instancia um objeto do tipo Expenses
        List<Expenses> expensesList = ExpensesDAO.findAll();                                                                                       // Atribui uma descrição ao objeto expenses

        for (Expenses expenses1 : expensesList) {
            System.out.println(expenses1);
        }
    }
}
