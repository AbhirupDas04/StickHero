package com.stickhero.stickhero;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class Testing_Facility {
    @Test
    public void Database_Test(){
        BufferedReader fileInputStream;
        try {
            fileInputStream = new BufferedReader(new FileReader("Game_Details.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Present");
            return;
        }
        int n_entries = -1;
        try {
            n_entries = Integer.parseInt(fileInputStream.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<HistoryUnit> list;

        if(n_entries != 0){
            ObjectInputStream in;
            try {
                in = new ObjectInputStream(new FileInputStream("Game_Records.txt"));
                try {
                    list = (ArrayList<HistoryUnit>) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for(HistoryUnit unit : list){
                assertNotNull(unit);
            }
        }
    }

    @Test
    public void SingletonTest(){
        Hero hero1 = Hero.getInstance();
        Hero hero2 = Hero.getInstance();

        assertEquals(hero1,hero2);
    }
}