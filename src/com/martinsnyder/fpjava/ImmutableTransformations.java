package com.martinsnyder.fpjava;

import java.util.Arrays;

public interface ImmutableTransformations {
    static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        int magicNumber = Arrays
                .stream(intArray)
                .map(i -> i * 2)
                .filter(i -> i > 5)
                .reduce((acc, i) -> acc + i).get();

        System.out.println("The magic number is " + magicNumber);
    }
}