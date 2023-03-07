package crud;

import crud.dao.ExpensesDAO;
import crud.models.Category;
import crud.models.Expenses;

import java.time.LocalDate;
import java.util.Optional;

import static java.lang.System.*;

public class UpdateCrud {

    public static void main(String[] args){


        ExpensesDAO expense = new ExpensesDAO();                                                                         // Instanciar uma nova Despesa
        Optional<Expenses> expensesOptional  = expense.findById(1L);                                            // Buscar uma Despesa pelo ID

        Expenses Expenses = expensesOptional.get();                                                                       // Pegar a Despesa citada acima
        out.println(Expenses.getId());                                                                             // Imprimir o ID da Despesa Citada
        out.println(Expenses.getDescription());                                                                    // Imprimir a DESCRIÇÃO da Despesa Citada
        out.println(Expenses.getDate());                                                                           // Imprimir a DATA da Despesa Citada
        out.println(Expenses.getValue());                                                                          // Imprimir o VALOR da Despesa Citada
        out.println(Expenses.getCategory());                                                                       // Imprimir a CATEGORIA daDespesa Citada

        // Atualizar a Despesa

        Expenses.setDescription("Atualizando Descrição");                                                                 // Atualizar a Descrição da Despesa
        Expenses.setDate(LocalDate.of(2021, 10, 10));                                                                     // Atualizar a Data da Despesa
        Expenses.setValue(100.00);                                                                                        // Atualizar o Valor da Despesa
        Expenses.setCategory(Category.FOOD);                                                                              // Atualizar a Categoria da Despesa

        expense.update(Expenses);                                                                                // Atualizar a Despesa





    }
}
