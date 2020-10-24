package luban.jdk;

public class Study implements Cloneable{
    private String math;

    private String chinese;

    private Score so;

    public Score getSo() {
        return so;
    }

    public void setSo(Score so) {
        this.so = so;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public Object clone() throws CloneNotSupportedException {
        Study s = (Study) super.clone();
        return s;
    }


}
