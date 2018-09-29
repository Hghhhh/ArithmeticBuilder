package main;

import java.util.Stack;

public class Expression {

    public static final String SYMBOL[] = {"+","-","*","÷"};

    private static final int MAXSYMBOL = 3;

    private int theLengthOfprofixExpr;

    //这个表达式生成的二叉树
    private BinaryTree root;

    //计算结果
    private Fraction result;

    //表达式的字符串
    private String expression;

    public Expression(){
        super();
    }


    /**
     * 按照限制的最大自然数随机生成表达式二叉树的构造方法
     * @param maxNum
     * @throws Exception
     */
    public Expression(int maxNum) throws Exception {
        //随机决定符号数量
        int symbolNum = (int)(Math.random()*MAXSYMBOL)+1;
        root = generateBinaryTree(maxNum,symbolNum);
        result =getResult(root);
        expression = root.midTraversing()+" =";
    }

    /**
     * 按照传递进来的表达式生成二叉树
     * @param expression
     */
    public Expression(String expression) throws Exception {
        String profixExpr = changExpressionToReversePoland(expression);
        String[] elements = profixExpr.split(" ");
        String[] realElements = new String[elements.length];
        int j=0;
        //去掉split分割后可能出现的空字符串元素
        for(int i=0;i<elements.length;i++){
            if(elements[i].equals("")||elements[i].isEmpty()){

            }
            else{
                realElements[j] = elements[i];
                j++;
            }
        }
        theLengthOfprofixExpr = j-1;
        BinaryTree binaryTree = new BinaryTree();
        reversePolandToTree(realElements,binaryTree);
        root = binaryTree;
        result = getResult(root);
    }


    /**
     * 将普通表达式转化为逆波兰表达式
     * @param expression
     * @return
     */
    public String changExpressionToReversePoland(String expression){
        //逆波兰表达式
        StringBuilder profixExpr = new StringBuilder();
        String[] elements = expression.split(" ");
        Stack<String> stack = new Stack<String>();
        String element = null;
        String pop = null;
        for(int i=0; i<elements.length; i++){
            element = elements[i];
            //如果当前字符为操作数
            if(!isSymbol(element)&&!element.equals("(")&&!element.equals(")")) {
                profixExpr.append(element+" ");
            }
            //如果当前字符为操作符
            else if(isSymbol(element)) {
                if(stack.isEmpty())
                    stack.push(element);
                else {

                    while(true) {
                        if(stack.isEmpty() || priority(stack.peek()) < priority(element))
                            break;
                        pop = stack.pop();
                        profixExpr.append(pop+" ");
                    }
                    stack.push(element);
                }
            }
            //如果当前字符为‘(’
            else if("(" .equals(element) ) {
                stack.push(element);
            }
            //如果当前字符为‘)’
            else if(")".equals(element)) {
                while(!(pop = stack.pop() ).equals("("))
                    profixExpr.append(pop+" ");
            }
            else
                System.out.println("输入文件中表达式有错误");
        }
        while(!stack.isEmpty()){
            profixExpr.append(stack.pop()+" ");
        }
        String profixExprTem = profixExpr.toString();
        return profixExprTem.substring(0,profixExprTem.length()-1);//去掉最后的" "
    }

    /**
     * 由逆波兰表达式生成二叉树
     * @param elements
     * @param node
     */
    public void reversePolandToTree(String[] elements,BinaryTree node){
        if(isSymbol(elements[theLengthOfprofixExpr])){
            node.symbol = elements[theLengthOfprofixExpr];
            theLengthOfprofixExpr--;
            node.rightChild = new BinaryTree();
            reversePolandToTree(elements,node.rightChild);
            node.leftChild = new BinaryTree();
            reversePolandToTree(elements,node.leftChild);
        }
        else{
            node.fraction = new Fraction(elements[theLengthOfprofixExpr]);
            theLengthOfprofixExpr--;
            return ;
        }
    }

    /**
     * 随机生成二叉树
     * @param maxNum
     * @param symbolNum
     * @return
     */
    public BinaryTree generateBinaryTree(int maxNum, int symbolNum){
        BinaryTree binaryTree = new BinaryTree();
        if(symbolNum==0){
            binaryTree.fraction = NumberFactory.getNumber(maxNum);
        }
        else{
            binaryTree.symbol = SYMBOL[(int)(Math.random() * 4)];
            int leaveSymbolNum = symbolNum-1;
            int symbolNumToLeft = (int)(Math.random()*(leaveSymbolNum+1));
            binaryTree.leftChild = generateBinaryTree(maxNum,symbolNumToLeft);
            binaryTree.rightChild = generateBinaryTree(maxNum,leaveSymbolNum-symbolNumToLeft);
        }
        return binaryTree;
    }

    /**
     * 计算二叉树的结果
     * @param binaryTree
     * @return
     * @throws Exception
     */
    public Fraction getResult(BinaryTree binaryTree) throws Exception {
        if(binaryTree.leftChild==null&&binaryTree.rightChild==null){
            return binaryTree.fraction;
        }
        else{
            String symbol = binaryTree.symbol;
            Fraction leftChildFraction = getResult(binaryTree.leftChild);
            Fraction rightChildFraction = getResult(binaryTree.rightChild);
            binaryTree.fraction = operation(symbol,leftChildFraction,rightChildFraction);
            //若结果为负数，左右子树交换，值取绝对值
            if(binaryTree.fraction.getMolecule()<0){
                binaryTree.fraction.setMolecule(Math.abs(binaryTree.fraction.getMolecule()));
                BinaryTree node = binaryTree.leftChild;
                binaryTree.leftChild = binaryTree.rightChild;
                binaryTree.rightChild = node;
            }
            return binaryTree.fraction;
        }
    }

    public Fraction operation(String symbol,Fraction leftChildFraction, Fraction rightChildFraction) throws Exception {
            switch (symbol){
                case "+":
                    return Fraction.add(leftChildFraction,rightChildFraction);
                case "-":
                    return Fraction.subtraction(leftChildFraction,rightChildFraction);
                case "*":
                    return Fraction.multiplication(leftChildFraction,rightChildFraction);
                case "÷":
                    return Fraction.division(leftChildFraction,rightChildFraction);
                default:
                    System.out.println("error");
                    return null;
            }
    }

    private boolean isSymbol(String element){
        for(int i=0;i<4;i++){
            if(element.equals(SYMBOL[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到当前操作符的优先级
     */
    private int priority(String symbol) {
        switch(symbol) {
            case "*":
            case "÷":
                return 3;
            case "+":
            case "-":
                return 2;
            case "(":
                return 1;
            default:
                return 0;

        }
    }


    /**
     *  重写判断两个Expression对象是否相同的方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Expression expression = (Expression)obj;
        //先判断表达式的结果是否相同
        if(expression.getResult().toString().equals(this.result.toString())){
            //再判断二叉树是否相同
            if(expression.getRoot().equals(this.root)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return root.hashCode()+result.hashCode();
    }


    public Fraction getResult() {
        return result;
    }

    public void setResult(Fraction result) {
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public BinaryTree getRoot() {
        return root;
    }

    public void setRoot(BinaryTree root) {
        this.root = root;
    }

    public  int getTheLengthOfprofixExpr() {
        return theLengthOfprofixExpr;
    }

    public  void setTheLengthOfprofixExpr(int theLengthOfprofixExpr) {
        this.theLengthOfprofixExpr = theLengthOfprofixExpr;
    }

}
