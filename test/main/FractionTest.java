package main;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractionTest {
    Fraction fraction1;
    Fraction fraction2;
    {
        fraction1 = new Fraction();
        fraction1.setMolecule(5);
        fraction1.setDenominator(9);
        fraction2 = new Fraction();
        fraction2.setMolecule(90);
        fraction2.setDenominator(9);
    }
    @Test
    public void testHashCode() {
        Fraction fraction = new Fraction();
        fraction.setMolecule(4);
        fraction.setDenominator(9);
        assertEquals("4/9", fraction.toString());
        assertEquals(4123,fraction.hashCode());
    }

    @Test
    public void add() {
        assertEquals("10'5/9",Fraction.add(fraction1,fraction2).toString());
    }

    @Test
    public void subtraction() {
        assertEquals("-9'-4/9",Fraction.subtraction(fraction1,fraction2).toString());
    }

    @Test
    public void multiplication() {
        assertEquals("5'5/9",Fraction.multiplication(fraction1,fraction2).toString());
    }

    @Test
    public void division() throws Exception {
        assertEquals("1/18",Fraction.division(fraction1,fraction2).toString());
    }

    @Test
    public void equals() {
        Fraction fraction3 = new Fraction();
        fraction3.setDenominator(9);
        fraction3.setMolecule(5);
        assertEquals(true,fraction3.equals(fraction1));
    }
}