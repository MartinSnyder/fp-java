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

    interface Combiner<R, T> {
        R combine(R r, T t);
    }

    static <R, T> R fold(R acc, List<T> l, Combiner<R, T> combiner) {
        if (l.isEmpty()) {
            return acc;
        }
        else {
            R nextAcc = combiner.combine(acc, l.head());
            return fold(nextAcc, l.tail(), combiner);
        }
    }

    static <T> int length(List<T> l) {
        return fold(0, l, (acc, next) -> acc + 1);
    }

    static <T> String listToString(List<T> l) {
        return fold("", l, (acc, next) -> acc + next.toString());
    }

    static int sum(List<Integer> l) {
        return fold(0, l, (acc, next) -> acc + next);
    }

    static void main(String[] args) {
        List<Integer> numbers = new Node<>(8, new Node<>(6, new Node<>(7, new Node<>(5, new Node<>(3, new Node<>(0, new Node<>(9, new Empty<>())))))));

        System.out.println("List is " + listToString(numbers));
        System.out.println("lengthRecursive is " + lengthRecursive(numbers));
        System.out.println("Length is " + length(numbers));
        System.out.println("Sum is " + sum(numbers));
    }
}
