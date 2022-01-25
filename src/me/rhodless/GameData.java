package me.rhodless;

import me.rhodless.binding.Character;

public class GameData {

    private static Character character;


    public static Character getCharacter() {
        return character;
    }

    public static void setCharacter(Character character) {
        GameData.character = character;
    }
}
