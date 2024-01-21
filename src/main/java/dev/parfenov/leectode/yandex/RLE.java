package dev.parfenov.leectode.yandex;

// "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB"; // --> "A4B3C2XYZD4E3F3A6B28"
public class RLE {
    public static void main(String[] args) {
        var s = "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB";
        System.out.println(rle(s));
    }

    private static String rle(String s) {
        var rle = new StringBuilder();
        var counter = 0;
        var previous = s.charAt(0);

        for (var current : s.toCharArray()) {
            if (current != previous) {
                rle.append(previous).append(counter);
                previous = current;
                counter = 0;
            }
            counter++;
        }

        return rle.append(previous).append(counter).toString();
    }
}
