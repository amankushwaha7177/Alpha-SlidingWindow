public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
/*
a.  == → compares references when both sides are Integer
        Integer a = 1000;
        Integer b = 1000;

        System.out.println(a == b); // false

        Here both are Integer objects, so == checks whether they are the same object, not whether their values are equal.
        That's why:
        our.get(c) == required.get(c)

        can be problematic.
        Use:
        our.get(c).intValue() == required.get(c).intValue()

        or:
        our.get(c).equals(required.get(c))

b. < → Java automatically unboxes to int
        Integer a = 5;
        Integer b = 10;

        System.out.println(a < b); // true

        Java sees < and automatically converts:
        Integer → int

        So this:
        our.get(cc) < required.get(cc)

        effectively becomes:
        our.get(cc).intValue() < required.get(cc).intValue()

        Simple rule to remember
        Integer == Integer  → reference comparison ❌
        Integer < Integer   → automatic unboxing ✅
        Integer > Integer   → automatic unboxing ✅
        Integer + Integer   → automatic unboxing ✅
        Integer == int      → automatic unboxing ✅

        So in your code:
        our.get(c) == required.get(c)

        ➡️ use .intValue() / .equals().
        But:
        our.get(cc) < required.get(cc)

        ➡️ no .intValue() is required because < automatically unboxes them.



 */