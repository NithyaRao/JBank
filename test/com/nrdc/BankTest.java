package com.nrdc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class BankTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorWithBank() {
        Bank b1 = new Bank("BOA");
        assertNotNull(b1.getId());
        assertEquals("BOA", b1.getName());
    }

    @Test
    public void testAddClientToBank(){
        Bank b1 = new Bank("BOA");
        b1.addClient("Bob");
        assertEquals(1, b1.getClients().size());
    }

    @Test
    public void testRemoveClientFromBank(){
        Bank b1 = new Bank("BOA");
        b1.addClient("Bob");
        b1.getClients().get(0).openAccount(AccountType.CHECKING);
        b1.getClients().get(0).openAccount(AccountType.SAVING);
        b1.addClient("Ann");
        b1.removeClient(b1.getClients().get(0).getId());
        assertEquals(2, b1.getClients().size());
        assertFalse(b1.getClients().get(0).isActive());
    }
}