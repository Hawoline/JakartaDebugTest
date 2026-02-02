package org.example.jakartadebugtest.daotest.dowork.domain;

import org.example.jakartadebugtest.daotest.dowork.data.PersistException;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Identified<PK>, PK extends Serializable> {
    T create();

    T persist(T object) throws PersistException;

    T getByPk(int id) throws PersistException;

    void update(T object) throws PersistException;

    void delete(T object) throws PersistException;

    List<T> getAll() throws PersistException;
}
