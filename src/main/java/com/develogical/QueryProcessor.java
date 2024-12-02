package com.develogical;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("which one of the following numbers is the largest")) {
            if (query.toLowerCase().contains("which one of the following numbers is the largest")) {
                // Extract the part after the colon, split by commas
                String[] parts = query.split(":");
                if (parts.length < 2) {
                    return "Invalid query format.";
                }

                // Get the part after the colon, split by commas, and remove any extra spaces
                String[] numbersStr = parts[1].replace("?", "").split(",");
                int max = Integer.MIN_VALUE;

                // Parse the numbers and find the largest
                for (String numStr : numbersStr) {
                    try {
                        int num = Integer.parseInt(numStr.trim());
                        if (num > max) {
                            max = num;
                        }
                    } catch (NumberFormatException e) {
                        return "Invalid number format.";
                    }
                }

                // Return the largest number as a string
                return String.valueOf(max);
            }
        }

        if (query.toLowerCase().contains("to the power of")) {
            String[] tokens = query.split(" to the power of ");
            String[] num1arr = tokens[0].split(" ");
            int num1 = Integer.parseInt(num1arr[num1arr.length - 1]);
            int num2 = Integer.parseInt(tokens[1].trim());
            return String.valueOf(num1 * num2);
        }

        if (query.toLowerCase().contains("which of the following numbers is both a square and a cube")) {
            // Extract the part after the colon, split by commas
            String[] parts = query.split(":");
            if (parts.length < 2) {
                return "Invalid query format.";
            }

            // Get the part after the colon, split by commas, and remove any extra spaces
            String[] numbersStr = parts[1].replace("?", "").split(",");
            StringBuilder result = new StringBuilder();

            // Loop through the numbers to check if they are both a square and a cube
            for (String numStr : numbersStr) {
                try {
                    int num = Integer.parseInt(numStr.trim());

                    // Check if the number is both a square and a cube (perfect sixth power)
                    if (isPerfectSixthPower(num)) {
                        result.append(num).append(" ");
                    }

                } catch (NumberFormatException e) {
                    return "Invalid number format.";
                }
            }

            // Return the result as a string, trimming any extra spaces
            return result.toString().trim().isEmpty() ? "No numbers found." : result.toString().trim();
        }

        if (query.toLowerCase().contains("plus")) {
            String[] tokens = query.split(" ");
            Integer num1 = null;
            Integer num2 = null;
            for (String token : tokens) {
                try {
                    Integer.parseInt(token);
                    if (num1 == null) {
                        num1 = Integer.parseInt(token);
                    } else if (num2 == null) {
                        num2 = Integer.parseInt(token);
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            return String.valueOf(num1 + num2);
        }

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.contains("your name")) {
            return "Peter and Om";
        }

        if (query.toLowerCase().contains("minus")) {
            return handleSubtraction(query);
        }

        if (query.toLowerCase().contains("multiplied by")) {
            return handleMultiplication(query);
        }

        if (query.toLowerCase().contains("which of the following numbers are primes")) {
            return findPrimes(query);
        }

        if (query.toLowerCase().contains("which of the following numbers is both a square and a cube")) {
            return findSquareAndCube(query);
        }

        return "";
    }

    private static boolean isPerfectSixthPower(int num) {
        if (num < 0) {
            return false;
        }

        int cubeRoot = (int) Math.round(Math.pow(num, 1.0 / 3.0)); // Cube root
        int squareRoot = (int) Math.round(Math.sqrt(num)); // Square root

        // Check if both cubeRoot^3 equals num and squareRoot^2 equals num
        return (cubeRoot * cubeRoot * cubeRoot == num) && (squareRoot * squareRoot == num);
    }

    public static String handleSubtraction(String query) {
        String[] tokens = query.split(" ");
        int num1 = 0, num2 = 0;
        boolean firstNumFound = false, secondNumFound = false;

        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                if (!firstNumFound) {
                    num1 = num;
                    firstNumFound = true;
                } else if (!secondNumFound) {
                    num2 = num;
                    secondNumFound = true;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        // Perform subtraction and return the result
        return String.valueOf(num1 - num2);
    }

    public static String handleMultiplication(String query) {
        String[] tokens = query.split(" ");
        int num1 = 0, num2 = 0;
        boolean firstNumFound = false, secondNumFound = false;

        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                if (!firstNumFound) {
                    num1 = num;
                    firstNumFound = true;
                } else if (!secondNumFound) {
                    num2 = num;
                    secondNumFound = true;
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        // Perform multiplication and return the result
        return String.valueOf(num1 * num2);
    }

    public static String findPrimes(String query) {
        String[] parts = query.split(":");
        if (parts.length < 2) {
            return "Invalid query format.";
        }

        String[] numbersStr = parts[1].replace("?", "").split(",");
        StringBuilder result = new StringBuilder();

        for (String numStr : numbersStr) {
            try {
                int num = Integer.parseInt(numStr.trim());
                if (isPrime(num)) {
                    result.append(num).append(" ");
                }
            } catch (NumberFormatException e) {
                return "Invalid number format.";
            }
        }

        return result.toString().trim().isEmpty() ? "No primes found." : result.toString().trim();
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String findSquareAndCube(String query) {
        String[] parts = query.split(":");
        if (parts.length < 2) {
            return "Invalid query format.";
        }

        String[] numbersStr = parts[1].replace("?", "").split(",");
        StringBuilder result = new StringBuilder();

        for (String numStr : numbersStr) {
            try {
                int num = Integer.parseInt(numStr.trim());
                if (isSquareAndCube(num)) {
                    result.append(num).append(" ");
                }
            } catch (NumberFormatException e) {
                return "Invalid number format.";
            }
        }

        return result.toString().trim().isEmpty() ? "No number found that is both a square and a cube."
                : result.toString().trim();
    }

    private static boolean isSquareAndCube(int num) {
        double sqrt = Math.sqrt(num);
        double cbrt = Math.cbrt(num);

        return (sqrt == Math.floor(sqrt)) && (cbrt == Math.floor(cbrt));
    }

}