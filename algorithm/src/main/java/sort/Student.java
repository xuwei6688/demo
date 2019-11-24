package main.java.sort;

/**
 * @Author xuwei
 * @Date 2019-11-24
 * @Version V1.0
 **/
public class Student implements Comparable<Student>{
    private String name;
    private Float score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        return score.compareTo(o.score);
    }
}
