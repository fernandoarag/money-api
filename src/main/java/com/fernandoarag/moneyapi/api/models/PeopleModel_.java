package com.fernandoarag.moneyapi.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeopleModel.class)
public abstract class PeopleModel_ {

	public static volatile SingularAttribute<PeopleModel, Long> id;
	public static volatile SingularAttribute<PeopleModel, String> name;
	public static volatile SingularAttribute<PeopleModel, AddressModel> address;
	public static volatile SingularAttribute<PeopleModel, Boolean> active;

}
