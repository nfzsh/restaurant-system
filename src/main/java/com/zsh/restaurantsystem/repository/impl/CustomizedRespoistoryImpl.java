package com.zsh.restaurantsystem.repository.impl;

import com.zsh.restaurantsystem.repository.CustomizedRepoistory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class CustomizedRespoistoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomizedRepoistory<T, ID> {
    private EntityManager entityManager;

    public CustomizedRespoistoryImpl(JpaEntityInformation entityInformation,
                                     EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public T refresh(T t) {
        entityManager.refresh(t);
        return t;
    }


}