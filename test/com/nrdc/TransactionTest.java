package com.nrdc;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by localadmin on 7/28/16.
 */
public class TransactionTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testConstructorWithAmountAndType() throws Exception {
        Transaction t = new Transaction(100f, TransactionType.DEPOSIT);
        assertNotNull(t.getId());
        assertNotNull(t.getDate());
        assertEquals(100f, t.getAmount(), 0.1f);
        assertEquals(TransactionType.DEPOSIT, t.getType());
    }

    @Test
    public void testToString() throws Exception {
        Transaction t = new Transaction(100f, TransactionType.DEPOSIT);
        assertThat(t.toString(), CoreMatchers.containsString( "amount=100.0, type=DEPOSIT" ));
    }
}