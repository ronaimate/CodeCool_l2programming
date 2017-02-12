package proxy_pattern;

public class CommanderProxy extends Commander{
    private Commander commander;
    public CommanderProxy() {
        commander = new DataCommander();
    }

    @Override
    String receive() {
        System.out.print("COMMANDERPROXY: very important checking");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return commander.receive();
    }
}
