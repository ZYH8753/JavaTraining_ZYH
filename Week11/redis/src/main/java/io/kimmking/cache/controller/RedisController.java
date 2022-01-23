package io.kimmking.cache.controller;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class RedisController {
    private static final String KEY_LOCK = "key_test";

    private static final String KEY_STOCK = "a";

    private static final int MAX_TIMES_TO_LOCK_KEY = 5;

    private static final int TRY_LOCK_KEY_MILLIS_PER_TIME = 1000;

    private static final int LOCK_KEY_EXPIRED_MILLIS = 5000;

    public static final String PUBLISH_ORDER_TOPIC = "topic";

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("redisLock")
    public String getRedisLock() throws InterruptedException {
        long lockValue = System.currentTimeMillis();
        ValueOperations operations = redisTemplate.opsForValue();
        for (int i = 1; i <= MAX_TIMES_TO_LOCK_KEY; i++) {
            if (operations.setIfAbsent(KEY_LOCK, String.valueOf(lockValue), LOCK_KEY_EXPIRED_MILLIS, TimeUnit.MILLISECONDS)) {

                System.out.println("hanppen sth...");

                String lua = "local cur = redis.call('get', KEYS[1]) \n "
                        + "if cur == ARGV[1] then \n"
                        + "redis.call('del', KEYS[1]) \n"
                        + "return 1 \n"
                        + "end \n"
                        + "return 0 \n";
                RedisScript<Long> script = new DefaultRedisScript<>(lua, Long.class);
                List<String> keyList = new ArrayList<>();
                keyList.add(KEY_LOCK);
                Long ans = (Long) redisTemplate.execute(script, redisTemplate.getStringSerializer(),
                        redisTemplate.getStringSerializer(), keyList, String.valueOf(lockValue));
                if (ans != null && ans == 1) {
                    return "success";
                }
                System.out.println("release lock failed. rollback!");
                break;
            }
            Thread.sleep(TRY_LOCK_KEY_MILLIS_PER_TIME);
        }
        return "failed";
    }

    @RequestMapping("redisDecr")
    public String decrRedis() throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000));
        ValueOperations operations = redisTemplate.opsForValue();
        Long ans = operations.decrement(KEY_STOCK, 1);
        System.out.println(ans);
        if (ans < 0) {
            return "failed";
        }
        return String.valueOf(ans);
    }

    @RequestMapping("pubOrder")
    public String pubOrder() {
        redisTemplate.convertAndSend(PUBLISH_ORDER_TOPIC, "order");
        return "ok";
    }


    @RequestMapping("redisCluster")
    public String redisCluster() {
//        ClusterJedis.getJedisCluster().set("a","1");
//        ClusterJedis.getJedisCluster().set("b","2");
        System.out.println(redisTemplate.opsForValue().get("a"));
        return "ok";
    }

    @RequestMapping("redissonCluster")
    public String redissonCluster(@RequestParam String a) {
//        ClusterJedis.getJedisCluster().set("a","1");
//        ClusterJedis.getJedisCluster().set("b","2");
        RMap<String, String> map1 = redissonClient.getMap("map1");
        map1.put("bbb", a);
        return "ok";
    }



}
