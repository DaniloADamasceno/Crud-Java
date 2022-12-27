package com.data_Access_Object;

import com.models.Expenses;

import java.util.List;
import java.util.Optional;

public interface Itf_Expenses {

    Expenses save(Expenses expenses);                           // Salvar
    Expenses update(Expenses expenses);                         // Atualizar
    void delete(Long id);                                       // Deletar
    List<Expenses> findAll();                                   // Listar todos as Despesas
    Optional<Expenses> findById(Long id);                       // Buscar por ID
    List<Expenses> findByDescription(String description);       // Buscar por Descrição
}
