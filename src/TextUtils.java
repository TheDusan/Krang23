import java.time.Period;

public class TextUtils {

    private static final int PERIOD = 100;


    public static void textCrawl(String text) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Thread.sleep(PERIOD);
        }
        System.out.println();
    }

    public static void textCrawl(String text, int period) throws InterruptedException {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            Thread.sleep(period);
        }
        System.out.println();
    }



    public static void displayOutroCrawl() throws InterruptedException {
        textCrawl(Text.STAFF_TEXT);
        textCrawl("KРАЈ?");
    }

    public static void displayIntroCrawl() throws InterruptedException {
        textCrawl(" _        _______  _______  _        _______    _______  ______  ", 20);
        textCrawl("| \\    /\\(  ____ )(  ___  )( (    /|(  ____ \\  / ___   )/ ___  \\ ", 20);
        textCrawl("|  \\  / /| (    )|| (   ) ||  \\  ( || (    \\/  \\/   )  |\\/   \\  \\", 20);
        textCrawl("|  (_/ / | (____)|| (___) ||   \\ | || |            /   )   ___) /", 20);
        textCrawl("|   _ (  |     __)|  ___  || (\\ \\) || | ____     _/   /   (___ ( ", 20);
        textCrawl("|  ( \\ \\ | (\\ (   | (   ) || | \\   || | \\_  )   /   _/        ) \\", 20);
        textCrawl("|  /  \\ \\| ) \\ \\__| )   ( || )  \\  || (___) |  (   (__/\\/\\___/  /", 20);
        textCrawl("|_/    \\/|/   \\__/|/     \\||/    )_)(_______)  \\_______/\\______/ ", 20);


        textCrawl(Text.INTRO2, PERIOD);
    }
}
