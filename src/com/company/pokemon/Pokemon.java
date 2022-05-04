package com.company.pokemon;

import com.company.item.Item;
import com.company.myLog.MyLogger;
import java.util.ArrayList;
import java.util.logging.*;

//pokemonul inafara luptei, deoarece nu poate folosi un atac
//iar acesta ar trebui salat pentru a nu-i echipa de mai multe ori
//itemele, pentru a evolua dupa o batalie, precum si alte motive
public class Pokemon implements Cloneable, Comparable<Pokemon> {
    private static Logger logger = new MyLogger().getLogger();

    private String name;
    private Integer hp;
    private Integer attack;
    private Integer specialAttack;
    private Integer defense;
    private Integer specialDefense;
    private Ability firstAbility;
    private Ability secondAbility;
    private ArrayList<Item> items = new ArrayList<Item>();

    protected void setName(String name) {
        this.name = name;
    }

    protected void setHp(Integer hp) {
        this.hp = hp;
    }

    protected void setAttack(Integer attack) {
        this.attack = attack;
        this.specialAttack = null;    // poate avea doar attack normal
    }

    protected void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
        this.attack = null;           // poate avea doar attack special
    }

    protected void setDefense(Integer defense) {
        this.defense = defense;
    }

    protected void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }
    
    protected void setFirstAbility(Ability ability) {
        this.firstAbility = ability;
    }

    protected void setSecondAbility(Ability ability) {
        this.secondAbility = ability;
    }

    public void addItem(Item item) {
        if (this.items.size() == 3) throw new IndexOutOfBoundsException("Maxim 3 item-uri per pokemon!");               //<-poate avea maxim 3
        boolean checkIfPresentItem = items.stream().anyMatch(i -> i.getName().contains(item.getName()));                //iteme
        if (checkIfPresentItem) throw new IllegalArgumentException("Maxim 1 item de un tip per pokemon!");              //<-lambda expression care
        this.items.add(item);                                                                                           //imi returneaza un boolean
    }                                                                                                                   //daca itemul adaugat deja exista

    public String getName() {
        return name;
    }

    public Integer getHp() {
        return hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public Integer getDefense() {
        return defense;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public Ability getFirstAbility() {
        return firstAbility;
    }

    public Ability getSecondAbility() {
        return secondAbility;
    }

    public void decreaseHealth(Integer damage) {    //<-nu este folosita explicit in lupte, dar este apelata de clasa Wrapper PokemonInBattle
        if (damage <= 0) return;                    //<-pentru a nu da "heal" din greseala pokemonului(cazul cand defense > damage)
        this.hp = this.hp - damage;
    }

    public void equipItems() {                      //<-echipeaza un pokemon inainte de lupta
        for (Item item : items) {
            this.hp = this.hp + item.getIncHp();
            if (this.attack == null) {
                this.specialAttack =  this.specialAttack + item.getIncSpecialAttack();
            } else {
                this.attack = this.attack + item.getIncAttack();
            }
            this.defense = this.defense + item.getIncDefense();
            this.specialDefense = this.specialDefense + item.getIncSpecialDefense();
        }
    }

    public void increaseStats() {                   //<-in cazul in care castiga o batalie;
        this.hp++;                                  //este apelata doar prin metoda increase din Antrenor
        if (this.attack == null) {
            this.specialAttack++;
        } else {
            this.attack++;
        }
        this.defense++;
        this.specialDefense++;
    }

    @Override
    public String toString() {
        String attackOrSpecial;
        if (this.specialAttack == null) {
            attackOrSpecial = " Att: " + attack;
        } else {
            attackOrSpecial = " SpAtt: " + specialAttack;
        }
        return "Nume: " + this.name + " Hp: " + this.hp + attackOrSpecial + " Def: " + this.defense + " SpDef: " + this.specialDefense;
    }

    @Override
    public Pokemon clone() {
        Pokemon pokemonClone = PokemonFactory.getInstanta().createPokemon(this.name);

        pokemonClone.setName(this.name);
        pokemonClone.setHp(this.hp);
        if (this.specialAttack == null) {
            pokemonClone.setAttack(this.attack);
        } else {
            pokemonClone.setSpecialAttack(this.specialAttack);

        }
        pokemonClone.setDefense(this.defense);
        pokemonClone.setSpecialDefense(this.defense);

        for (Item item : items) {
            pokemonClone.addItem(item.clone());
        }

        return pokemonClone;
    }

    private int calculatePower() {  //<-clasa ajutatoare pentru a ajuta la comparare
        Integer attackOrSpecial;
        if (this.attack == null) {
            attackOrSpecial = this.specialAttack;
        } else {
            attackOrSpecial = this.attack;
        }
        return this.hp + attackOrSpecial + this.defense + this.specialDefense;
    }

    @Override
    public int compareTo(Pokemon pokemon) { //<-pentru a determina cel mai puternic pokemon al unui antrenor
        if (this.calculatePower() > pokemon.calculatePower()) {
            return 1;
        }
        if (this.calculatePower() < pokemon.calculatePower()) {
            return -1;
        }
        return this.name.compareTo(pokemon.name);
    }

    public String itemsToString() { //<-pentru afisarea itemelor
        String s = new String();
        for (Item item : items) {
            s = s + item.getName() + " ";
        }
        return s;
    }
}
