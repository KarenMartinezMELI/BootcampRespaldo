package dom.ej.clase23.dto;

public class SubjectDTO {
    String name;
    int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
