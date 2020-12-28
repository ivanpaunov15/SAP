package com.ivan.ShopManagement.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {

    @Test
    void getId() {
        Statistics statistics = new Statistics("Mbot",2);
        assertEquals(0,statistics.getId());
    }

    @Test
    void setId() {
        Statistics statistics = new Statistics("Mbot",2);
        statistics.setId(1);
        assertEquals(1,statistics.getId());
    }

    @Test
    void getProduct() {
        Statistics statistics = new Statistics("Mbot",2);
        assertEquals("Mbot",statistics.getProduct());
    }

    @Test
    void setProduct() {
        Statistics statistics = new Statistics("Mbot",2);
        statistics.setProduct("Lego");
        assertEquals("Lego",statistics.getProduct());
    }

    @Test
    void getQuantity() {
        Statistics statistics = new Statistics("Mbot",2);
        assertEquals(2,statistics.getQuantity());
    }

    @Test
    void setQuantity() {
        Statistics statistics = new Statistics("Mbot",2);
        statistics.setQuantity(3);
        assertEquals(3,statistics.getQuantity());
    }
    @Test
    public void testDefaultConstructor(){
       Statistics statistics = new Statistics();

        assertNotNull(statistics);
    }
}