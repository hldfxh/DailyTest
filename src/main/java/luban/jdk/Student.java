package luban.jdk;

import java.io.Serializable;

public class Student implements Cloneable {

    //private static final long serialVersionUID = 3176884157478865770L;
    private String name;
    private int age;
    private Study study;
    private int[] i;
    private String[] s;
    private int score;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Student s = (Student)super.clone();
        s.setStudy((Study)this.study.clone());
        return s;
    }

    public int[] getI() {
        return i;
    }

    public void setI(int[] i) {
        this.i = i;
    }

    public String[] getS() {
        return s;
    }

    public void setS(String[] s) {
        this.s = s;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}
