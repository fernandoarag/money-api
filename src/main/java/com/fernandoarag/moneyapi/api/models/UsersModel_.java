package com.fernandoarag.moneyapi.api.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsersModel.class)
public abstract class UsersModel_ {

    public static volatile SingularAttribute<UsersModel, Long> id;
    public static volatile SingularAttribute<UsersModel, String> name;
    public static volatile SingularAttribute<UsersModel, String> email;
    public static volatile SingularAttribute<UsersModel, String> password;

}
