package com.company.fileToAntrenor;

import com.company.item.ItemFactory;
import com.company.pokemon.Antrenor;
import com.company.pokemon.Pokemon;
import com.company.pokemon.PokemonFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*clasa folosita pentru a citi doi antrenori din
* fisiere cu numele "antrenor1.in", "antrenor2.in"*/

public class FileToAntrenor {
    private Antrenor antrenor1;
    private Antrenor antrenor2;

    private static Antrenor readAntrenorFromFile(String filePath) { //citeste un singur antrenor
        Antrenor antrenor = new Antrenor();
        PokemonFactory pokemonFactory = PokemonFactory.getInstanta();
        ItemFactory itemFactory = ItemFactory.getInstanta();
        Pokemon pokemon1;
        Pokemon pokemon2;
        Pokemon pokemon3;

        File file = new File(filePath);
        try {
            Scanner antrenorScanner = new Scanner(file);
            antrenor.setName(antrenorScanner.nextLine());
            antrenor.setVarsta(Integer.valueOf(antrenorScanner.nextLine()));
            pokemon1 = pokemonFactory.createPokemon(antrenorScanner.nextLine());
            String itemsLine = antrenorScanner.nextLine();
            String[] items;
            if (!itemsLine.equals("none")) {
                items = itemsLine.split(",");
                for (int i = 0; i < 3; i++) {
                    pokemon1.addItem(itemFactory.createItem(items[i]));
                }
            }
            pokemon2 = pokemonFactory.createPokemon(antrenorScanner.nextLine());
            itemsLine = antrenorScanner.nextLine();
            if (!itemsLine.equals("none")) {
                items = itemsLine.split(",");
                for (int i = 0; i < 3; i++) {
                    pokemon2.addItem(itemFactory.createItem(items[i]));
                }
            }
            pokemon3 = pokemonFactory.createPokemon(antrenorScanner.nextLine());
            itemsLine = antrenorScanner.nextLine();
            if (!itemsLine.equals("none")) {
                items = itemsLine.split(",");
                for (int i = 0; i < 3; i++) {
                    pokemon3.addItem(itemFactory.createItem(items[i]));
                }
            }


            antrenor.addPokemon(pokemon1);
            antrenor.addPokemon(pokemon2);
            antrenor.addPokemon(pokemon3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return antrenor;
    }

    public void readAntrenoriFromFolder(int nrOfTest) { // citeste ambii antrenori
        String folderName = "src/com/company/tests/test" + nrOfTest + "/";
        String firstFile = folderName + "antrenor1.in";
        String secondFile = folderName + "antrenor2.in";
        antrenor1 = readAntrenorFromFile(firstFile);    //<-ii memoreaza ca si campuri
        antrenor2 = readAntrenorFromFile(secondFile);   //
    }

    public ArrayList<Antrenor> getAntrenori() {          //<-pentru ca apoi sa-i returneaza prin get
        ArrayList<Antrenor> antrenori = new ArrayList<Antrenor>();
        antrenori.add(antrenor1);
        antrenori.add(antrenor2);
        return antrenori;
    }
}
