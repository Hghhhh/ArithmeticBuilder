package main;

import org.junit.Test;

import static org.junit.Assert.*;
public class TestForBug {
    Fraction fraction1 = new Fraction();
    Fraction fraction2= new Fraction();
    Fraction fraction3= new Fraction();
    Fraction fraction4= new Fraction();

    {
        fraction1.setMolecule(1);
        fraction1.setDenominator(1);
        fraction2.setDenominator(2);
        fraction2.setMolecule(13);
        fraction3.setDenominator(1);
        fraction3.setMolecule(4);
        fraction4.setMolecule(3);
        fraction4.setDenominator(1);
    }

    @Test
    public void testResult() throws Exception {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.symbol="-";
        binaryTree.rightChild = new BinaryTree();
        binaryTree.rightChild.fraction =fraction4;
        binaryTree.leftChild = new BinaryTree();
        binaryTree.leftChild.symbol="-";
        binaryTree.leftChild.rightChild = new BinaryTree();
        binaryTree.leftChild.rightChild.fraction = fraction3;
        binaryTree.leftChild.leftChild = new BinaryTree();
        binaryTree.leftChild.leftChild.symbol="-";
        binaryTree.leftChild.leftChild.rightChild = new BinaryTree();
        binaryTree.leftChild.leftChild.rightChild.fraction = fraction2;
        binaryTree.leftChild.leftChild.leftChild = new BinaryTree();
        binaryTree.leftChild.leftChild.leftChild.fraction = fraction1;
        // Expression expression = new Expression("8 ÷ ( 4 - 3 + 1 )");
        // assertEquals("4",expression.getResult().toString());
        System.out.println(binaryTree.midTraversing());
        Expression expression2 = new Expression();
        assertEquals("-12'-1/2",expression2.getResult(binaryTree).toString());
        // assertEquals(true,binaryTree.midTraversing().equals(expression.getRoot().midTraversing()));
        System.out.println(binaryTree.midTraversing());
        System.out.println(expression2.getResult(binaryTree));
        //4'1/5 + 6 - 1/2 - 9'3/5 = 19'3/10
    }
}
