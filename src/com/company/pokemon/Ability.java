package com.company.pokemon;

/* clasa care reprezinta abilitatea unui pokemon */
public class Ability implements Cloneable {
    private final Integer damage;
    private final Boolean stun;
    private final Boolean dodge;
    private Integer cooldown;

    public Ability(Integer damage, Boolean stun, Boolean dodge, Integer cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public Integer getDamage() {
        return damage;
    }

    public Boolean getDodge() {
        return this.dodge;
    }

    public boolean getStun() {
        return this.stun;
    }

    public String toString() {
        return " " + this.damage + " " + this.stun + " " + this.dodge + " " + this.cooldown + " ";
    }

    public Ability clone() {
        Ability ability = new Ability(this.damage, this.stun, this.dodge, this.cooldown);
        return ability;
    }
}
