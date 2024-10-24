import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner scanner = new Scanner(System.in);
    private Thread musicThread;

    public void start() throws InterruptedException {
        boolean playAgain = true;
        while (playAgain) {
            playGame();
            playAgain = askToPlayAgain();
        }
        System.out.println("Хвала што сте играли!");
    }

    private void playGame() throws InterruptedException {
        System.out.printf("%s\n", Text.PRODUCTION_COMPANIES);
        Thread.sleep(200);
        TextUtils.textCrawl(Text.INTRO);

        playMusic(Music.INTRO_MUSIC, false);
        TextUtils.displayIntroCrawl();

        getInputAndConfirmName();
        stopMusic();

        playMusic(Music.FIELD_MUSIC, true);
        TextUtils.textCrawl(Text.INTRO7);
        TextUtils.textCrawl(Text.INTRO8);
        stopMusic();

        GameCharacter hero = new GameCharacter("Јунак", 120, 20, 2);
        GameCharacter opponent1 = new Villain(SpecialAttacksMessage.NAMES.get(0), 50, 10, 0, SpecialAttacksMessage.CRTANJE, SpecialAttacksMessage.CRTANJE_MESSAGE, 10);

        playMusic(Music.FIGHT_MUSIC, true);
        System.out.println("Првa противница: " + opponent1.getName() + " - има око соколово, преписивање не може да јој промакне!");
        fight(hero, opponent1);
        if (status(hero)) return;
        hero.setHp(hero.getMaxHp());
        stopMusic();

        playMusic(Music.FIELD_MUSIC, true);
        TextUtils.textCrawl(Text.INTRO10);
        stopMusic();

        GameCharacter opponent2 = new Villain(SpecialAttacksMessage.NAMES.get(1), 80, 15, 1, SpecialAttacksMessage.TOTALNA_KONFUZIJA, SpecialAttacksMessage.KONFUZIJA_MESSAGE, 15);
        playMusic(Music.FIGHT2_MUSIC, true);
        System.out.println("\nДруги противник: " + opponent2.getName() + " - увек спреман да не објасни ништа");
        fight(hero, opponent2);
        if (status(hero)) return;
        stopMusic();
        hero.setHp(hero.getMaxHp());

        playMusic(Music.FIELD_MUSIC, true);
        TextUtils.textCrawl(Text.INTRO11);
        TextUtils.textCrawl(Text.INTRO12);
        stopMusic();

        GameCharacter opponent3 = new Villain(SpecialAttacksMessage.NAMES.get(2), 100, 20, 1, SpecialAttacksMessage.USPAVANKA, SpecialAttacksMessage.USPAVANKA_MESSAGE, 20);
        playMusic(Music.FIGHT3_MUSIC, true);
        System.out.println("\nТрећи противник: " + opponent3.getName() + " - последњи син Криптона, крије се под ликом просветног радника");
        fight(hero, opponent3);
        if (status(hero)) return;
        stopMusic();

        System.out.println(Text.WIN);
        playMusic(Music.END_MUSIC, false);
        TextUtils.displayOutroCrawl();
    }

    private void getInputAndConfirmName() {
        System.out.print(Text.ENTER_NAME);
        scanner.nextLine();
        System.out.print(Text.NAME_QUESTION);
        scanner.nextLine();
        System.out.println(Text.NAME_CONFIRMATION);
    }

    private void playMusic(String music, boolean loop) {
        musicThread = new Thread(() -> {
            Sound.playMusic(music, loop);
        });
        musicThread.start();
    }

    private void stopMusic() {
        if (musicThread != null) {
            musicThread.stop();
            Sound.stopMusic(true);
        }
    }

    private void fight(GameCharacter hero, GameCharacter enemy) {
        Random random = new Random();
        while (true) {
            System.out.println("\n" + hero.getName() + " (Здравље: " + hero.getHp() + ")");
            System.out.println(enemy.getName() + " (Здравље: " + enemy.getHp() + ")");
            System.out.println("Изаберите акцију:");
            System.out.println("1. Удри!");
            System.out.println("2. Попиј чаробни напитак (" + hero.getHealingPotions() + " преостало)");

            int choice = getPlayerChoice();

            if (choice == 1) {
                hero.attack(enemy);
            } else if (choice == 2) {
                if (hero.getHealingPotions() == 0) {
                    System.out.println("Немате више чаробног напитка!");
                } else {
                    hero.useHealingPotion();
                }
            }

            if (enemy.getHp() == 0) {
                System.out.println(enemy.getName() + " губи!");
                break;
            }

            if (enemy.getHealingPotions() > 0 && enemy.getHp() < enemy.getMaxHp() && random.nextBoolean()) {
                enemy.useHealingPotion();
            } else {
                enemy.attack(hero);
            }

            if (hero.getHp() == 0) {
                System.out.println(hero.getName() + " је изгубио!");
                break;
            }
        }
    }

    private int getPlayerChoice() {
        int choice = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                choice = Integer.parseInt(input);
                if (choice == 1 || choice == 2) {
                    validInput = true;
                } else {
                    System.out.println(Text.WRONG_KEY);
                }
            } else {
                System.out.println(Text.WRONG_KEY);
            }
        }
        return choice;
    }

    private boolean status(GameCharacter hero) throws InterruptedException {
        if (hero.getHp() == 0) {
            Sound.stopMusic(true);
            System.out.println(Text.DEFEAT);
            return true;
        } else {
            System.out.println(Text.VICTORY_TEXT);
            return false;
        }
    }

    private boolean askToPlayAgain() {
        System.out.print("Желите ли да играте поново? y за да, n за не: ");
        String input = scanner.nextLine().trim().toLowerCase();
        stopMusic();
        return input.equals("y");
    }
}
