package questions;

import java.util.Map;

public enum Difficulty {
    ALL(0),
    EASY(1),
    MEDIUM(2),
    HARD(3);

    public final int value;

    Difficulty(int value) {
        this.value = value;
    }

    public static Difficulty getDeifficultt(int inputValue) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.value == inputValue) {
                return difficulty;
            }
        }
        return null;
    }
}
