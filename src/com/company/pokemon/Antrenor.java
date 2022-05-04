package com.company.pokemon;

import java.util.ArrayList;
import java.util.Collections;

public class Antrenor {

    private String nume;
    private Integer varsta;
    private ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();    //<-are doar 3 abilitati

    public Antrenor() {}

    public void setName(String nume) {
        this.nume = nume;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public void addPokemon(Pokemon pokemon) {
        if (this.pokemons.size() == 3) throw new ArrayIndexOutOfBoundsException("Maximum of 3 pokemons per trainer!");  //<-nu poate avea mai mult de 3
                                                                                                                        //pokemoni
        boolean checkIfPresentPokemon = pokemons.stream().anyMatch(p -> p.getName().contains(pokemon.getName()));       //<-lambda expression pentru a verifica
        if (checkIfPresentPokemon) throw new IllegalArgumentException("Maximum 1 pokemon de un tip per antrenor!");     //ca pokemonul adaugat nu exista deja

        this.pokemons.add(pokemon);
    }

    public String getName() {
        return this.nume;
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public void increaseStatsOfIthPokemon(int index) {  //<-intrucat la vsNeutrel1/2 trebuie sa selectez random un pokemon
        this.pokemons.get(index).increaseStats();       //,ii retin indexul pentru a putea apoi sa evolueze, in cazul unei victorii
    }

    @Override
    public String toString() {
        return "Antrenorul " + this.nume + " cu varsta de " + this.varsta + " si cu pokemonii:\n" + pokemonsToString();
    }

    public Pokemon getBestPokemon() {
        return Collections.max(pokemons);
    }   //<-pentru duelul final

    public String pokemonsToString() {  //<-pentru afisare
        String s = new String();
        for (Pokemon pokemon : pokemons) {
            s = s + pokemon.toString() + "\n";
        }
        return s;
    }
}
