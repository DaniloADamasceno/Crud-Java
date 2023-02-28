package crud.dao;

import crud.models.Category;
import crud.models.Expenses;

import java.util.List;
import java.util.Optional;

public interface InterfaceExpenses {

    Expenses save(Expenses expenses);                           // Salvar
    Expenses update(Expenses expenses);                         // Atualizar
    void delete(Long id);                                       // Deletar
    List<Expenses> findAll();                                   // Listar todos as Despesas
    Optional<Expenses> findById(Long id);                       // Buscar por ID

    List<Expenses> findByCategory(Category category);                 // Buscar por Categoria
}
