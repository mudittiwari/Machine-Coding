public class CLIAnimations {

    public static void loadingDots(String message, int dots, int delayMs) throws InterruptedException {
        System.out.print(message);
        for (int i = 0; i < dots; i++) {
            Thread.sleep(delayMs);
            System.out.print(".");
        }
        System.out.println();
    }

    public static void rollingDiceAnimation() throws InterruptedException {
        String[] frames = {"[ .  ]", "[ .. ]", "[ ...]", "[..  ]", "[.   ]"};
        System.out.print("Rolling dice ");
        for (int i = 0; i < 10; i++) {
            System.out.print("\rRolling dice " + frames[i % frames.length]);
            Thread.sleep(100);
        }
        System.out.print("\r                    \r"); // Clear line
    }

    public static void showWinBanner(String name) throws InterruptedException {
        System.out.println("\n🎉🎉🎉 " + name + " WINS! 🎉🎉🎉");
        String banner =
                " __     ______  _    _   _      ____   _____ ______ \n" +
                        " \\ \\   / / __ \\| |  | | | |    / __ \\ / ____|  ____|\n" +
                        "  \\ \\_/ / |  | | |  | | | |   | |  | | (___ | |__   \n" +
                        "   \\   /| |  | | |  | | | |   | |  | |\\___ \\|  __|  \n" +
                        "    | | | |__| | |__| | | |___| |__| |____) | |____ \n" +
                        "    |_|  \\____/ \\____/  |______\\____/|_____/|______|\n";

        for (String line : banner.split("\n")) {
            System.out.println(line);
            Thread.sleep(100);
        }
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
