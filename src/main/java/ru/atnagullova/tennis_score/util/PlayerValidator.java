package ru.atnagullova.tennis_score.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerValidator {

    public static boolean isValidPlayerName(String name) {
        return name != null && !name.isBlank();
    }



}
