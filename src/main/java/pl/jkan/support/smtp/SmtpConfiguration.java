package pl.jkan.support.smtp;

public class SmtpConfiguration {
    private String serverAddress;
    private String user;
    private String password;
    private String port;

    public SmtpConfiguration(String serverAddress, String user, String password, String port) {
        this.serverAddress = serverAddress;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }
}
