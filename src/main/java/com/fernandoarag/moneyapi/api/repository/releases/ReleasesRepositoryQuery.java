package com.fernandoarag.moneyapi.api.repository.releases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fernandoarag.moneyapi.api.models.ReleasesModel;
import com.fernandoarag.moneyapi.api.repository.filter.ReleasesFilter;
import com.fernandoarag.moneyapi.api.repository.projection.ReleasesSummary;

public interface ReleasesRepositoryQuery {

    public Page<ReleasesModel> filter(ReleasesFilter releasesFilter, Pageable pageable);

    public Page<ReleasesSummary> summary(ReleasesFilter releasesFilter, Pageable pageable);
}
