package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\Day4\\PuzzleInput.txt"));
        System.out.println(puzzleOne(bufferedReader));
    }

    private static int puzzleOne(BufferedReader bufferedReader) throws IOException {
        String line;
        int points = 0;

        while ((line = bufferedReader.readLine()) != null) {
            line = line.substring(line.indexOf(":") + 2).trim();

            String[] winningNumbers = line.substring(0, line.lastIndexOf("|")).split("\\s+");
            String[] myNumbers = line.substring(line.lastIndexOf("|") + 2).split("\\s+");

            int matchingNumbers = (int) Arrays.stream(myNumbers)
                    .filter(num -> Arrays.asList(winningNumbers).contains(num))
                    .count();

            int score = (matchingNumbers > 1) ? (int) Math.pow(2, matchingNumbers - 1) : 1;
            points += (matchingNumbers > 0) ? score : 0;
        }
        return points;
    }
}
