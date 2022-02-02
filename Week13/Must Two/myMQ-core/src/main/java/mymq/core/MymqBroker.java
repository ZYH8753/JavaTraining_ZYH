package mymq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MymqBroker {
    public static final int CAPACITY = 10000;

    private final Map<String, Mymq> kmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name) {
        kmqMap.putIfAbsent(name, new Mymq(name, CAPACITY));
    }

    public Mymq findKmq(String topic) {
        return this.kmqMap.get(topic);
    }

    public MymqResponse send(MymqMessage message) {
        Mymq mymq = findKmq(message.getTopic());
        if (mymq == null) {
            createTopic(message.getTopic());
        }
        findKmq(message.getTopic()).send(message);
//        if (null == mymq) throw new RuntimeException("Topic[" + message.getTopic() + "] doesn't exist.");
        MymqResponse res = new MymqResponse(true, "produce message is successful! topic=" + message.getTopic() + ", message=" + message.getMessage(), null);
        return res;
    }

    public MymqResponse consume(MymqMessage message) {
        Mymq mymq = findKmq(message.getTopic());
        MymqResponse res;
        if (mymq == null) {
            res = new MymqResponse(false, "consume message failed! topic=" + message.getTopic(), null);
        } else {
            res = new MymqResponse(true, "consume message is successful! topic=" + message.getTopic(), mymq.poll(1000));
        }
        return res;
    }

}
