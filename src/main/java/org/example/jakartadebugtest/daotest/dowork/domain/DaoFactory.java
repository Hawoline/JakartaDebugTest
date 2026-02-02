package org.example.jakartadebugtest.daotest.dowork.domain;



public interface DaoFactory<Context> {
    interface DaoCreator<Context> {
        Dao getDao(Context context);
    }

    Context getContext();

    Dao<Student, Integer> getStudentDao(Context context);

    Dao<Group, Integer> getGroupDao(Context context);

    Dao getDao(Context context);
}