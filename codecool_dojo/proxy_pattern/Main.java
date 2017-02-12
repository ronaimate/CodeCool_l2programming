package proxy_pattern;

public class Main {
    public static void main(String[] args) {
        Commander commander = new DataCommander();
        System.out.print(commander.receive());

        Commander commanderTwo = new CommanderProxy();
        System.out.print(commanderTwo.receive());
    }
}
