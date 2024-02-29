package com.fernandoarag.moneyapi.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressModel.class)
public abstract class AddressModel_ {

	public static volatile SingularAttribute<AddressModel, String> street;
	public static volatile SingularAttribute<AddressModel, String> number;
	public static volatile SingularAttribute<AddressModel, String> complement;
	public static volatile SingularAttribute<AddressModel, String> neighborhood;
	public static volatile SingularAttribute<AddressModel, String> zip_code;
	public static volatile SingularAttribute<AddressModel, String> city;
	public static volatile SingularAttribute<AddressModel, String> state;

}
