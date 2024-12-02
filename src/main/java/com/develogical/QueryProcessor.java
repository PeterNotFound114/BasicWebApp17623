package com.develogical;

public class QueryProcessor {

    public String process(String query) {

        if (query.toLowerCase().contains("which one of the following numbers is the largest: ")) {
            String[] numbers = query.split(":")[1].split(", ");
            int max = Integer.MIN_VALUE;
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                if (num > max) {
                    max = num;
                }
            }
            return String.valueOf(max);
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

        if (query.contains("of the following numbers is the largest: ")) {
            return "";
        }

        return "";
    }

}