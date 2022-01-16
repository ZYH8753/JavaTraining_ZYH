package com.zyh.javatraining.weekthree.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("xjava", "HeaderHttpResponseFilter");
    }
}
