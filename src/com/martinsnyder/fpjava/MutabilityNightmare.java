package com.martinsnyder.fpjava;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public interface MutabilityNightmare {
    class Person {
        public String givenName;
        public String familyName;

        public Person(String givenName, String familyName) {
            this.givenName = givenName;
            this.familyName = familyName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(givenName, person.givenName) &&
                    Objects.equals(familyName, person.familyName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(givenName, familyName);
        }

        @Override
        public String toString() {
            return givenName + " " + familyName;
        }
    }

    static void main(String[] args) {
        Person martin = new Person("Martin", "Snyder");
        Set<Person> people = new HashSet<>();

        people.add(martin);
        martin.givenName = "Marty";
        people.add(martin);
        Set<Person> peopleCopy = new HashSet<>(people);

        System.out.println("people                                         : " + people);
        System.out.println("people.contains(new Person(\"Martin\", \"Snyder\")): " + people.contains(new Person("Martin", "Snyder")));
        System.out.println("people.size()                                  : " + people.size());
        System.out.println("peopleCopy.size()                              : " + peopleCopy.size());
    }
}
