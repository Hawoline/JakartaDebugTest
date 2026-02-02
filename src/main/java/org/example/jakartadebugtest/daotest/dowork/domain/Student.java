package org.example.jakartadebugtest.daotest.dowork.domain;

import java.util.Date;

public record Student(int id, String name, int groupId, Date enrollmentDate, String surname)
        implements Identified<Integer> {

    @Override
    public String toString() {
        return "Student[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "groupId=" + groupId + ", " +
                "enrollmentDate=" + enrollmentDate + ", " +
                "surname=" + surname + ']';
    }

    @Override
    public Integer getId() {
        return id;
    }
}
