package com.company.event;

import com.company.pokemon.Pokemon;

import static com.company.event.Action.doAction;
import static com.company.event.RandomPokemon.randomActionPokemon;

/*Un wrapper in jurul clasei Pokemon
* folosit pentru a-i adauga utilitati
* in timpul unei batalii*/
public class PokemonInBattle {
    private Pokemon pokemon;            //<-pokemonul in sine
    private Boolean isDodging;          //<-daca in tura respectiva da Dodge
    private Boolean isStunned;          //<-daca in tura respecitva are Stun
    private Integer currentCooldown1;   //
    private Integer currentCooldown2;   //<-cooldown-urile curente ale abilitatilor(atunci cand e 0 se poate folosi)


    public PokemonInBattle(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.isDodging = false;
        this.isStunned = false;
        this.currentCooldown1 = 0;
        this.currentCooldown2 = 0;
    }

    public void stun() {
        this.isStunned = true;
    }

    public void wakeUpFromStun() {
        this.isStunned = false;
    }

    public void dodge() {
        this.isDodging = true;
    }

    public void stopDodging() {
        this.isDodging = false;
    }

    public Boolean getIsDodging() {
        return this.isDodging;
    }

    public Boolean getIsStunned() {
        return this.isStunned;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public void decreaseHealth(Integer damage) {    //<-pentru a putea fi ranit
        this.pokemon.decreaseHealth(damage);
    }

    public void decCurrentCooldown() {              //<-folosita la fiecare runda defensiva
        if (this.currentCooldown1 > 0) this.currentCooldown1--;
        if (this.currentCooldown2 > 0) this.currentCooldown2--;
    }

    //reset-urile folosite pentru dupa folosirea unei abilitati
    public void resetCooldown1() {
        this.currentCooldown1 = this.pokemon.getFirstAbility().getCooldown();
    }

    public void resetCooldown2() {
        this.currentCooldown2 = this.pokemon.getSecondAbility().getCooldown();
    }

    public Boolean isFirstAbilityReady() {
        if (this.currentCooldown1 == 0) return true;
        return false;
    }

    public Boolean isSecondAbilityReady() {
        if (this.currentCooldown2 == 0) return true;
        return false;
    }

    //face un random action asupra unui pokemon
    public Action.GameStatus doRandomAction(PokemonInBattle defensivePokemon) {
        RandomPokemon.ACTION randomAction = randomActionPokemon(this);
        Action.GameStatus gameStatus =  doAction(this, defensivePokemon, randomAction);
        return gameStatus;
    }


}
