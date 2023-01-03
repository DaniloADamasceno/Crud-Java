package crud;

import crud.dataAccessObject.ExpensesDAO;

public class DeleteCrud {

    public static void main(String[] args){

        ExpensesDAO dataAccessObject = new ExpensesDAO();                                                                // Instanciar uma nova despesa
        dataAccessObject.delete(1L);                                                                                //Deletar a despesa pelo ID

    }
}
