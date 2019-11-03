package com.martinsnyder.fpjava;

import java.util.Optional;

interface OptionalUsage {
    static Optional<Double> divide(double numerator
                                  , double denominator) {
        return denominator == 0
                ? Optional.empty()
                : Optional.of(numerator / denominator);
    }

    static void main(String[] args) {
        System.out.println("1 / 0 = " + divide(1, 0));
        System.out.println("1 / 3 = " + divide(1, 3));
    }
}
