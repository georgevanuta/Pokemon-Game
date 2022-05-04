package com.company.pokemon;

//aceasta clasa transforma un Pokemon intr-un subtip de al sau
//reprezinta un fel de downcasting
public class ConcretePokemon {
    public static  <T extends Pokemon> void toConcretePokemon(Pokemon pokemon,T concretePokemon) {
        if (pokemon == null) throw new NullPointerException("Pokemonul este null!");
        if (!(concretePokemon instanceof Pokemon)) throw new IllegalArgumentException("Parametrul trebuie sa fie o subclasa a Pokemon!");

        concretePokemon.setName(pokemon.getName());
        concretePokemon.setHp(pokemon.getHp());
        if (pokemon.getSpecialAttack() == null) {
            concretePokemon.setAttack(pokemon.getAttack());
        } else {
            concretePokemon.setSpecialAttack(pokemon.getSpecialAttack());
        }
        concretePokemon.setDefense(pokemon.getDefense());
        concretePokemon.setSpecialDefense(pokemon.getSpecialDefense());
        concretePokemon.setFirstAbility(pokemon.getFirstAbility());
        concretePokemon.setSecondAbility(pokemon.getSecondAbility());
    }
}
