package cn.six.http;

import android.widget.Button;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;

import ca.six.aut.robolectric.BaseRoboTestCase;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by songzhw on 2016-10-26
 */
public class HttpDemoTest extends BaseRoboTestCase {
    @Captor private ArgumentCaptor<Callback> captor;
    @Mock HttpEngine http;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onClick() throws Exception {
        HttpDemo actv = Robolectric.setupActivity(HttpDemo.class);
        actv.setHttpEngine(http);
        actv.btn.performClick();

        verify(http).request(anyString(), captor.capture());

        ResponseBody body = ResponseBody.create(null, "abc");
        Response resp = new Response.Builder().body(body).build();

        captor.getValue().onResponse(null, resp);

        assertEquals("abc", actv.btn.getText());
        MockResponse resp2 ;
    }
}