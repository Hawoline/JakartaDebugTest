package org.example.jakartadebugtest.daotest.mydao;

public interface MyDao<B, I> {
    void create(B business);
    void update(B business);

    void delete(B business);

    B get(I id);
}
