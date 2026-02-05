package org.example.jakartadebugtest.daotest.dowork.domain;

import java.util.Date;
import java.util.Objects;

public final class Student implements Identified<Integer> {
    private int id;
    private String name;
    private int groupId;
    private Date enrollmentDate;
    private String surname;

    public Student(int id, String name, int groupId, Date enrollmentDate, String surname) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.enrollmentDate = enrollmentDate;
        this.surname = surname;
    }

    public Student() {
    }

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

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public int groupId() {
        return groupId;
    }

    public Date enrollmentDate() {
        return enrollmentDate;
    }

    public String surname() {
        return surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Student) obj;
        return this.id == that.id &&
                Objects.equals(this.name, that.name) &&
                this.groupId == that.groupId &&
                Objects.equals(this.enrollmentDate, that.enrollmentDate) &&
                Objects.equals(this.surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, groupId, enrollmentDate, surname);
    }

}
