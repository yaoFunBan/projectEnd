package com.example.kimhuang.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Donnapa on 16-May-16.
 */
@RunWith(JUnit4.class)
public class game3Test {

    @Test
    public void randomInStorage() {
        List<Integer> random = new ArrayList<>();
        random.add(1);
        random.remove(0);
        assertEquals(0, random.size());
    }

    @Test
    public void randomInStorage2() {
        List<Integer> random = new ArrayList<>();
        random.add(1);
        random.add(2);
        random.remove(0);
        assertEquals(1, random.size());
    }

    @Test
    public void randomInStorage3() {
        List<Integer> random = new ArrayList<>();
        random.add(1);
        random.add(2);
        random.remove(1);
        assertEquals(1, (int)random.get(0));
    }

    @Test
    public void randomInStorage4() {
        List<Integer> random = new ArrayList<>();
        random.add(1);
        random.add(2);
        random.add(3);
        random.remove(0);
        assertEquals(2, (int)random.get(0));
    }


    @Test
    public void randomInStorage5() {
        Random rand = new Random();
        List<Integer> random = new ArrayList<>();
        int n = rand.nextInt(random.size());
        assertEquals(2, (int)random.get(0));
    }
}