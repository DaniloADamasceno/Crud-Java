package crud;

import crud.dao.ExpensesDAO;
import crud.models.Category;
import crud.models.Expenses;

import java.util.Optional;

public class UpdateCrud {

    public static void main(String[] args){


        ExpensesDAO expense = new ExpensesDAO();                                                                         // Instanciar uma nova Despesa
        Optional<Expenses> expensesOptional  = dataAccessObject.findById(1L);                                            // Buscar uma Despesa pelo ID

        Expenses Expenses =expensesOptional.get();                                                                       // Pegar a Despesa citada acima
        System.out.println(expense.getId());                                                                             // Imprimir o ID da Despesa Citada
        System.out.println(expense.getDescription());                                                                    // Imprimir a DESCRIÇÃO da Despesa Citada
        System.out.println(expense.getDate());                                                                           // Imprimir a DATA da Despesa Citada
        System.out.println(expense.getValue());                                                                          // Imprimir o VALOR da Despesa Citada
        System.out.println(expense.getCategory());                                                                       // Imprimir a CATEGORIA daDespesa Citada

        // Atualizar a Despesa

        expense.setDescription("Atualizando Descrição");                                                                 // Atualizar a Descrição da Despesa
        expense.setDate(LocalDate.of(2021, 10, 10));                                                                     // Atualizar a Data da Despesa
        expense.setValue(100.00);                                                                                        // Atualizar o Valor da Despesa
        expense.setCategory(Category.FOOD);                                                                              // Atualizar a Categoria da Despesa

        dataAccessObject.update(expense);                                                                                // Atualizar a Despesa





    }
}
