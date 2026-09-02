import java.util.HashMap;

class _1_BruteForce_O_n2 {
    /*
    Idea :
        Generate every possible substring using two loops and check whether
        the current substring contains at least one a, one b, and one c.
        T = O(n²)
    */

    public static void main(String[] args) {

        String str = "abcabc";
        int ans = 0;



        for(int i = 0; i < str.length(); i++) {

            HashMap<Character, Integer> map = new HashMap<>();

            for(int j = i; j < str.length(); j++) {

                char current = str.charAt(j);

                map.put(current, map.getOrDefault(current, 0) + 1);

                /*
                Once the substring contains all three characters, every longer
                substring starting from the same i will also contain all three.
                */

                if(map.size() == 3) {
                    ans++;
                }
            }
        }

        System.out.println("Answer = " + ans);

        /*
        Time = O(n²)
        Reason : The outer loop chooses every starting position, while the inner
                 loop extends the substring through every possible ending position.

        Space = O(1)
        Reason : The HashMap can contain only three possible characters, namely
                 a, b, and c, so its size never grows with the input length.
        */

        System.out.println("Time = O(n²), Space = O(1)");
    }
}