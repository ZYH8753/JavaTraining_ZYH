package mymq.core;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Mymq {

    public Mymq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue(capacity);
    }

    private String topic;

    private int capacity;

    private LinkedBlockingQueue<MymqMessage> queue;

    public boolean send(MymqMessage message) {
        return queue.offer(message);
    }

    public MymqMessage poll() {
        return queue.poll();
    }

    @SneakyThrows
    public MymqMessage poll(long timeout) {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

}
