package com.zyh.javatraining.weekthree.gateway.router;

import java.util.List;
import java.util.Random;

public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {
    private int cnt = 0;
    @Override
    public String route(List<String> urls) {
        cnt++;
        return urls.get(cnt=(cnt % urls.size()));
    }
}
