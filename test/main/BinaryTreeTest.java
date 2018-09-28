package main;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    Fraction fraction1;
    Fraction fraction2;
    Fraction fraction3;
    Fraction fraction4;
    BinaryTree binaryTree;
    BinaryTree binaryTree2;
    {
        fraction1 = new Fraction();
        fraction1.setMolecule(5);
        fraction1.setDenominator(9);
        fraction2 = new Fraction();
        fraction2.setMolecule(90);
        fraction2.setDenominator(9);
        fraction3 = new Fraction();
        fraction3.setMolecule(5);
        fraction3.setDenominator(9);
        fraction4 = new Fraction();
        fraction4.setMolecule(90);
        fraction4.setDenominator(9);
        binaryTree = new BinaryTree();
        binaryTree.symbol = "*";
        binaryTree.rightChild = new BinaryTree();
        binaryTree.rightChild.symbol = "÷";
        binaryTree.rightChild.leftChild = new BinaryTree();
        binaryTree.rightChild.rightChild = new BinaryTree();
        binaryTree.rightChild.leftChild.fraction = fraction1;
        binaryTree.rightChild.rightChild.fraction = fraction2;
        binaryTree.leftChild = new BinaryTree();
        binaryTree.leftChild.symbol = "÷";
        binaryTree.leftChild.leftChild = new BinaryTree();
        binaryTree.leftChild.rightChild = new BinaryTree();
        binaryTree.leftChild.leftChild.fraction = fraction4;
        binaryTree.leftChild.rightChild.fraction = fraction3;
    }
    {
        binaryTree2 = new BinaryTree();
        binaryTree2.symbol = "*";
        binaryTree2.rightChild = new BinaryTree();
        binaryTree2.rightChild.symbol = "÷";
        binaryTree2.rightChild.leftChild = new BinaryTree();
        binaryTree2.rightChild.rightChild = new BinaryTree();
        binaryTree2.rightChild.leftChild.fraction = fraction1;
        binaryTree2.rightChild.rightChild.fraction = fraction2;
        binaryTree2.leftChild = new BinaryTree();
        binaryTree2.leftChild.symbol = "÷";
        binaryTree2.leftChild.leftChild = new BinaryTree();
        binaryTree2.leftChild.rightChild = new BinaryTree();
        binaryTree2.leftChild.leftChild.fraction = fraction4;
        binaryTree2.leftChild.rightChild.fraction = fraction3;
    }
    @Test
    public void midTraversing() {
        //assertEquals("10 ÷ 5/9 * (5/9 ÷ 10) =",binaryTree.midTraversing()+" =");
        assertEquals("10 ÷ 5/9 + 5/9 ÷ 10 =",binaryTree2.midTraversing()+" =");
    }

    @Test
    public void testEquals() {
        assertEquals(true,binaryTree.equals(binaryTree2));
    }

    @Test
    public void testHashCode() {
        assertEquals(true,binaryTree.hashCode()==binaryTree2.hashCode());
        System.out.println(binaryTree2.hashCode());
    }


}