import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOne {
    public static void main(String[] args) throws IOException {
        int sum = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\PuzzleInput.txt"))
                .lines()
                .mapToInt(line -> Integer.parseInt(line.replaceAll("[^0-9]", "").charAt(0) +
                        line.replaceAll("[^0-9]", "").substring(line.replaceAll("[^0-9]", "").length() - 1)))
                .sum();

        System.out.println(sum);

        int sum2 = new BufferedReader(new FileReader("E:\\Programming\\AdventOfCode2023\\src\\PuzzleInput.txt"))
                .lines()
                .mapToInt(DayOne::calculateCalibrationValue)
                .sum();

        System.out.println(sum2);
    }

    private static int calculateCalibrationValue(String line) {
        Pattern pattern = Pattern.compile("(1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine)");
        Pattern patternReversed = Pattern.compile("(1|2|3|4|5|6|7|8|9|eno|owt|eerht|ruof|evif|xis|neves|thgie|enin)");

        Matcher matcherReversed = patternReversed.matcher(new StringBuilder().append(line).reverse().toString());
        Matcher matcher = pattern.matcher(line);

        StringBuilder digits = new StringBuilder();

        if (matcher.find()) {
            digits.append(mapStringToDigit(matcher.group()));
        }

        if (matcherReversed.find()) {
            digits.append(mapStringToDigit(matcherReversed.group()));
        }

        return Integer.parseInt(digits.substring(0, 1) + digits.substring(digits.length() - 1));
    }

    private static String mapStringToDigit(String input) {
        return switch (input) {
            case "one", "eno" -> "1";
            case "two", "owt" -> "2";
            case "three", "eerht" -> "3";
            case "four", "ruof" -> "4";
            case "five", "evif" -> "5";
            case "six", "xis" -> "6";
            case "seven", "neves" -> "7";
            case "eight", "thgie" -> "8";
            case "nine", "enin" -> "9";
            default -> input;
        };
    }
}
