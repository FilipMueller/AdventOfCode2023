package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
    private static final int SIZE = 140;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\Day3\\PuzzleInput.txt"));
        char[][] input = inputToCharArray(br);
        System.out.println(puzzleOne(input));
        System.out.println(puzzleTwo(input));
    }

    private static char[][] inputToCharArray(BufferedReader br) throws IOException {
        String line;
        char[][] input = new char[SIZE][SIZE];
        int i = 0;

        while ((line = br.readLine()) != null) {
            int j = 0;
            for (char c : line.toCharArray()) {
                input[i][j] = c;
                j++;
            }
            i++;
        }
        return input;
    }

    private static int puzzleOne(char[][] input) {
        List<Integer> columnNumbers = new ArrayList<>();
        int sum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (Character.isDigit(input[row][column])) {
                    columnNumbers.add(column);
                    if (column + 1 == SIZE || !(input[row][column + 1] >= '0' && input[row][column + 1] <= '9')) {
                        for (int innerRow = Math.max(0, row - 1); innerRow < Math.min(SIZE, row + 2); innerRow++) {
                            for (int innerColumn = Math.max(0, columnNumbers.get(0) - 1); innerColumn < Math.min(SIZE, columnNumbers.get(columnNumbers.size() - 1) + 2); innerColumn++) {
                                if (input[innerRow][innerColumn] != '.' && !Character.isDigit(input[innerRow][innerColumn])) {
                                    sum += Integer.parseInt(new String(input[row], columnNumbers.get(0), columnNumbers.size()));
                                    innerRow = row + 2;
                                    innerColumn = columnNumbers.get(columnNumbers.size() - 1) + 2;
                                }
                            }
                        }
                        columnNumbers.clear();
                    }
                }
            }
        }
        return sum;
    }

    private static int puzzleTwo(char[][] input) {
        List<Integer> adjacentNumbers= new ArrayList<>();
        int sum = 0;

        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (input[row][column] == '*') {
                    for (int innerRow = Math.max(0, row - 1); innerRow < Math.min(SIZE, row + 2); innerRow++) {
                        int numbersFound = 0;
                        for (int innerColumn = Math.max(0, column - 1); innerColumn < Math.min(SIZE, column + 2); innerColumn++) {
                            if (Character.isDigit(input[innerRow][innerColumn])) {
                                numbersFound++;
                                if (numbersFound > 2) {
                                    innerRow = row + 2;
                                    innerColumn = column + 2;
                                } else {
                                    int number = getNumber(input[innerRow], innerColumn);
                                    adjacentNumbers.add(number);
                                }
                                while (Character.isDigit(input[innerRow][innerColumn])) {
                                    innerColumn++;
                                    if (innerColumn == SIZE) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (adjacentNumbers.size() == 2) {
                        sum += adjacentNumbers.get(0) * adjacentNumbers.get(1);
                    }
                    adjacentNumbers.clear();
                }
            }
        }
        return sum;
    }

    private static int getNumber(char[] row, int index) {
        int temp = index;
        int start = index;
        int end = index;
        while (row[temp] >= '0' && row[temp] <= '9') {
            start = temp;
            temp--;
            if (temp == -1) {
                break;
            }
        }
        temp = index;
        while (row[temp] >= '0' && row[temp] <= '9') {
            end = temp;
            temp++;
            if (temp == row.length) {
                break;
            }
        }
        end = end - start + 1;
        return Integer.parseInt(new String(row, start, end));
    }
}
