package com.company.event;

import com.company.actualPokemons.Neutrel1;
import com.company.myLog.MyLogger;
import com.company.pokemon.Antrenor;
import com.company.pokemon.Pokemon;
import com.company.pokemon.PokemonFactory;

import java.util.logging.Logger;

/*evenimenul vsNeutrel1*/
public class ConcreteEventVsNeutrel1 implements EventStrategy {
    private static Logger logger = new MyLogger().getLogger();

    @Override
    public boolean executeEvent(Antrenor antrenor1, Antrenor antrenor2) {
        /* Batalia se face vsNeutrel1 */
        PokemonFactory pokemonFactory = PokemonFactory.getInstanta();
        Neutrel1 neutrel1 = (Neutrel1) pokemonFactory.createPokemon("Neutrel1");

        /* Ceremonia de deschidere a evenimentului */
        logger.info("\n\n-----Evenimentul este batalia vs Neutrel1-----");
        logger.info("--Primul antrenor este:");
        logger.info(antrenor1.toString());
        logger.info("--Iar cel de-al doilea:");
        logger.info(antrenor2.toString());
        logger.info("Sa inceapa meciurile!");

        /* alege un pokemon random de la fiecare antrenor */
        int indexPokemon1, indexPokemon2;

        RandomPokemon randomPokemon1 = new RandomPokemon();
        Pokemon pokemon1 = randomPokemon1.randomPokemonOfTrainer(antrenor1).clone();
        indexPokemon1 = randomPokemon1.getIndex();

        RandomPokemon randomPokemon2 = new RandomPokemon();
        Pokemon pokemon2 = randomPokemon2.randomPokemonOfTrainer(antrenor2).clone();
        indexPokemon2 = randomPokemon2.getIndex();


        logger.info("\n\nPrimul duel va incepe!");
        logger.info(antrenor1.getName() + " il cheama in batalie pe " + pokemon1.getName());

        /* echiparea fiecaruia cu itemele sale */
        pokemon1.equipItems();
        pokemon2.equipItems();

        /* incepe primul duel */
        logger.info("Itemele lui " + pokemon1.getName() + " sunt "  + pokemon1.itemsToString());
        logger.info("Pokemonul s-a echipat si are urmatorarele atribute:");
        logger.info(pokemon1.toString());
        Duel duel1 = new Duel(pokemon1, neutrel1.clone());
        duel1.waitForLastBattleToFinish();
        duel1.startBattle();
        if (!duel1.didFirstWin()) {
            logger.info("Antrenorul " + antrenor1.getName() + " a pierdut acest eveniment, deci toata arena!");
            logger.info("Antrenorul " + antrenor2.getName() + " a castigat arena!");
            return false;
        } else {
            antrenor1.increaseStatsOfIthPokemon(indexPokemon1);
            logger.info("Pokemonul castiagtor al lui " + antrenor1.getName() + " a evoluat!");
            logger.info(antrenor1.getPokemons().get(indexPokemon1).toString());
        }

        /* incepe al doilea meci */
        Duel duel2 = new Duel(pokemon2, neutrel1.clone());
        duel2.waitForLastBattleToFinish();
        logger.info("\n\nAl doilea duel va incepe!");
        logger.info(antrenor2.getName() + " il cheama in batalie pe " + pokemon2.getName() + "!");
        logger.info("Pokemonul s-a echipat si are urmatorarele atribute:");
        logger.info(pokemon2.toString());
        duel2.startBattle();
        if (!duel2.didFirstWin()) {
            logger.info("Antrenorul " + antrenor2.getName() + " a pierdut acest eveniment, deci toata arena!");
            logger.info("Antrenorul " + antrenor1.getName() + " a castigat arena");
            return false;
        } else {
            antrenor2.increaseStatsOfIthPokemon(indexPokemon2);
            logger.info("Pokemonul castiagtor al lui " + antrenor2.getName() + " a evoluat!");
            logger.info(antrenor2.getPokemons().get(indexPokemon2).toString());

        }

        logger.info("!!!Ambii antrenori au trecut acest eveniment!!!");
        logger.info("!!!Sa trecem la urmatorul eveniment!!!");
        return true;
    }
}
