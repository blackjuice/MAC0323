/*************************************************************************
 *  Compilation:  javac Person.java
 *  Execution:    java Person
 * 
 *  Implementing equals() in a client-defined type.
 *
 *************************************************************************/

public final class Person {
    private final String name;
    private final long info;

    public Person(String name, long info) {
        this.name = name;
        this.info = info;
    }

    // how you're supposed to implement equals
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Person that = (Person) y;
        return (this.name.equals(that.name)) && (this.info == that.info);
    }

    public String toString() {
        return name + " " + info;
    }

    public static void main(String[] args) {
        Person a = new Person("Alice", 1234);
        Person b = new Person("Alice", 1234);
        Person c = new Person("Bob",   1234);
        Person d = new Person("Alice", 4321);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
        StdOut.println("d = " + d);
        StdOut.println("a == a: " + a.equals(a));
        StdOut.println("a == b: " + a.equals(b));
        StdOut.println("a == c: " + a.equals(c));
        StdOut.println("a == d: " + a.equals(d));
    }



}
