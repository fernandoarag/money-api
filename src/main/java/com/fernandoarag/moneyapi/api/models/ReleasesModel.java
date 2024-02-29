package com.fernandoarag.moneyapi.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fernandoarag.moneyapi.api.models.enums.ReleaseTypeEnum;

@Entity
@Table(name = "releases")
public class ReleasesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;

    @NotNull
    private BigDecimal price;

    private String observation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReleaseTypeEnum type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoriesModel category;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PeopleModel person;

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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ReleaseTypeEnum getType() {
        return type;
    }

    public void setType(ReleaseTypeEnum type) {
        this.type = type;
    }

    public CategoriesModel getCategory() {
        return category;
    }

    public void setCategory(CategoriesModel category) {
        this.category = category;
    }

    public PeopleModel getPerson() {
        return person;
    }

    public void setPerson(PeopleModel person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReleasesModel other = (ReleasesModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
