package main;

public class BinaryTree {
    public static final String SYMBOL[] = {"+","-","*","÷"};

    protected BinaryTree leftChild;
    protected BinaryTree rightChild;
    //符号，如果该节点为非叶节点则该属性有意义
    protected String symbol;
    //分数，如果该节点为叶子节点则该属性有意义
    protected Fraction fraction;

    /***
     * 中序遍历
     * @return
     */
    public String  midTraversing() {
        BinaryTree node = this;
        StringBuilder expression = new StringBuilder();
        if(node.symbol!=null){
            String symbol = node.symbol;
            String left = node.leftChild.midTraversing();
            String right = node.rightChild.midTraversing();
            if(addBrackets(symbol,node.leftChild.symbol,1)){
                expression.append("( ").append(left+" ) ").append(symbol+" ");
            }
            else{
                expression.append(left+" ").append(symbol+" ");
            }
            if(addBrackets(symbol,node.rightChild.symbol,2)){
                expression.append("( ").append(right+ " ) ");
            }
            else{
                expression.append(right);
            }
            return expression.toString();
        }
        else{
            return node.fraction.toString();
        }
    }

    /***
     * 判断是否需要加括号
     * @param symbol1
     * @param symbol2
     * @param leftOrRight 1表示left，2表示right
     * @return
     */
    public static boolean addBrackets(String symbol1,String symbol2,int leftOrRight){
        if(symbol2==null){
            return false;
        }
        if(symbol1.equals(SYMBOL[1])){
            if(symbol2.equals(SYMBOL[1])||symbol2.equals(SYMBOL[0])) {
                if (leftOrRight == 2) return true;
            }
        }
        if(symbol1.equals(SYMBOL[2])){
            if(symbol2.equals(SYMBOL[0])||symbol2.equals(SYMBOL[1])){
                return true;
            }
        }
        if(symbol1.equals(SYMBOL[3])){
            if(symbol2.equals(SYMBOL[0])||symbol2.equals(SYMBOL[1])){
                return true;
            }
            if(leftOrRight==2){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            BinaryTree binaryTree = (BinaryTree) obj;
            boolean l = false;
            boolean r = false;
            boolean f;
            if(binaryTree.fraction!=null){
               f = binaryTree.fraction.equals(this.fraction);
            }
            else{
                f = this.fraction == null;
            }
            boolean s ;
            if(binaryTree.symbol!=null){
                s = binaryTree.symbol.equals(this.symbol);
            }
            else{
                s = this.symbol==null;
            }
            if (binaryTree.leftChild != null && binaryTree.rightChild != null&&f&&s) {
                l = binaryTree.leftChild.equals(this.leftChild);
                r = binaryTree.rightChild.equals(this.rightChild);
                if(l==false&&r==false&&(binaryTree.symbol.equals(SYMBOL[0])||binaryTree.symbol.equals(SYMBOL[2]))){
                    r = binaryTree.rightChild.equals(this.leftChild);
                    l = binaryTree.leftChild.equals(this.rightChild);
                }
            }
            else{
                l = this.leftChild==null;
                r = this.rightChild==null;
            }

                return l && r && f && s;

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hashCode = fraction!=null ? fraction.hashCode()*31 : 0;
        hashCode += symbol!=null? symbol.hashCode() : 0 ;
        hashCode += leftChild != null ? leftChild.hashCode() : 0;
        hashCode += rightChild!=null? rightChild.hashCode() : 0;
        return hashCode;
    }
}
