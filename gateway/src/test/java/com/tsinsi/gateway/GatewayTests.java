package com.tsinsi.gateway;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class GatewayTests {

    @Test
    public void testA() {
        List<String> list = Collections.emptyList();
        System.out.println(list.get(0));
    }

}
