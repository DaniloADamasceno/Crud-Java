package crud;

import crud.dao.ExpensesDAO;

public class DeleteCrud {

    public static void main(String[] args){

        ExpensesDAO expenseToDelete = new ExpensesDAO();    // Instanciar uma nova despesa
        expenseToDelete.delete(1L);                     //Deletar a despesa pelo ID

    }
}
