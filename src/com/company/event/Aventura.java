package com.company.event;

import com.company.myLog.MyLogger;
import com.company.pokemon.Antrenor;

import java.util.Random;
import java.util.logging.Logger;

/*aventura in sine, aici este
* toata complexitatea proiectului*/

/*am folosit Strategy pentru
* a adauga mai usor evenimente noi*/

public class Aventura {
    private static EventStrategy eventStrategy;
    private static Logger logger = new MyLogger().getLogger();

    enum Event {        //un enum pentru fiecare
        NEUTREL1,       //eveniment
        NEUTREL2,
        FINAL
    }

    private static void setEventStrategy(EventStrategy eventStrategy) {
        Aventura.eventStrategy = eventStrategy;
    }

    private static boolean executeStrategy(Antrenor antrenor1, Antrenor antrenor2) {
        return eventStrategy.executeEvent(antrenor1, antrenor2);
    }

    private static Event choseRandomEvent() {
        return Event.values()[new Random().nextInt(Event.values().length)];
    }
    //^- alege un eveniment random

    public static void startAdventure(Antrenor antrenor1, Antrenor antrenor2) {
        Event event = choseRandomEvent();
        while (event != Event.FINAL) {  //cat timp nu se alege evenimentul FINAL, antrenorii se vor
            switch (event) {            //lupta cu Neutrel1/2
                case NEUTREL1 -> {
                    setEventStrategy(new ConcreteEventVsNeutrel1());
                    break;
                }
                case NEUTREL2 -> {
                    setEventStrategy(new ConcreteEventVsNeutrel2());
                    break;
                }
                default -> {
                    throw new IllegalArgumentException("That's not an event!");
                }
            }
            if (!executeStrategy(antrenor1, antrenor2)) {                           //returneaza false atunci cand unul dintre
                logger.info("-----------The adventure is over!-----------");   //antrenori a pierdut o batalie cu Neutrel1/2
                return;
            }
            event = choseRandomEvent(); //<-se reselecteaza un eveniment random
        }
        /*intra aici cand s-a selectat FINAL*/
        setEventStrategy(new ConcreteEventVsAntrenor());
        executeStrategy(antrenor1, antrenor2);
    }
}
