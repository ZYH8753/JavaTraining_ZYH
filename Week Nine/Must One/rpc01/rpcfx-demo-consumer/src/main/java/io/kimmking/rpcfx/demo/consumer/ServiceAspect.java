package io.kimmking.rpcfx.demo.consumer;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.api.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;


@Aspect
@Component
public class ServiceAspect {
    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    @Autowired
    private NettyClient nettyClient;

//    @Pointcut("execution(* io.kimmking.rpcfx.demo.consumer.UserServiceImpl.*(..))")
    @Pointcut("execution(* io.kimmking.rpcfx.demo.api.UserService.*(..))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Class implClass = target.getClass();
        Class[] interfaces = implClass.getInterfaces();

        String methodName = null;
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            methodName = signature.getName();
        }

        if (methodName == null) {
            return null;
        }

        Class targetClass = null;
        for (Class klass : interfaces) {
            String finalMethodName = methodName;
            if (Arrays.stream(klass.getMethods()).anyMatch(k -> finalMethodName.equals(k.getName()))) {
                targetClass = klass;
                break;
            }
        }

        if (targetClass == null) {
            return null;
        }


        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(targetClass.getName());
        request.setMethod(methodName);
        request.setParams(joinPoint.getArgs());

        RpcfxResponse response = null;
        try {
            response = nettyClient.getRpcfxResponse(request);
//            response = post(request, "http://localhost:8081/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response != null ? JSON.parseObject(response.getResult().toString(), User.class) : null;
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

}
