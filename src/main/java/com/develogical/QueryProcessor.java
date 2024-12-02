package com.develogical;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("which one of the following numbers is the largest")) {
            return handleLargestNumber(query);
        }

        if (query.toLowerCase().contains("which of the following numbers is both a square and a cube")) {
            return findSquareAndCube(query);
        }

        if (query.toLowerCase().contains("plus")) {
            return handleAddition(query);  // Updated method to handle multiple additions
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

        if (query.contains("your name")) {
            return "Peter and Om";
        }

        if (query.toLowerCase().contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        }

        if (query.toLowerCase().contains("to the power of")) {
            return handleExponentiation(query);
        }

        return "";
    }

    // Handles the addition of multiple numbers
    public static String handleAddition(String query) {
        String[] tokens = query.split(" ");
        int sum = 0;

        // Go through the tokens and sum up the numbers
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                sum += num;
            } catch (NumberFormatException e) {
                continue;  // Ignore non-numeric tokens
            }
        }

        System.out.println(String.valueOf(sum));

        return String.valueOf(sum);  // Return the sum of all numbers
    }

    // Handles subtraction (as before)
    public static String handleSubtraction(String query) {
        String[] tokens = query.split(" ");
        int result = 0;
        boolean firstNumFound = false;
    
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                if (!firstNumFound) {
                    result = num; // Assign the first number as the starting result
                    firstNumFound = true;
                } else {
                    result -= num; // Subtract subsequent numbers
                }
            } catch (NumberFormatException e) {
                continue; // Ignore non-numeric tokens
            }
        }
    
        return String.valueOf(result);
    }
    

    // Handles multiplication (as before)
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

        return String.valueOf(num1 * num2);  // Perform multiplication
    }

    // Find primes (as before)
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

    // Prime number check
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

    // Find square and cube numbers (as before)
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

        return result.toString().trim().isEmpty() ? "No number found that is both a square and a cube." : result.toString().trim();
    }

    // Check if a number is both a square and a cube (i.e., a perfect sixth power)
    private static boolean isSquareAndCube(int num) {
        double sqrt = Math.sqrt(num);
        double cbrt = Math.cbrt(num);

        return (sqrt == Math.floor(sqrt)) && (cbrt == Math.floor(cbrt));
    }

    // Handle the largest number query (as before)
    public static String handleLargestNumber(String query) {
        String[] parts = query.split(":");
        if (parts.length < 2) {
            return "Invalid query format.";
        }

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

        return String.valueOf(max);
    }

    public static String handleExponentiation(String query) {
        String[] tokens = query.split(" ");
        int base = 0, exponent = 0;
        boolean baseFound = false, exponentFound = false;
    
        for (String token : tokens) {
            try {
                int num = Integer.parseInt(token);
                if (!baseFound) {
                    base = num; // First number is the base
                    baseFound = true;
                } else if (!exponentFound) {
                    exponent = num; // Second number is the exponent
                    exponentFound = true;
                }
            } catch (NumberFormatException e) {
                continue; // Ignore non-numeric tokens
            }
        }
    
        // Perform the exponentiation and return the result
        double result = Math.pow(base, exponent);
        return String.valueOf((long) result); // Return the result as a long to handle large values
    }
    
}
