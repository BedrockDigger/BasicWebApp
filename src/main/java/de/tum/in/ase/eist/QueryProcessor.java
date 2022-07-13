package de.tum.in.ase.eist;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QueryProcessor {

    public String process(String query) {
        query = query.toLowerCase();
        if (query.contains("shakespeare")) {
            return "William Shakespeare (26 April 1564 - 23 April 1616) was an " +
                    "English poet, playwright, and actor, widely regarded as the greatest " +
                    "writer in the English language and the world's pre-eminent dramatist.";
        } else if (query.contains("which of the following numbers is the largest")) {
            return String.valueOf(Arrays.stream(query.split(":")[1].split(",")).map(String::trim).mapToInt(Integer::parseInt).max().orElse(0));
        }
        if (query.contains("plus")) {
            String[] chunks = query.split(" ");
            int sum = Integer.parseInt(chunks[2]) + Integer.parseInt(chunks[4]);
            return String.valueOf(sum);
        } else {
            return "";
        }
    }
}
