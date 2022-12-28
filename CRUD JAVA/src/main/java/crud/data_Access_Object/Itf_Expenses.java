package crud.data_Access_Object;

import crud.models.Category;
import crud.models.Expenses;

import java.util.List;
import java.util.Optional;

public interface Itf_Expenses {

    Expenses save(Expenses expenses);                           // Salvar
    Expenses update(Expenses expenses);                         // Atualizar
    void delete(Long id);                                       // Deletar
    List<Expenses> findAll();                                   // Listar todos as Despesas
    Optional<Expenses> findById(Long id);                       // Buscar por ID

    Category findByCategory(Category category);                 // Buscar por Categoria
}
