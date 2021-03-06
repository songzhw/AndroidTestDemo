package cn.six.junit;
import android.os.Looper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cn.six.robolectric.BaseRoboTestCase;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginPresenterTest extends BaseRoboTestCase{
    @Mock UserManager mgr;
    @Captor ArgumentCaptor<LoginCallback> captor;

    @Before public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(mgr.isLogin()).thenReturn(false);
    }

    @Test public void testLogin(){
        LoginPresenter presenter = new LoginPresenter(mgr);
        presenter.login();

        verify(mgr).login(eq(UserType.LEGAL), captor.capture());
        captor.getValue().onSucc();

    }



}