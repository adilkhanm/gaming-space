package com.example.gaming_space.model;

public enum GameType {
    TETRIS("tetris"),
    MINESWEEPER("minesweeper"),
    CITIES("cities");

    private String value;

    GameType(String value) {
        this.value = value;
    }
}
