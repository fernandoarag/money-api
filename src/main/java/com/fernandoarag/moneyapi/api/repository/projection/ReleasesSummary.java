package com.fernandoarag.moneyapi.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fernandoarag.moneyapi.api.models.enums.ReleaseTypeEnum;

public class ReleasesSummary {

    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate dateOfPayment;
    private BigDecimal price;
    private ReleaseTypeEnum type;
    private String category;
    private String person;

    public ReleasesSummary(Long id, String description, LocalDate dueDate, LocalDate dateOfPayment, BigDecimal price,
            ReleaseTypeEnum type, String category, String person) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.dateOfPayment = dateOfPayment;
        this.price = price;
        this.type = type;
        this.category = category;
        this.person = person;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ReleaseTypeEnum getType() {
        return type;
    }

    public void setType(ReleaseTypeEnum type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

}
