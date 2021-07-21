package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String mas = req.massage();
        String txt = "";
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(mas, new ConcurrentLinkedQueue<>());
            txt = req.text();
            queue.get(mas).add(txt);
        } else {
            txt = queue.getOrDefault(mas, new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(txt, Resp.STATUS);
    }
}
