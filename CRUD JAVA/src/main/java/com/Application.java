package com;

import com.data_Access_Object.ExpensesDAO;
import com.models.Category;
import com.models.Expenses;

import java.time.LocalDate;

public class Application    {
    public static void main(String[] args) {

        Expenses expenses = new Expenses();                     // Instancia um objeto do tipo Expenses
        expenses.setDescription("Teste-1.0.0");                 // Atribui uma descrição ao objeto expenses
        expenses.setValue(100.00);                              // Atribui um valor ao objeto expenses
        expenses.setDate(LocalDate.now());                      // Atribui uma data ao objeto expenses
        expenses.setCategory(Category.OTHERS);                   // Atribui uma categoria ao objeto expenses

        ExpensesDAO.save(expenses);                             // Salva o objeto DEspesas no banco de dados


    }
}
