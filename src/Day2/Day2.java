package Day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        int puzzleOne = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\Day2\\PuzzleInput.txt"))
                .lines()
                .mapToInt(Day2::gameIsPossible)
                .sum();

        System.out.println(puzzleOne);

        int puzzleTwo = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\Day2\\PuzzleInput.txt"))
                .lines()
                .mapToInt(Day2::powerOfSets)
                .sum();

        System.out.println(puzzleTwo);
    }

    private static int gameIsPossible(String line) {
        int gameNumber = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.lastIndexOf(":")));
        line = line.substring(line.lastIndexOf(": ") + 2);

        String[] game = line.split("; ");

        for (String set : game) {
            int red = 0;
            int green = 0;
            int blue = 0;

            for (String draw : set.split(", ")) {
                int currentInt = Integer.parseInt(draw.substring(0,draw.indexOf(" ")));
                String currentColor = draw.substring(draw.indexOf(" ") + 1);

                switch (currentColor) {
                    case "red" -> red += currentInt;
                    case "green" -> green += currentInt;
                    case "blue" -> blue += currentInt;
                }
                if (red > 12 || green > 13 || blue > 14) {
                    return 0;
                }
            }
        }
        return gameNumber;
    }

    private static int powerOfSets(String line) {
        line = line.substring(line.lastIndexOf(": ") + 2);
        String[] game = line.split("; ");

        int red = 0;
        int green = 0;
        int blue = 0;

        for (String set : game) {
            for (String draw : set.split(", ")) {
                int currentInt = Integer.parseInt(draw.substring(0, draw.indexOf(" ")));
                String currentColor = draw.substring(draw.indexOf(" ") + 1);

                switch (currentColor) {
                    case "red" -> {
                        if (red < currentInt) {
                            red = currentInt;
                        }
                    }
                    case "green" -> {
                        if (green < currentInt) {
                            green = currentInt;
                        }
                    }
                    case "blue" -> {
                        if (blue < currentInt) {
                            blue = currentInt;
                        }
                    }
                }
            }
        }
        return red*green*blue;
    }
}
