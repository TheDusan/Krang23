import java.util.Random;

class GameCharacter {

    private String name;
    private int maxHp;
    private int hp;
    private int damage;
    private int healingPotions;

    public GameCharacter(String name, int maxHp, int damage, int healingPotions) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.damage = damage;
        this.healingPotions = healingPotions;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealingPotions() {
        return healingPotions;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



    public void attack(GameCharacter enemy) {

        int damage = this.damage;
        boolean isCritical = (new Random().nextInt(100) < 20);
        if (isCritical) {
            System.out.println(this.name + " је ударио жестоко!");
            damage = (int) (damage * 1.5);
        }

        System.out.println(this.name + " је ударио " + enemy.getName() + " и тиме нанео " + damage + " поена штете.");
        enemy.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.hp = 0;
            System.out.println(this.name + " је поражен!");
        }
    }

    public void useHealingPotion() {
        if (healingPotions <= 0) {
            System.out.println(this.name + " је остао без чаробног напитка!");
        } else {
            int healAmount = (int) (maxHp * 0.25);
            hp += healAmount;
            if (hp > maxHp) {
                hp = maxHp;
            }
            healingPotions--;
            System.out.println(this.name + " је попио чаробни напитак и тако повратио " + healAmount + " здравствених поена.");
        }

    }
}