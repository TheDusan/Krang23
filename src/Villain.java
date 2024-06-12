import java.util.Random;

class Villain extends GameCharacter {

    private String specialAttackName;
    private String specialAttackMessage;
    private int specialAttackDamage;

    public Villain(String name, int maxHp, int damage, int healingPotions, String specialAttackName, String specialAttackMessage, int specialAttackDamage) {
        super(name, maxHp, damage, healingPotions);
        setSpecialAttackName(specialAttackName);
        setSpecialAttackMessage(specialAttackMessage);
        setSpecialAttackDamage(specialAttackDamage);
    }

    public void setSpecialAttackName(String specialAttackName) {
        this.specialAttackName = specialAttackName;
    }

    public void setSpecialAttackMessage(String specialAttackMessage) {
        this.specialAttackMessage = specialAttackMessage;
    }

    public void setSpecialAttackDamage(int specialAttackDamage) {
        this.specialAttackDamage = specialAttackDamage;
    }

    public String getSpecialAttackName() {
        return this.specialAttackName;
    }

    public int getSpecialAttackDamage() {
        return this.specialAttackDamage;
    }

    public String getSpecialAttackMessage() {
        return this.specialAttackMessage;
    }

    @Override
    public void attack(GameCharacter enemy) {
        int damage = this.getDamage();
        boolean isCritical = (new Random().nextInt(100) < 20);
        if (isCritical) {
            System.out.println(this.getName() + " је ударио жестоко!");
            damage = (int) (damage * 1.5);
        }

        System.out.println(this.getName() + " напада " + enemy.getName() + "а и наноси му " + damage + " поена штете.");
        enemy.takeDamage(damage);

        if (new Random().nextBoolean()) {
            specialAttack(enemy);
        }
    }

    public void specialAttack(GameCharacter enemy) {
        System.out.println(this.getName() + " извођи специјални напад " + this.specialAttackName + " и наноси " + this.specialAttackDamage + " поена штете.");
        System.out.println(this.specialAttackMessage);
        enemy.takeDamage(this.specialAttackDamage);
    }
}
