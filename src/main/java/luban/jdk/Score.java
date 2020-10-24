package luban.jdk;

public class Score{

    private int score;
    private String score1;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public static void main(String[] args) {
        Integer i1 = new Integer(100);
        Integer i2 = new Integer(100);
        System.out.println(i1.equals(i2));
    }
}
