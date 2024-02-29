package com.fernandoarag.moneyapi.api.repository.people;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.fernandoarag.moneyapi.api.models.PeopleModel;
import com.fernandoarag.moneyapi.api.models.PeopleModel_;
import com.fernandoarag.moneyapi.api.repository.filter.PeopleFilter;

public class PeopleRepositoryImpl implements PeopleRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<PeopleModel> filter(PeopleFilter peopleFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PeopleModel> criteria = builder.createQuery(PeopleModel.class);
        Root<PeopleModel> root = criteria.from(PeopleModel.class);

        // Criar as restrições
        Predicate[] predicates = createRestrictions(peopleFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<PeopleModel> query = manager.createQuery(criteria);

        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(peopleFilter));
    }

    private Predicate[] createRestrictions(PeopleFilter peopleFilter, CriteriaBuilder builder,
            Root<PeopleModel> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(peopleFilter.getName())) {
            predicates.add(builder.like(
                    builder.lower(root.get(PeopleModel_.name)),
                    "%" + peopleFilter.getName().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<PeopleModel> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber() > 0 ? pageable.getPageNumber() : 0;
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRecord);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(PeopleFilter peopleFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<PeopleModel> root = criteria.from(PeopleModel.class);

        Predicate[] predicates = createRestrictions(peopleFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
