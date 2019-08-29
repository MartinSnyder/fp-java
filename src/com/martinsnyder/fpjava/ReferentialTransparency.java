/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

interface ReferentialTransparency {
    interface Combiner<R, T> {
        R combine(T t1, T t2);
    }

    interface Invocable<T> {
        T invoke();
    }

    static <T, R> boolean looksReferentiallyTransparent(Invocable<T> invocable, Combiner<R, T> combiner) {
        T singleResult = invocable.invoke();
        R combinedSingleResult = combiner.combine(singleResult, singleResult);

        R combinedDoubleInvocation = combiner.combine(invocable.invoke(), invocable.invoke());

        return combinedSingleResult.equals(combinedDoubleInvocation);
   }

    static void main(String[] args) {
        boolean floorLooksRT = looksReferentiallyTransparent(() -> Math.floor(3.4d), Double::sum);
        boolean nanoTimeLooksRT = looksReferentiallyTransparent(System::nanoTime, Long::sum);

        System.out.println("floorLooksRT: " + floorLooksRT);
        System.out.println("nanoTimeLooksRT: " + nanoTimeLooksRT);
   }
}
