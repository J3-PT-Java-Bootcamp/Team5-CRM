package com.ironhack.data;

import com.ironhack.data.datasources.Datasource;

public class ContactRepository {

    private static ContactRepository instance;
    private final Datasource datasource;

    private ContactRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public static ContactRepository getInstance(Datasource datasource) {
        if(instance == null) {
            instance = new ContactRepository(datasource);
        }
        return instance;
    }

    public int getMaxContactId() {
        return datasource.getMaxLeadId();
    }
}
