package com.company.pokemon;

/* Am folosit Builder pentru Pokemon deoarece exista o multitudine de pokemoni care au diferite campuri nule
   (cum ar fi attack/specialAttack, firstAbility si secondAbility) */
public class PokemonBuilder {
    private final Pokemon pokemon = new Pokemon();

    public Pokemon build() {
        return this.pokemon;
    }

    public PokemonBuilder withName(String name) {
        this.pokemon.setName(name);
        return this;
    }

    public PokemonBuilder withHp(Integer hp) {
        this.pokemon.setHp(hp);
        return this;
    }

    public PokemonBuilder withAttack(Integer attack) {
        this.pokemon.setAttack(attack);
        return this;
    }

    public PokemonBuilder withSpecialAttack(Integer specialAttack) {
        this.pokemon.setSpecialAttack(specialAttack);
        return this;
    }

    public PokemonBuilder withDef(Integer def) {
        this.pokemon.setDefense(def);
        return this;
    }

    public PokemonBuilder withSpecialDef(Integer specialDef) {
        this.pokemon.setSpecialDefense(specialDef);
        return this;
    }

    public PokemonBuilder withFirstAbility(Ability ability) {
        this.pokemon.setFirstAbility(ability);
        return this;
    }

    public PokemonBuilder withSecondAbility(Ability ability) {
        this.pokemon.setSecondAbility(ability);
        return this;
    }
}
