/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

import java.util.Arrays;
import java.util.stream.Stream;

interface ImmutableTransformations {
    static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        int magicNumber = Arrays
                .stream(intArray)
                .map(i -> i * 2)
                .filter(i -> i > 5)
                .flatMap(i -> Stream.of(i, i))
                .reduce((acc, i) -> acc + i).get();

        System.out.println("The magic number is " + magicNumber);
    }
}