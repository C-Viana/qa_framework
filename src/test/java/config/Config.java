package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = load();

    private Config() {}

    public static String get(String key) {
        String override = System.getProperty(key);   // permite -Dtest.environment=QA
        return override != null ? override : PROPS.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    private static Properties load() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível carregar config.properties", e);
        }
    }
}
