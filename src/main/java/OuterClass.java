import java.lang.String;

public class OuterClass implements Cloneable{

    public String attr;

    public OuterClass(String attr) {
        this.attr = attr;
    }

    public String getAttr(){
        return attr;
    }

    public OuterClass clone() throws CloneNotSupportedException{
        return (OuterClass) super.clone();
    }

    class InnerClass{

        public String attr;

        public InnerClass(String attr) {
            this.attr = attr;
        }

        public String getAttr(){
            return attr;
        }
        public String getAttrO(){
            return OuterClass.this.attr;
        }
        public String getAttrO2(){
            return OuterClass.this.getAttr();
        }
    }

    static class StaticInnerClass{
        static String getStaticInfo(){
            return "static";
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        OuterClass out = new OuterClass("out");
        InnerClass inner = out.new InnerClass("inner");

        OuterClass clone = out.clone();
        System.out.println("is: "+out.getAttr());
        System.out.println("is: "+out.attr);
        System.out.println("is: "+inner.getAttr());
        System.out.println("is: "+inner.getAttrO());
        System.out.println("is: "+inner.getAttrO2());
       // System.out.println("is: "+out.StaticInnerClass.getStaticInfo());
        System.out.println("is: "+OuterClass.StaticInnerClass.getStaticInfo());
    }
}
