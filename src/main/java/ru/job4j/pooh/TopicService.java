package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicService implements Service {
    private final AtomicInteger integer = new AtomicInteger(1);
    private final ConcurrentHashMap<String,
            ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>>> queue =
            new ConcurrentHashMap<>();
    @Override
    public Resp process(Req req) {
        String mas = req.massage();
        String text = null;
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(mas, new ConcurrentHashMap<>());
            text = req.text();
            queue.get(mas).putIfAbsent(integer.getAndIncrement(), new ConcurrentLinkedQueue<>());
            queue.get(mas).get(integer.get()).add(text);
        } else {
            text = queue.getOrDefault(mas, new ConcurrentHashMap<>())
                    .getOrDefault(Integer.valueOf(req.arg()), new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, Resp.STATUS);
    }
}
