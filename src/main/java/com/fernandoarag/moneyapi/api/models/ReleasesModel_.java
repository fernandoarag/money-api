package com.fernandoarag.moneyapi.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.fernandoarag.moneyapi.api.models.enums.ReleaseTypeEnum;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReleasesModel.class)
public abstract class ReleasesModel_ {

	public static volatile SingularAttribute<ReleasesModel, Long> id;
	public static volatile SingularAttribute<ReleasesModel, String> description;
	public static volatile SingularAttribute<ReleasesModel, LocalDate> dueDate;
	public static volatile SingularAttribute<ReleasesModel, LocalDate> dateOfPayment;
	public static volatile SingularAttribute<ReleasesModel, BigDecimal> price;
	public static volatile SingularAttribute<ReleasesModel, String> observation;
	public static volatile SingularAttribute<ReleasesModel, ReleaseTypeEnum> type;
	public static volatile SingularAttribute<ReleasesModel, CategoriesModel> category;
	public static volatile SingularAttribute<ReleasesModel, PeopleModel> person;

}
