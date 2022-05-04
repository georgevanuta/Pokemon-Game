package com.company.event;

import com.company.pokemon.Pokemon;
import static com.company.event.Action.*;

/*Aici se reprezinta fiecare duel folosind doua thread-uri
* reprezentand fiecare pokemon*/
public class Duel {
    private static final Integer TIME_TO_SLEEP = 500;  //cat sa stea intre actiuni

    private static PokemonInBattle pokemon1, pokemon2;
    private static Object lock = new Object();
    private static boolean t1turn = true;               //pentru a determina al cui e randul
    private Thread thread1, thread2;                    //thread-urile
    private static GameStatus gameStatus;               //status-ul duelului
    private static Boolean didFirstWin;                 /*<-daca a castigat primul pokemon->in vsNeutrel1/2 daca e true
                                                         *inseamna ca a castigat antrenorul, iar in vsAntrenor inseamna
                                                         *ca a castigat primul antrenor*/
    public Duel(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = new PokemonInBattle(pokemon1);
        this.pokemon2 = new PokemonInBattle(pokemon2);
        initializeThreads();
    }

    private void initializeThreads() {  //<-pregatirea thread-urilor
        thread1 = new Thread() {        //<-reprezinta pokemon1

            @Override
            public void run() {
                synchronized (lock) {
                    while (areAlive()) {
                        gameStatus = pokemon1.doRandomAction(pokemon2);
                        try {
                            sleep(TIME_TO_SLEEP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t1turn = false;
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    if (pokemon1.getPokemon().getHp() <= 0) didFirstWin = false;
                    else didFirstWin = true;
                }

            }
        };
        thread2 = new Thread() {    //<-reprezinta pokemon2

            @Override
            public void run() {
                synchronized (lock) {
                    while (areAlive()) {
                        if (t1turn)
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                            }
                        gameStatus = pokemon2.doRandomAction(pokemon1);
                        try {
                            sleep(TIME_TO_SLEEP);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t1turn = true;
                        lock.notify();
                    }
                }
                if (pokemon1.getPokemon().getHp() <= 0) didFirstWin = false;    //<-calculul castigatorului
                else didFirstWin = true;
            }
        };
    }

    public GameStatus startBattle() {   //<-incepe batalia
        thread1.start();
        thread2.start();
        waitForLastBattleToFinish();
        return gameStatus;
    }

    public void waitForLastBattleToFinish() {   //<-pentru a nu se suprapune bataliile
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean areAlive() {
        if (pokemon1.getPokemon().getHp() <= 0 || pokemon2.getPokemon().getHp() <= 0) {
            return false;
        }
        return true;
    }               //<-verifica daca ambii pokemoni sunt in viata

    public boolean didFirstWin() {
        return didFirstWin;
    }
}
