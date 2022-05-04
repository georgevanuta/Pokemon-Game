package com.company.event;

import com.company.myLog.MyLogger;
import com.company.pokemon.Pokemon;

import java.util.logging.Logger;

/*ajuta la simularea unui atac de la
* ofensiva la defensiva*/
public class Action {
    private static Logger logger = new MyLogger().getLogger();

    enum GameStatus {   //pentru a deterimina starea unui meci
        FIRST_WON,
        SECOND_WON,
        DRAW,
        ONGOING
    }

    private static GameStatus checkIfMatchFinished(Pokemon pokemon1, Pokemon pokemon2) {    //verifica care este starea
        if (pokemon1.getHp() <= 0 && pokemon2.getHp() <= 0) {                               //unui meci, returnand GameStatus
            logger.info("It's a draw!");
            return GameStatus.DRAW;
        }
        if (pokemon1.getHp() <= 0) {
            logger.info(pokemon2.getName() + " has won the match!");
            return GameStatus.SECOND_WON;
        }
        if (pokemon2.getHp() <= 0) {
            logger.info(pokemon1.getName() + " has won the match");
            return GameStatus.FIRST_WON;
        }
        return GameStatus.ONGOING;
    }

    public static GameStatus doAction(PokemonInBattle offensivePokemon, PokemonInBattle defensivePokemon, RandomPokemon.ACTION action) {
        String nameOff = offensivePokemon.getPokemon().getName();
        String nameDef = defensivePokemon.getPokemon().getName();
        Integer damageBlocked = 0;  //pentru afisare la final
        Integer initialDamage = 0;  //"-"

        GameStatus gameStatus = checkIfMatchFinished(offensivePokemon.getPokemon(), defensivePokemon.getPokemon());
        if (gameStatus != GameStatus.ONGOING) { //in cazul in care unul dintre pokemoni a murit
            return gameStatus;
        }

        if (defensivePokemon.getIsDodging()) {  //daca un pokemon defensiv are dodge, atunci trece peste randul inamicului
            logger.info(nameDef + " is dodging this turn!");
            defensivePokemon.stopDodging();     //dureaza doar o tura
            offensivePokemon.decCurrentCooldown();
            return gameStatus;
        }

        if (offensivePokemon.getIsStunned()) {  //daca un pokemon ofensiv are stun. atunci trece peste randul sau
            logger.info(nameOff + " is stunned this turn!");
            offensivePokemon.wakeUpFromStun();  //dureaza doar o tura
            offensivePokemon.decCurrentCooldown();
            return gameStatus;
        }

        Integer damageDealt;    //se salveaza pentru afisare
        switch (action.toString()) {
            case "ATTACK" -> {
                damageBlocked = defensivePokemon.getPokemon().getDefense();
                initialDamage = offensivePokemon.getPokemon().getAttack();
                damageDealt = initialDamage - damageBlocked;
                break;
            }
            case "SPECIAL_ATTACK" -> {
                damageBlocked = defensivePokemon.getPokemon().getSpecialDefense();
                initialDamage = offensivePokemon.getPokemon().getSpecialAttack();
                damageDealt = initialDamage - damageBlocked;
                break;
            }
            /*in cazul unui Ability, nu exista def ar pokemonului defensiv*/
            case "ABILITY1" -> {
                initialDamage = offensivePokemon.getPokemon().getFirstAbility().getDamage();
                damageDealt = initialDamage;

                if (offensivePokemon.getPokemon().getFirstAbility().getDodge() == true) {
                    offensivePokemon.dodge();
                }
                if (offensivePokemon.getPokemon().getFirstAbility().getStun() == true) {
                    defensivePokemon.stun();
                }
                offensivePokemon.resetCooldown1();
                break;
            }
            case "ABILITY2" -> {
                initialDamage = offensivePokemon.getPokemon().getSecondAbility().getDamage();
                damageDealt = initialDamage;

                if (offensivePokemon.getPokemon().getSecondAbility().getDodge() == true) {
                    offensivePokemon.dodge();
                }
                if (offensivePokemon.getPokemon().getSecondAbility().getStun() == true) {
                    defensivePokemon.stun();
                }
                offensivePokemon.resetCooldown2();
                break;
            }
            default -> {
                throw new IllegalArgumentException("That's not a valid action!");
            }
        }
        if (damageDealt < 0) damageDealt = 0;   //folosit pentru afisare
        defensivePokemon.decreaseHealth(damageDealt);
        defensivePokemon.decCurrentCooldown();

        logger.info(nameOff + " used " + action + " which dealt " + damageDealt + "(" + initialDamage  + "-" + damageBlocked + ") damage!");
        logger.info(nameDef + " now has " + defensivePokemon.getPokemon().getHp() + " HP!");

        return GameStatus.ONGOING;  //daca ajunge pana aici, inseamna ca ambii pokemoni sunt inca in viata, sau cel defensiv
                                    //a murit in aceasta runda
    }
}