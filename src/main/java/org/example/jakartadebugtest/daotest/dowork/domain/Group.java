package org.example.jakartadebugtest.daotest.dowork.domain;

public class Group implements Identified<Integer> {
    private int id;
    private String department;
    private int number;

    public Group(int id, String department, int number) {
        this.id = id;
        this.department = department;
        this.number = number;
    }

    public Group() {
    }

    public String getDepartment() {
        return department;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public Integer getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }
}
