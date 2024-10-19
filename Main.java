import java.util.ArrayList;
import java.util.List;

class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}

public class Main {

    public int add(String numbers) throws NegativeNumberException {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        if (numbers.startsWith("//")) {
            String[] parts = numbers.split("\n", 2);
            delimiter = parts[0].substring(2);
            numbers = parts[1];
        }

        numbers = numbers.replace("\n", delimiter);
        
        String[] numList = numbers.split(delimiter);
        List<Integer> negativeNumbers = new ArrayList<>();
        int total = 0;

        for (String num : numList) {
            if (!num.isEmpty()) {
                int n = Integer.parseInt(num);
                if (n < 0) {
                    negativeNumbers.add(n);
                }
                total += n;
            }
        }

        if (!negativeNumbers.isEmpty()) {
            throw new NegativeNumberException("negative numbers not allowed: " + String.join(", ", negativeNumbers.stream().map(String::valueOf).toArray(String[]::new)));
        }

        return total;
    }

    public static void main(String[] args) {
        Main calculator = new Main();
        try {
            System.out.println(calculator.add("")); // Output: 0
            System.out.println(calculator.add("1")); // Output: 1
            System.out.println(calculator.add("1,5")); // Output: 6
            // System.out.println(calculator.add("1,-5"));
            System.out.println(calculator.add("1\n2,3")); // Output: 6
            System.out.println(calculator.add("//;\n1;2")); // Output: 3
            System.out.println(calculator.add("//;\n1;-2;3;-9")); // throw exception
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}
