package ru.job4j.pooh;

public class Req {

    private static String arg;
    private final String method;
    private final String mode;
    private final String text;
    private final String massage;

    private Req(String method, String mode, String text, String massage) {
        this.method = method;
        this.mode = mode;
        this.text = text;
        this.massage = massage;
    }

    public static Req of(String context) {
        String method = "";
        String mode = "";
        String text = "";
        String name = "";
        text = context;
        String[] first = context.split(" ");
        method = first[0];
        String[] parse = first[1].split("/");
        if (parse.length > 2) {
            mode = parse[1];
            name = parse[2];
        }
        if (parse.length > 3) {
            arg = parse[3];
        }
        return new Req(method, mode, text, name);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }
    public  String massage() {
        return massage;
    }
    public String arg() {
        return arg;
    }
}
