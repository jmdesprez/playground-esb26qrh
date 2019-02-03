package com.yourself;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static jee.jpa.TechIOCommands.existsInFile;
import static jee.jpa.TechIOCommands.msg;
import static jee.jpa.TechIOCommands.success;


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

}