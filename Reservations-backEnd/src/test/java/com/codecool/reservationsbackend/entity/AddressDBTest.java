package com.codecool.reservationsbackend.entity;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AddressDBTest {



    public static int getAnagram(String s) {
        String firstHalf = s.substring(0,(s.length()/2));
        String secondHalf = s.substring((s.length()/2), s.length());
        List<String> firstHalfStrings = Arrays.asList(firstHalf.split("(?!^)"));
        List<String> secondHalfStrings = Arrays.asList(secondHalf.split("(?!^)"));

        int toChange=0;
        for (int i = 0; i < firstHalfStrings.size(); i++) {
            String letter = firstHalfStrings.get(i);
            for (int j = 0; j < secondHalfStrings.size(); j++) {
                if (secondHalfStrings.get(j).equals(letter)){
                    ++toChange;

                }
            }
        }
        return secondHalfStrings.size()-toChange;


    }

    @Test
    public void testit(){
        System.out.println(getAnagram("786678"));
    }
}
