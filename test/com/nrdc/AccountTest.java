package com.nrdc;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class AccountTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorWithAccountType() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        assertNotNull(a1.getId());
        assertEquals(0f, a1.getBalance(), 0.1f);
        assertEquals(AccountType.CHECKING, a1.getType());
        assertFalse(a1.isClosed());
        assertEquals(0, a1.getTransactions().size());
    }

    @Test
    public void testToString() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        assertThat(a1.toString(), CoreMatchers.containsString("type=CHECKING, balance=0.0"));
    }

    @Test
    public void testDeposit() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        assertEquals(a1.getBalance(), 100f, 0.1f);
        assertEquals(1, a1.getTransactions().size());
     }

    @Test
    public void testWithdrawHasEnoughBalance() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.withdraw(50f);
        assertEquals(a1.getBalance(), 50f, 0.1f);
        assertEquals(2, a1.getTransactions().size());
    }
    @Test
    public void testWithdrawdoesnothaveEnoughBalance() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.withdraw(50f);
        a1.withdraw(100);
        assertEquals(a1.getBalance(), 0f, 0.1f);
        assertEquals(3, a1.getTransactions().size());
    }

    @Test
    public void testFilterTransactionsReturnDepositAmountsinArray() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.deposit(50f);
        a1.deposit(50f);
        a1.deposit(200f);
        a1.withdraw(50f);
        a1.withdraw(25f);
        assertEquals(6, a1.getTransactions().size());
//        a1.filterTransactions(TransactionType.DEPOSIT);
        assertArrayEquals(new Float[]{100f,50f,50f,200f}, a1.filterTransactions(TransactionType.DEPOSIT));
    }
    @Test
    public void testCloseAccount() throws Exception {
        Account a1 = new Account(AccountType.CHECKING);
        a1.deposit(100f);
        a1.close();
        assertTrue(a1.isClosed());
        assertEquals(0f, a1.getBalance(), 0.1f);
    }
}