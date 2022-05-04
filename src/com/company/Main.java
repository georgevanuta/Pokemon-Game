package com.company;

import com.company.fileToAntrenor.FileToAntrenor;
import com.company.pokemon.*;
import static com.company.event.Aventura.startAdventure;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format",  // schimba formatul logger-ului in
                "%1$tF %1$tT [%4$s] %5$s%6$s%n");                       // "data" "timp" "[level]" "mesaj"

        Integer nrOfTest = 4;   // de la 0 la 9, pentru a rula diferite teste cu diferiti antrenori
                                // testul3 este singurul meci care se poate incheia dupa vsNeutrel2
                                // deoreace Mihnea are un Neutrel1(fara iteme), care poate fi invins de Neutrel2

        Antrenor antrenor1, antrenor2;
        FileToAntrenor fileToAntrenor = new FileToAntrenor();
        fileToAntrenor.readAntrenoriFromFolder(nrOfTest);
        ArrayList<Antrenor> antrenori =  fileToAntrenor.getAntrenori();
        antrenor1 = antrenori.get(0);
        antrenor2 = antrenori.get(1);

        startAdventure(antrenor1, antrenor2);
    }
}
