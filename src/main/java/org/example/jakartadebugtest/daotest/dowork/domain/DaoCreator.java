package org.example.jakartadebugtest.daotest.dowork.domain;

public interface DaoCreator<Context> {
    Dao getDao(Context context);

}
