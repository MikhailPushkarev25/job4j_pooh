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
        String method = null;
        String text = null;
        String mode = null;
        String name = null;
        text = context;
        String[] parse = context.split(" ");
        method = parse[0];
        String[] str = parse[1].split("/");
        if (str.length > 2) {
            mode = str[1];
            name = str[2];
        }
        if (str.length > 3) {
            arg = str[3];
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
