package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicService implements Service {
    private final AtomicInteger integer = new AtomicInteger(1);
    private final ConcurrentHashMap<String, ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>>>
            queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String massage = req.massage();
        String text = "";
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(massage, new ConcurrentHashMap<>());
            text = req.text();
            queue.get(massage).putIfAbsent(integer.getAndIncrement(), new ConcurrentLinkedQueue<>());
            queue.get(massage).get(integer.get()).add(text);
        } else {
            text = queue.getOrDefault(massage, new ConcurrentHashMap<>())
                    .getOrDefault(Integer.valueOf(req.arg()), new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, Resp.STATUS);
    }
}
