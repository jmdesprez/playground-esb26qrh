package com.yourself;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class UniverseTest {

    @Test
    public void test() throws FileNotFoundException {
        try {
            Assertions.assertThat(Universe.countAllStars(2, 3)).as("Running Universe.countAllStars(2, 3)...").isEqualTo(5);
            Assertions.assertThat(Universe.countAllStars(9, -3)).as("Running Universe.countAllStars(9,-3)...").isEqualTo(6);
            success(true);

            if (existsInFile("Arrays.stream(galaxies).sum()", new File("./src/main/java/com/yourself/Universe.java"))) {
                msg("My personal Yoda, you are. 🙏", "* ● ¸ .　¸. :° ☾ ° 　¸. ● ¸ .　　¸.　:. • ");
                msg("My personal Yoda, you are. 🙏", "           　★ °  ☆ ¸. ¸ 　★　 :.　 .   ");
                msg("My personal Yoda, you are. 🙏", "__.-._     ° . .　　　　.　☾ ° 　. *   ¸ .");
                msg("My personal Yoda, you are. 🙏", "'-._\\7'      .　　° ☾  ° 　¸.☆  ● .　　　");
                msg("My personal Yoda, you are. 🙏", " /'.-c    　   * ●  ¸.　　°     ° 　¸.    ");
                msg("My personal Yoda, you are. 🙏", " |  /T      　　°     ° 　¸.     ¸ .　　  ");
                msg("My personal Yoda, you are. 🙏", "_)_/LI");
            } else {
                msg("Kudos 🌟", "Did you know that since Java8 is out you can use streams? Try it!");
                msg("Kudos 🌟", "");
                msg("Kudos 🌟", "int[] galaxies = {37, 3, 2};");
                msg("Kudos 🌟", "int totalStars = Arrays.stream(galaxies).sum(); // 42");
            }
        } catch (AssertionError ae) {
            success(false);
            msg("Oops! 🐞", ae.getMessage());
            msg("Hint 💡", "Did you properly accumulate all stars into 'totalStars'? 🤔");
        }
    }

    private static void msg(String channel, String msg) {
        System.out.println(String.format("TECHIO> message --channel \"%s\" \"%s\"", channel, msg));
    }

    private static void success(boolean success) {
        System.out.println(String.format("TECHIO> success %s", success));
    }

    // check if a string exists in a text file
    private static boolean existsInFile(String str, File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().contains(str))
                    return true;
            }
            return false;
        } finally {
            scanner.close();
        }
    }
}