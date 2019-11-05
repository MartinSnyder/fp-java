package com.martinsnyder.fpjava;

import java.util.Optional;

interface OptionalUsage {
    static Optional<Double> divide(double numerator
                                  ,double denominator) {
        return denominator == 0
                ? Optional.empty()
                : Optional.of(numerator / denominator);
    }

    static void main(String[] args) {
        System.out.println("1 / 0 = " + divide(1, 0));
        System.out.println("1 / 3 = " + divide(1, 3));
    }

    // Use optional.orElse(defaultValue) instead
    static int safeGet(Optional<Integer> optional, int defaultValue) {
        if (optional.isPresent()) {
            return optional.get();
        }
        else {
            return defaultValue;
        }
    }

    // Use optional.map(Object::toString) instead
    static Optional<String> safeToString(Optional<Integer> optional) {
        if (optional.isPresent()) {
            return Optional.of(optional.get().toString());
        }
        else {
            return Optional.empty();
        }
    }

    // Use numerator.flatMap(i -> divide(i, denominator)) instead
    static Optional<Double> safeDivide(Optional<Integer> numerator, int denominator) {
        if (numerator.isPresent()) {
            return divide(numerator.get(), denominator);
        }
        else {
            return Optional.empty();
        }
    }
}
