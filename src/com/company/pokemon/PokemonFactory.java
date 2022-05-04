package com.company.pokemon;

import com.company.actualPokemons.*;

import static com.company.pokemon.ConcretePokemon.toConcretePokemon;

/*am creat subclase pentru fiecare tip de pokemon deoarece
* ar deschide mai multe oportunitati pentru a adauga noi
* functionalitati pentru fiecare tip de pokemon */

/*de asemenea, este singleton deoarece nu este nevoie de
* mai mult de o fabrica*/

public class PokemonFactory {
    private static PokemonFactory instantaFactory;

    private PokemonFactory() {}

    public static PokemonFactory getInstanta() {
        if (instantaFactory == null) {
            instantaFactory = new PokemonFactory();
        }
        return instantaFactory;
    }

    public Pokemon createPokemon(String name) {
        Pokemon pokemon = new Pokemon();
        switch (name) {
            case "Neutrel1":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(10)
                        .withAttack(3)
                        .withDef(1)
                        .withSpecialDef(1)
                        .build();
                Neutrel1 neutrel1 = new Neutrel1();
                toConcretePokemon(pokemon, neutrel1);
                return neutrel1;
            case "Neutrel2":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(20)
                        .withAttack(4)
                        .withDef(1)
                        .withSpecialDef(1)
                        .build();
                Neutrel2 neutrel2 = new Neutrel2();
                toConcretePokemon(pokemon, neutrel2);
                return neutrel2;
            case "Pikachu":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(35)
                        .withSpecialAttack(4)
                        .withDef(2)
                        .withSpecialDef(3)
                        .withFirstAbility(new Ability(6, false ,false, 4))
                        .withSecondAbility(new Ability(4, true, true, 5))
                        .build();
                Pikachu pikachu = new Pikachu();
                toConcretePokemon(pokemon, pikachu);
                return pikachu;
            case "Bulbasaur":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(42)
                        .withSpecialAttack(5)
                        .withDef(3)
                        .withSpecialDef(1)
                        .withFirstAbility(new Ability(6, false, false, 4))
                        .withSecondAbility(new Ability(5, false, false, 3))
                        .build();
                Bulbasaur bulbasaur = new Bulbasaur();
                toConcretePokemon(pokemon, bulbasaur);
                return bulbasaur;
            case "Charmander":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(50)
                        .withAttack(4)
                        .withDef(3)
                        .withSpecialDef(2)
                        .withFirstAbility(new Ability(4, true, false, 4))
                        .withSecondAbility(new Ability(7, false, false, 6))
                        .build();
                Charmander charmander = new Charmander();
                toConcretePokemon(pokemon, charmander);
                return charmander;
            case "Squirtle":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(60)
                        .withSpecialAttack(3)
                        .withDef(5)
                        .withSpecialDef(5)
                        .withFirstAbility(new Ability(4, false, false, 3))
                        .withSecondAbility(new Ability(2, true, false, 2))
                        .build();
                Squirtle squirtle = new Squirtle();
                toConcretePokemon(pokemon, squirtle);
                return squirtle;
            case "Snorlax":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(62)
                        .withAttack(3)
                        .withDef(6)
                        .withSpecialDef(4)
                        .withFirstAbility(new Ability(4, true, false, 5))
                        .withSecondAbility(new Ability(0, false, true, 5))
                        .build();
                Snorlax snorlax = new Snorlax();
                toConcretePokemon(pokemon, snorlax);
                return snorlax;
            case "Vulpix":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(36)
                        .withAttack(5)
                        .withDef(2)
                        .withSpecialDef(4)
                        .withFirstAbility(new Ability(8, true, false, 6))
                        .withSecondAbility(new Ability(2, false, true, 7))
                        .build();
                Vulpix vulpix = new Vulpix();
                toConcretePokemon(pokemon, vulpix);
                return vulpix;
            case "Eevee":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(39)
                        .withSpecialAttack(4)
                        .withDef(3)
                        .withSpecialDef(3)
                        .withFirstAbility(new Ability(5, false, false, 3))
                        .withSecondAbility(new Ability(3, true, false, 3))
                        .build();
                Eevee eevee = new Eevee();
                toConcretePokemon(pokemon, eevee);
                return eevee;
            case "Jigglypuff":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(34)
                        .withAttack(4)
                        .withDef(2)
                        .withSpecialDef(3)
                        .withFirstAbility(new Ability(4, true, false, 4))
                        .withSecondAbility(new Ability(3, true, false, 4))
                        .build();
                Jigglypuff jigglypuff = new Jigglypuff();
                toConcretePokemon(pokemon, jigglypuff);
                return jigglypuff;
            case "Meowth":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(41)
                        .withAttack(3)
                        .withDef(4)
                        .withSpecialDef(2)
                        .withFirstAbility(new Ability(5, false, true, 4))
                        .withSecondAbility(new Ability(1, false, true, 3))
                        .build();
                Meowth meowth = new Meowth();
                toConcretePokemon(pokemon, meowth);
                return meowth;
            case "Psyduck":
                pokemon = new PokemonBuilder()
                        .withName(name)
                        .withHp(43)
                        .withAttack(3)
                        .withDef(3)
                        .withSpecialDef(3)
                        .withFirstAbility(new Ability(2, false, false, 4))
                        .withSecondAbility(new Ability(2, true, false, 5))
                        .build();
                Psyduck psyduck = new Psyduck();
                toConcretePokemon(pokemon, psyduck);
                return psyduck;
        } throw new IllegalArgumentException("Pokemonul " + name + " nu este valid!");
    }
}
