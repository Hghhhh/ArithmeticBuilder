package main;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ExpressionTest {
    Fraction fraction1;
    Fraction fraction2;
    Fraction fraction3;
    Fraction fraction4;
    BinaryTree binaryTree;
    BinaryTree binaryTree2;
    Expression expression;
    {

            fraction1 = new Fraction();
            fraction1.setMolecule(34);
            fraction1.setDenominator(7);
            fraction2 = new Fraction();
            fraction2.setMolecule(2);
            fraction2.setDenominator(1);
            fraction3 = new Fraction();
            fraction3.setMolecule(4);
            fraction3.setDenominator(1);
            fraction4 = new Fraction();
            fraction4.setMolecule(2);
            fraction4.setDenominator(1);
            binaryTree = new BinaryTree();
            binaryTree.symbol = "+";
            binaryTree.rightChild = new BinaryTree();
            binaryTree.rightChild.symbol = "+";
            binaryTree.rightChild.leftChild = new BinaryTree();
            binaryTree.rightChild.rightChild = new BinaryTree();
            binaryTree.rightChild.leftChild.fraction = fraction2;
            binaryTree.rightChild.rightChild.fraction = fraction1;
            binaryTree.leftChild = new BinaryTree();
            binaryTree.leftChild.fraction = fraction3;
        try {
            expression = new Expression(10);

        } catch (Exception e) {
            e.printStackTrace();
        }

        binaryTree2 = new BinaryTree();
        binaryTree2.symbol = "+";
        binaryTree2.leftChild = new BinaryTree();
        binaryTree2.leftChild.symbol = "+";
        binaryTree2.leftChild.leftChild = new BinaryTree();
        binaryTree2.leftChild.rightChild = new BinaryTree();
        binaryTree2.leftChild.rightChild.fraction = fraction2;
        binaryTree2.leftChild.leftChild.fraction =fraction1;
        binaryTree2.rightChild = new BinaryTree();
        binaryTree2.rightChild.fraction = fraction3;

    }

    {
        fraction1.setMolecule(8);
        fraction1.setDenominator(1);
        fraction2.setDenominator(1);
        fraction2.setMolecule(4);
        fraction3.setDenominator(1);
        fraction3.setMolecule(3);
        fraction4.setMolecule(1);
        fraction4.setDenominator(1);
    }

    @Test
    public void generateBinaryTree() {
            for(int i=0;i<10;i++) {
                try {
                    Expression expression = new Expression(10);
                    System.out.println(expression.getExpression());
                }catch (Exception e){

                }
            }
    }

    @Test
    public void getResult() throws Exception {
        assertEquals("7/136",expression.getResult(binaryTree).toString());
    }

    @Test
    public void operation() throws Exception {
        assertEquals("10'5/9",expression.operation("+",fraction1,fraction2).toString());
        assertEquals("-9'-4/9",expression.operation("-",fraction1,fraction2).toString());
        assertEquals("5'5/9",expression.operation("*",fraction1,fraction2).toString());
        assertEquals("1/18",expression.operation("÷",fraction1,fraction2).toString());
    }

    @Test
    public void testEquals() throws Exception {
        System.out.println(binaryTree2.hashCode()+"   "+binaryTree.hashCode());
        Set<Expression> expressions = new HashSet<Expression>();
        expression.setRoot(binaryTree);
        expression.setResult(expression.getResult(binaryTree));
        expression.setExpression(binaryTree.midTraversing()+" =");
        Expression expression2 = new Expression(10);
        expression2.setRoot(binaryTree2);
        expression2.setResult(expression2.getResult(binaryTree2));
        expression2.setExpression(binaryTree2.midTraversing()+" = ");
        assertEquals(true,expression2.equals(expression));
        expressions.add(expression);
        assertEquals(false,expressions.add(expression2));
        System.out.println(expressions.toString()+expressions.size());
    }

    @Test
    public void testHashCode() throws Exception {
        expression.setRoot(binaryTree);
        expression.setResult(expression.getResult(binaryTree));
        expression.setExpression(binaryTree.midTraversing()+" =");
        Expression expression2 = new Expression(10);
        expression2.setRoot(binaryTree2);
        expression2.setResult(expression2.getResult(binaryTree2));
        expression2.setExpression(binaryTree2.midTraversing()+" = ");
        System.out.println(binaryTree2.hashCode()+"   "+binaryTree.hashCode());
        System.out.println(expression2.hashCode()+"  "+expression.hashCode());
        assertEquals(true,binaryTree2.hashCode()==binaryTree.hashCode());
        assertEquals(true,expression2.hashCode()==expression.hashCode());
    }

    @Test
    public void changExpressionToReversePoland() {
        Expression expression = new Expression();

        assertEquals("12 21 + 1'3/4 *",expression.changExpressionToReversePoland("( 12 + 21 ) * 1'3/4"));

        String s = expression.changExpressionToReversePoland("2'2/7 ÷ ( 7 ÷ ( 4 - 1 )  )");

        assertEquals("2'2/7 7 4 1 - ÷ ÷",s);

        String[] elements = s.split(" ");
        for(int i=0;i<elements.length;i++){
            System.out.println(elements[i]);
        }
        System.out.println(elements);


    }

    @Test
    public void reversePolandToTree() throws Exception {
        Expression expression = new Expression();
        String[] elements = expression.changExpressionToReversePoland("( 12 + 21 ) * 1'3/4 =").split(" ");
        expression.setTheLengthOfprofixExpr(elements.length-1);
        BinaryTree binaryTree = new BinaryTree();
        expression.reversePolandToTree(elements,binaryTree);
        String s = binaryTree.midTraversing();
        String result = expression.getResult(binaryTree).toString();
        System.out.println(s+" = " + result);
    }

}