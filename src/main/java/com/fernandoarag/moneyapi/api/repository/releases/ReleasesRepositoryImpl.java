package com.fernandoarag.moneyapi.api.repository.releases;

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

import com.fernandoarag.moneyapi.api.models.ReleasesModel;
import com.fernandoarag.moneyapi.api.models.ReleasesModel_;
import com.fernandoarag.moneyapi.api.repository.filter.ReleasesFilter;

public class ReleasesRepositoryImpl implements ReleasesRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<ReleasesModel> filter(ReleasesFilter releasesFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ReleasesModel> criteria = builder.createQuery(ReleasesModel.class);
        Root<ReleasesModel> root = criteria.from(ReleasesModel.class);

        // Criar as restrições
        Predicate[] predicates = createRestrictions(releasesFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<ReleasesModel> query = manager.createQuery(criteria);

        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(releasesFilter));
    }

    private Predicate[] createRestrictions(ReleasesFilter releasesFilter, CriteriaBuilder builder,
            Root<ReleasesModel> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(releasesFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get(ReleasesModel_.description)),
                    "%" + releasesFilter.getDescription().toLowerCase() + "%"));
        }

        if (releasesFilter.getDateFrom() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(ReleasesModel_.dueDate),
                            releasesFilter.getDateFrom()));
        }

        if (releasesFilter.getDateTo() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(ReleasesModel_.dueDate),
                            releasesFilter.getDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<ReleasesModel> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber() > 0 ? pageable.getPageNumber() : 0;
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRecord = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRecord);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(ReleasesFilter releasesFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<ReleasesModel> root = criteria.from(ReleasesModel.class);

        Predicate[] predicates = createRestrictions(releasesFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

}
