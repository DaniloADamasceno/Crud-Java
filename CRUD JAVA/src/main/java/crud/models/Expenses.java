package crud.models;

import crud.data_Access_Object.Itf_Expenses;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Expenses implements Itf_Expenses {

    private Long id;                            // primary key
    private String description;                 // Descrição dos itens
    private LocalDate date;                     // Data
    private Double value;                       // Valor
    private Category category;                  // Categoria dos produtos



    //--------------------------
    //       Constructors
    //--------------------------
    @Override
    public Expenses save(Expenses expenses) {
        return null;
    }

    @Override
    public Expenses update(Expenses expenses) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Expenses> findAll() {
        return null;
    }

    @Override
    public Optional<Expenses> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Expenses> findByDescription(String description) {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


