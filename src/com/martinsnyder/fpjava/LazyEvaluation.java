/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

import java.util.function.Supplier;
import java.util.stream.Stream;

interface LazyEvaluation {
    class FibonacciSupplier implements Supplier<Long> {
        long a = 0;
        long b = 1;

        @Override
        public Long get() {
            long next = a + b;
            a = b;
            b = next;
            return next;
        }
    }

    static void main(String[] args) {
        Stream<Long> fibStream = Stream.generate(new FibonacciSupplier());

        // Sum of first 17 odd fibonacci numbers
        long magicNumber = fibStream
            .filter(i -> i % 2 != 0)
            .limit(17)
            .reduce(0L, Long::sum);

        System.out.println("Solution is " + magicNumber);
    }
}
