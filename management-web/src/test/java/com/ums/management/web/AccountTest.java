package com.ums.management.web;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Rex on 2016/8/23.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/management*.xml"
})
public class AccountTest {
    @Test
    public void getAllUsers(){
    }
}
