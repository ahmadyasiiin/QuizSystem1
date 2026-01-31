package quizapp.model;

public enum Difficulty {
    EASY(5),
    MEDIUM(10),
    HARD(20);

    private final int point;

    Difficulty(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}

