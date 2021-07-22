package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String massage = req.massage();
        String text = "";
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(massage, new ConcurrentLinkedQueue<>());
            text = req.text();
            queue.get(massage).add(text);
        } else {
            text = queue.getOrDefault(massage, new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, Resp.STATUS);
    }
}
