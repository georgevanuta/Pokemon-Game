package com.company.event;

import com.company.myLog.MyLogger;
import com.company.pokemon.Antrenor;
import com.company.pokemon.Pokemon;

import java.util.logging.Logger;

/*evenimentul vsAntrenor*/
public class ConcreteEventVsAntrenor implements EventStrategy {
    private static Logger logger = new MyLogger().getLogger();
    private static final int maxNumberOfPokemons = 3;   //3 pokemoni per antrenor
    @Override
    public boolean executeEvent(Antrenor antrenor1, Antrenor antrenor2) {
        Boolean didFirstWin;

        logger.info("\n\n-----Evenimentul este batalia Antrenor vs Antrenor-----");
        logger.info("--Primul antrenor este:");
        logger.info(antrenor1.toString());
        logger.info("--Iar cel de-al doilea:");
        logger.info(antrenor2.toString());
        logger.info("Sa inceapa meciurile!");

        for (int i = 0; i < maxNumberOfPokemons; i++) {                 //<-bataliile dintre toti pokemonii, in ordine
            Pokemon pokemon1 = antrenor1.getPokemons().get(i).clone();
            Pokemon pokemon2 = antrenor2.getPokemons().get(i).clone();

            logger.info("\n\n---Batalia numarul " + (i + 1) + " este intre " + pokemon1.getName() + " si " + pokemon2.getName() + "\n\n");

            pokemon1.equipItems();
            logger.info("\nItemele lui " + pokemon1.getName() + " sunt " + pokemon1.itemsToString() + "\n");
            logger.info("Si are urmatoarele stats dupa echipare:");
            logger.info(pokemon1.toString());

            pokemon2.equipItems();
            logger.info("\nItemele lui " + pokemon2.getName() + " sunt " + pokemon2.itemsToString() + "\n");
            logger.info("Si are urmatoarele stats dupa echipare:");
            logger.info(pokemon2.toString() + "\n");

            Duel duel = new Duel(pokemon1, pokemon2);
            duel.waitForLastBattleToFinish();
            duel.startBattle();

            if (duel.didFirstWin()) {
                logger.info("!!Castigatorul este " + pokemon1.getName() + "!!");
                antrenor1.increaseStatsOfIthPokemon(i);
                logger.info("Pokemonul a evoluat si are urmatoare stats:");
                logger.info(antrenor1.getPokemons().get(i).toString());
            } else {
                logger.info("!!Castigatorul este " + pokemon2.getName() + "!!");
                antrenor2.increaseStatsOfIthPokemon(i);
                logger.info("Pokemon a evoluat si are urmatoarele stats:");
                logger.info(antrenor2.getPokemons().get(i).toString());
            }
        }
        /*ultima batalie*/
        Pokemon bestPokemon1 = antrenor1.getBestPokemon().clone();
        Pokemon bestPokemon2 = antrenor2.getBestPokemon().clone();

        /*prezentari generale*/
        logger.info("\n\n------Urmeaza ultima batalie intre cei mai buni pokemoni ai fiecarui antrenor!------\n\n");

        logger.info("Cel mai bun pokemon al lui " + antrenor1.getName() + " este:");
        logger.info(bestPokemon1.toString() + "\n");

        logger.info("Iar cel mai bun pokemon al lui " + antrenor2.getName() + " este:");
        logger.info(bestPokemon2.toString() + "\n");

        /*echiparea*/
        logger.info(bestPokemon1.getName() + " are urmatoare iteme: " + bestPokemon1.itemsToString());
        logger.info("si urmatoarele stats dupa echipare:");
        bestPokemon1.equipItems();
        logger.info(bestPokemon1.toString() + "\n");

        logger.info(bestPokemon2.getName() + " are urmarele iteme: " + bestPokemon2.itemsToString());
        logger.info("si urmatoarele stats dupa echipare:");
        bestPokemon2.equipItems();
        logger.info(bestPokemon2.toString() + "\n");

        /*duelul propriu-zis*/
        logger.info("Acum ca a trecut echiparea, sa inceapa ultimul meci!");

        Duel duelFinal = new Duel(bestPokemon1, bestPokemon2);
        duelFinal.waitForLastBattleToFinish();
        duelFinal.startBattle();

        /*concluzia aventurii*/
        didFirstWin = duelFinal.didFirstWin();
        if (didFirstWin) {
            logger.info("!!!!!!" + bestPokemon1.getName() + " a castigat meciul final!!!!!");
            logger.info("Iar " + antrenor1.getName() + " a castigat toata aventura!");
        } else {
            logger.info("!!!!!!" + bestPokemon2.getName() + " a castigat meciul final!!!!!");
            logger.info("Iar " + antrenor2.getName() + " a castigat toata aventura!");
        }
        return didFirstWin;
    }
}
