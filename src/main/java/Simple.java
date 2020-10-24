import java.io.File;
import java.net.URL;

public class Simple {
    public static void main(String[] args) {
        Simple simple = new Simple();
        simple.getClass().isAnnotationPresent(Component.class);
        Component annotation = simple.getClass().getAnnotation(Component.class);
        ClassLoader classLoader = Simple.class.getClassLoader();
        URL com = classLoader.getResource("com");
        String file = com.getFile();
        File file1 = new File(file);
        file1.listFiles();
        while (true);
    }
}
