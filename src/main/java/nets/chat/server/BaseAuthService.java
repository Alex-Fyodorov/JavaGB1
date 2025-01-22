package nets.chat.server;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService{

    private class Entry {
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private List<Entry> entries;

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен.");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен.");
    }

    @Override
    public String getNickByLoginAndPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) {
                return o.nick;
            }
        }
        return null;
    }

    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("l1", "p1", "nick1"));
        entries.add(new Entry("l2", "p2", "nick2"));
        entries.add(new Entry("l3", "p3", "nick3"));
    }

    @Override
    public boolean changeNick(String name, String nick) {
        return false;
    }
}
