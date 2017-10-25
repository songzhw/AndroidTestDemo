package cn.six.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.when;


public class InjectMockDemoTest {
    @Mock
    public User user;

    @InjectMocks
    public InjectMockDemo obj;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(user.getName()).thenReturn("szw");
    }

    @Test
    public void testModify(){
        assertEquals("[szw]", obj.modify());
    }
}

