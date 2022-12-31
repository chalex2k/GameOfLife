package ru.vsu.cs.course1.game;

import java.util.Random;

/**
 * Класс, реализующий логику игры
 */

public class Game {
    /**
     * объект Random для генерации случайных чисел
     * (можно было бы объявить как static)
     */
    private final Random rnd = new Random();

    /**
     * двумерный массив для хранения игрового поля
     * (в данном случае цветов, 0 - пусто; создается / пересоздается при старте игры)
     */
    private boolean[][] field = null;
    /**
     * Максимальное кол-во цветов
     */
    private int colorCount = 0;

    public Game() {
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        // создаем поле
        field = new boolean[rowCount][colCount];
        this.colorCount = colorCount;
    }

    public void switchCell(int row, int col) {
        int rowCount = getRowCount(), colCount = getColCount();
        if (row < 0 || row >= rowCount || col < 0 || col >= colCount) {
            return;
        }
        field[row][col] = !field[row][col];
    }

    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

    public boolean getCell(int row, int col) {
        if (row < 0 || row >= getRowCount() || col < 0 || col >= getColCount())
            throw new IndexOutOfBoundsException();
        return field[row][col];
    }

    public void next_generation() {
        boolean[][] newField =  new boolean[field.length][field[0].length];
        for(int r = 0; r < field.length; r++) {
            for(int c = 0; c < field[0].length; c++) {
                int countAliveNeighbors = 0;
                for(int dr = -1; dr <= 1; dr++){
                    for(int dc = -1; dc <= 1; dc++){
                        if (dr == 0 && dc == 0)
                            continue;
                        try {
                            if (getCell(r + dr, c + dc))
                                countAliveNeighbors++;
                        } catch (IndexOutOfBoundsException ignored){}

                    }
                }
                if (countAliveNeighbors == 3){
                    newField[r][c] = true;
                }
                else if (countAliveNeighbors == 2){
                    newField[r][c] = field[r][c];
                }
                else {
                    newField[r][c] = false;
                }

            }

        }

        field = newField;
    }
}
