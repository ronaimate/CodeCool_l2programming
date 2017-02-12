package proxy_pattern;

public class DataCommander extends Commander {
    @Override
    String receive() {
        return "DATACOMMANDER: receive";
    }
}
