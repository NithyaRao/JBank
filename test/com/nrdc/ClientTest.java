package com.nrdc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class ClientTest {
    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testConstructorWithClientType() throws Exception {
        Client c1 = new Client("Bob");
        assertNotNull(c1.getId());
        assertEquals("Bob", c1.getName());
    }

    @Test
    public void testOpenAccount() throws Exception {
        Client c1 = new Client("Bob");
        c1.openAccount(AccountType.CHECKING);
        assertEquals(1, c1.getAccounts().size());
        assertEquals("Bob", c1.getName());
        assertTrue(c1.isActive());
    }

    @Test
    public void testCloseAccount() throws Exception {
        Client c1 = new Client("Bob");
        c1.openAccount(AccountType.CHECKING);
        c1.openAccount(AccountType.SAVING);
        String id = c1.getAccounts().get(0).getId();
        c1.closeAccount(id);
        assertTrue(c1.getAccounts().get(0).isClosed());
        assertEquals(0f, c1.getAccounts().get(0).getBalance(), 0.1f);
    }

    @Test
    public void testDeActivateAccount() throws Exception {
        Client c1 = new Client("Bob");
        c1.openAccount(AccountType.CHECKING);
        c1.deActivate();
        assertFalse(c1.isActive());
    }
}