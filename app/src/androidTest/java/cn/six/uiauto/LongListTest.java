package cn.six.uiauto;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by songzhw on 2016/2/17
 */
@RunWith(AndroidJUnit4.class)
public class LongListTest {
    UiDevice device;
    @Before
    public void setup(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testList() throws UiObjectNotFoundException {
        device.pressHome();
        UiObject apps = device.findObject( new UiSelector().description("Apps"));
        apps.clickAndWaitForNewWindow();
        UiObject myApp = device.findObject(new UiSelector().text("AndroidAutoTest"));
        myApp.clickAndWaitForNewWindow();


        UiScrollable list = new UiScrollable(new UiSelector().resourceId("cn.six.aut:id/lv_list"));
        // public UiObject getChildByText(UiSelector childPattern, String text, boolean allowScrollSearch)
        UiObject item13 = list.getChildByText(new UiSelector().className("android.widget.TextView"), "item: 13", true);
        item13.click();

        UiObject top = device.findObject(new UiSelector().resourceId("cn.six.aut:id/tv_list_selection_row_value"));
        Assert.assertEquals("13", top.getText());

    }

}