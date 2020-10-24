package java.lang;
public class String {
    public static void main(String[] args) {
        System.out.printf(""+String.class.getClassLoader());
        String.show();
    }

    public static void show(){
        System.out.printf("dfs");
    }
}
