package com.nrdc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by localadmin on 7/28/16.
 */
public class Client {
    private String id;
    private String name;
    private boolean isActive;
    private ArrayList<Account> accounts;

    public Client(String name){
        this.id =  UUID.randomUUID().toString();
        this.name = name;
        this.accounts = new ArrayList<>();
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void openAccount(AccountType type) {
        Account a = new Account(type);
        accounts.add(a);
    }

    public void closeAccount(String id) {
        Optional<Account> account = accounts.stream().filter(a -> a.getId().equals(id)).findFirst();
        account.ifPresent(a -> a.close());
    }

    public void deActivate() {
        isActive = false;
    }
}
