package com.company.event;

import com.company.pokemon.*;

import java.util.ArrayList;
import java.util.Random;

/*pentru a asigura randomizarea unui meci*/
public class RandomPokemon {
    enum ACTION {
        ABILITY1,
        ABILITY2,
        ATTACK,
        SPECIAL_ATTACK
    }

    private int index;  //<-indexul pokemonului ales la intamplare

    public int getIndex() {
        return index;
    }

    //check-uri pentru a verifica daca se pot folosi abilitatile
    private static boolean check1stAbility(PokemonInBattle pokemon) {
        if (pokemon.getPokemon().getFirstAbility() != null && pokemon.isFirstAbilityReady() == true) {
            return true;
        }
        return false;
    }

    private static boolean check2ndAbility(PokemonInBattle pokemon) {
        if (pokemon.getPokemon().getSecondAbility() != null && pokemon.isSecondAbilityReady() == true) {
            return true;
        }
        return false;
    }

    //daca are attack sau specialAttack
    private static ACTION attackOrSpecial(Pokemon pokemon) {
        if (pokemon.getSpecialAttack() == null) {
            return ACTION.ATTACK;
        }
        return ACTION.SPECIAL_ATTACK;
    }

    //intoarce o actiune posibila a unui pokemon in acea tura
    public static ACTION randomActionPokemon(PokemonInBattle pokemon) {
        Random random = new Random();
        ArrayList<ACTION> possibleActions = new ArrayList<ACTION>();

        if (check1stAbility(pokemon)) {
            possibleActions.add(ACTION.ABILITY1);
        }
        if (check2ndAbility(pokemon)) {
            possibleActions.add(ACTION.ABILITY2);
        }
        possibleActions.add(attackOrSpecial(pokemon.getPokemon()));

        int amountOfPossibleActions = possibleActions.size();

        return possibleActions.get(random.nextInt(amountOfPossibleActions));
    }

    //intoarce la intamplare un pokemon al antrenorului
    public Pokemon randomPokemonOfTrainer(Antrenor antrenor) {
        Random random = new Random();
        int amountOfPokemons = antrenor.getPokemons().size();
        if (amountOfPokemons == 0) throw new IllegalArgumentException("Antrenorul " + antrenor.getName() + "" +
                "nu are pokemoni!");
        index = random.nextInt(amountOfPokemons);
        return antrenor.getPokemons().get(index);
    }
}
