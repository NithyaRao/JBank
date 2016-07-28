package com.nrdc;

import java.util.ArrayList;
import java.util.UUID;
import com.nrdc.Transaction;

/**
 * Created by localadmin on 7/28/16.
 */
public class Account {
    private String id;
    private AccountType type;
    private float balance;
    private boolean isClosed;
    private byte overDraftCount;
    private ArrayList<Transaction> transactions;

    public Account(AccountType type) {
        this.id =  UUID.randomUUID().toString();
        this.type = type;
        this.isClosed = false;
        this.transactions = new ArrayList<>();
        this.overDraftCount = 0;
        this.balance = 0f;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", isClosed=" + isClosed +
                ", overDraftCount=" + overDraftCount +
                ", transactions=" + transactions +
                '}';
    }

    public String getId() {
        return id;
    }

    public AccountType getType() {
        return type;
    }

    public float getBalance() {
        return balance;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(float amount) {
        if(this.isClosed()) return;
        this.balance += amount;
        Transaction transaction = new Transaction(amount, TransactionType.DEPOSIT);
        transactions.add(transaction);
    }

   public void withdraw(float amount) {
       if(this.isClosed()) return;
       if ( this.getBalance() >= amount ){
           this.balance -= amount;
           Transaction transaction = new Transaction(amount, TransactionType.WITHDRAW);
           transactions.add(transaction);
       } else {
           Transaction transaction = new Transaction(amount, TransactionType.FEE);
           transactions.add(transaction);
           this.balance -= 50f;
           this.overDraftCount += 1;
       }

       if (this.overDraftCount > 3) {
           this.isClosed = true;
       }
   }

    public Float[] filterTransactions(TransactionType type) {
//        Transaction trans = transactions.stream().filter(t -> t.getType() == TransactionType.DEPOSIT)
//                .toArray(Transaction[]::new);
         return transactions.stream().filter(t -> t.getType() == type)
                .map(a -> a.getAmount()).toArray(Float[]::new);

    }

    public void close() {
        isClosed = true;
        balance = 0f;
    }
}
