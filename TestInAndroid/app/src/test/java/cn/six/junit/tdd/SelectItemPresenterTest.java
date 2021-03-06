package cn.six.junit.tdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelectItemPresenterTest {

     // {1, 3, 5, 7, 9, 11, 12, 13, 15, 17, 19, 20};

    @Test
    public void testThreeTimes(){
        SelectItemPresenter p = new SelectItemPresenter();
        int[] ret = p.threeTimes();
        for(int i : ret){
            assertTrue( i % 3 == 0);
        }

        assertEquals(4, p.getSelected().length);
    }

    @Test
    public void testThreeFiveTimes(){
        SelectItemPresenter p = new SelectItemPresenter();
        int[] tmp = p.threeTimes();
        int[] ret = p.fiveTimes();
        for(int i : ret){
            assertTrue( i % 3 == 0);
            assertTrue( i % 5 == 0);
        }
        assertEquals(15, p.getSelected()[0]);
        assertEquals(1, p.getSelected().length);
    }

}