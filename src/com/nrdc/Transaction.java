package com.nrdc;

import java.util.Date;
import java.util.UUID;

/**
 * Created by localadmin on 7/28/16.
 */
public class Transaction {
    private String id;
    private Date date;
    private float amount;
    private TransactionType type;
    public void deleteme(){

    }

    public Transaction(float amount, TransactionType type) {
        this.amount = amount;
        this.type = type;
        this.id = UUID.randomUUID().toString();
//        Random rand = new Random();
//        id = String.valueof(Math.abs(rand.nextLong()));
        this.date = new Date();
      }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }

    public String getId() {
        return id;
    }


    public Date getDate() {
        return date;
    }


    public float getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
