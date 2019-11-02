/*
 *  Apache License
 *  Version 2.0, January 2004
 *
 *  See LICENSE file in project root for details
 */
package com.martinsnyder.fpjava;

interface PersistentDataStructure {
    interface List<T> {
        T head();
        List<T> tail();
        boolean isEmpty();
    }

    class Empty<T> implements List<T> {
        public T head() { throw new RuntimeException(".head() invoked on empty list"); }
        public List<T> tail() { throw new RuntimeException(".tail() invoked on empty list"); }
        public boolean isEmpty() { return true; }
    }

    class Node<T> implements List<T> {
        private final T item;
        private final List<T> next;

        public T head() { return item; }
        public List<T> tail() { return next; }
        public boolean isEmpty() { return false; }

        Node(T item, List<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    static <T> int lengthRecursive(List<T> l) {
        if (l.isEmpty()) {
            return 0;
        }
        else {
            return 1 + lengthRecursive(l.tail());
        }
    }

    static <T> int lengthTailRecursive(List<T> l) {
        return lengthTailR(l, 0);
    }

    private static <T> int lengthTailR(List<T> l, int acc) {
        if (l.isEmpty()) {
            return acc;
        }
        else {
            return lengthTailR(l.tail(), acc + 1);
        }
    }

    interface Combiner<R, T> {
        R combine(R r, T t);
    }

    static <R, T> R foldLeft(R acc, List<T> l, Combiner<R, T> combiner) {
        if (l.isEmpty()) {
            return acc;
        }
        else {
            R nextAcc = combiner.combine(acc, l.head());
            return foldLeft(nextAcc, l.tail(), combiner);
        }
    }

    static <R, T> R foldRight(R acc, List<T> l, Combiner<R, T> combiner) {
        if (l.isEmpty()) {
            return acc;
        }
        else {
            R interior = foldRight(acc, l.tail(), combiner);
            return combiner.combine(interior, l.head());
        }
    }

    static <T> int lengthWithFold(List<T> l) {
        return foldLeft(0, l, (acc, next) -> acc + 1);
    }

    static <T> String listToString(List<T> l) {
        return foldLeft("", l, (acc, next) -> acc + next.toString());
    }

    static int sum(List<Integer> l) {
        return foldLeft(0, l, (acc, next) -> acc + next);
    }

    interface Transformer<R, T> {
        R transform(T t);
    }

    static <R, T> List<R> map(List<T> l, Transformer<R, T> transformer) {
        return foldRight((List<R>)new Empty<R>(), l, (soFar, el) ->
            new Node<>(transformer.transform(el), soFar)
        );
    }

    static void main(String[] args) {
        List<Integer> numbers = new Node<>(8, new Node<>(6, new Node<>(7, new Node<>(5, new Node<>(3, new Node<>(0, new Node<>(9, new Empty<>())))))));

        System.out.println("List is        " + listToString(numbers));
        System.out.println("Offset List is " + listToString(map(numbers, i -> i > 0 ? i -1: i)));
        System.out.println("lengthRecursive is     " + lengthRecursive(numbers));
        System.out.println("lengthTailRecursive is " + lengthTailRecursive(numbers));
        System.out.println("lengthWithFold is      " + lengthWithFold(numbers));
        System.out.println("Sum is " + sum(numbers));
    }
}
