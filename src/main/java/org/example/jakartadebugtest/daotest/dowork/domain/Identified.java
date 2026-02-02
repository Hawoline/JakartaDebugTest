package org.example.jakartadebugtest.daotest.dowork.domain;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    public PK getId();
}