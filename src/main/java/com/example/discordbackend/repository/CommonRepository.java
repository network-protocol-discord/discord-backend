package com.example.discordbackend.repository;

import com.example.discordbackend.exception.BaseException;
import com.example.discordbackend.model.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Slf4j
public abstract class CommonRepository<E extends BaseEntity<I>, I> {

    private final Class<E> entityClass;

    @PersistenceContext
    private EntityManager em;

    protected CommonRepository(final Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E findById(final I id) {
        return em.find(entityClass, id);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public E save(final E entity) {
        //새로운 Entity 생성 (create)
        if(entity.getId() == null) {
            try{
                em.persist(entity);
            } catch (final BaseException e) {
                log.warn("create failed on entity {}", entity);
                throw e;
            }
        }
        //기존 엔티티 변경 (update)
        else {
            try {
                em.merge(entity);
            } catch (final BaseException e) {
                log.warn("update failed on entity {}", entity);
                throw e;
            }
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(final E entity) {
        try {
            em.remove(entity);
        } catch (final BaseException e) {
            log.warn("delete failed on entity {}", entity);
            throw e;
        }
    }
}
