package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class QueryProcessor {

    static boolean isPrime(int n) {
        // Corner case
        if (n <= 1) return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0) return false;

        return true;
    }

    private IntStream findIntegers(String query) {
        int i = 0;
        List<String> intStrings = new ArrayList<>();
        while (i < query.length()) {
            if (48 <= query.charAt(i) && query.charAt(i) <= 57) {
                StringBuilder sb = new StringBuilder();
                while (i < query.length() && 48 <= query.charAt(i) && query.charAt(i) <= 57) {
                    sb.append(query.charAt(i));
                    i++;
                }
                intStrings.add(sb.toString());
            }
            i++;
        }
        return intStrings.stream().mapToInt(Integer::parseInt);
    }

    public String process(String query) {

        query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " + "English poet, playwright, and actor, widely regarded as the greatest " + "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("largest")) {
            return String.valueOf(findIntegers(query).max().orElse(0));
        } else if (query.contains("plus")) {
            return String.valueOf(findIntegers(query).sum());
        } else if (query.contains("both a square and a cube")) {
            return String.valueOf(findIntegers(query).filter(i -> (Math.pow(i, 1.0 / 3.0) % 1 == 0) && (Math.pow(i, 1.0 / 2.0) % 1 == 0)).findFirst().orElse(0));
        } else if (query.contains("multiplied")) {
            return String.valueOf(findIntegers(query).reduce(1, (subtotal, element) -> subtotal * element));
        } else if (query.contains("primes")) {
            return String.valueOf(findIntegers(query).filter(QueryProcessor::isPrime).findFirst().orElse(0));
        } else {
            return "";
        }
    }
}
