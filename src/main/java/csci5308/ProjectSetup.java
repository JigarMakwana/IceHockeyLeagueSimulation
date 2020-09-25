package csci5308;
import java.io.*;
import java.util.Properties;

public class ProjectSetup {
    private String game;
    private boolean isSetup = true;

    public ProjectSetup(String game)
    {
        this.game = game;
    }

    public static void initConfig() throws IOException {
        Properties p = new Properties();
        OutputStream output = new FileOutputStream("config.properties");
        p.setProperty("developDBURL", "localhost");
        p.setProperty("developDBPort", "3306");
        p.setProperty("developDBUserName", "csci5308");
        p.setProperty("developDBPassword", "group11");

        p.setProperty("productionDBURL", "localhost");
        p.setProperty("productionDBPort", "3306");
        p.setProperty("productionDBUserName", "csci5308");
        p.setProperty("productionDBPassword", "group11");

        p.store(output, null);
        System.out.println("Config file created!");
    }

    public static void getConfig() throws IOException {
        Properties p = new Properties();
        InputStream file = new FileInputStream("config.properties");
        p.load(file);

        System.out.println("Config Data:");
        System.out.println("developDBURL: " + p.getProperty("developDBURL"));
        System.out.println("developDBPort: " + p.getProperty("developDBPort"));
        System.out.println("developDBUserName: " + p.getProperty("developDBUserName"));
        System.out.println("developDBPassword: " + p.getProperty("developDBPassword"));
        System.out.println("productionDBURL: " + p.getProperty("productionDBURL"));
        System.out.println("productionDBPort: " +p.getProperty("productionDBPort"));
        System.out.println("productionDBUserName: " + p.getProperty("productionDBUserName"));
        System.out.println("productionDBPassword: " + p.getProperty("productionDBPassword"));
    }

    public String getGame() {
        return game;
    }

    public boolean isSetup() {
        return isSetup;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
//        initConfig();
        getConfig();
    }
}
