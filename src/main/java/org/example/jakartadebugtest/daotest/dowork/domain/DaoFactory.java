package org.example.jakartadebugtest.daotest.dowork.domain;


import org.example.jakartadebugtest.daotest.dowork.data.PersistException;

public interface DaoFactory<Context> {
    interface DaoCreator<Context> {
        Dao create(Context context);
    }

    Context getContext();

    Dao<Student, Integer> getStudentDao(Context context);

    Dao<Group, Integer> getGroupDao(Context context);

    Dao getDao(Context context, Class dtoClass) throws PersistException;
}