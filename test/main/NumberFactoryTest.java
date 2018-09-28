package main;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberFactoryTest {

    @Test
    public void getNumber(){
        Fraction fraction = NumberFactory.getNumber(10);
        System.out.println(fraction.toString());
    }
}