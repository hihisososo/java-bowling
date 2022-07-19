package bowling.domain;

public enum Status {
    STRIKE("X"), HIT(""), SPARE("/"), MISS(""), GUTTER("-");

    private final String expression;

    Status(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
