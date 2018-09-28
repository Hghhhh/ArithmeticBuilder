package main;

/**
 * 生成随机自然数
 */
public class NumberFactory {


    /**
     * 生成随机自然数
     * @param max,最大的值限制
     * @return 按分数形式表现
     */
    public static Fraction getNumber(int max){
        //决定生成分数还是整数
        boolean isFraction = (int)(Math.random()*2)==0 ? true : false;
        if(isFraction){
            return Fraction.newFraction(max);
        }
        else{
            int num = (int)(Math.random()*max);
            return Fraction.intChangeToFraction(num);
        }
    }
}
