package ru.atnagullova.tennis_score.match_state;

public enum Point {
    LOVE ("0"),
    FIFTEEN ("15"),
    THIRTY ("30"),
    FORTY ("40"),
    AD ("AD");

    private String value;

    Point(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Point next() {
        return switch (this) {
            case LOVE -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            case FORTY -> AD;
            case AD -> LOVE;
        };
    }

    public Point stepBack() {
        return this == AD ? FORTY : this;
    }
}
