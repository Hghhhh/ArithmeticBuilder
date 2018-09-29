package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {


    public Main() throws IOException {
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0 ;i <args.length;i++){
            if(args[i].contains("-e")){
                intPutExpression(args);
                return;
            }
            else if(args[i].contains("-r")){
                outPutExpression(args);
                return ;
            }
        }
        System.out.println("输入的参数有误，请输入\n\t-r 四则运算表达式中最大的自然数（不能为0） -n 要生成的表达式数量（不能为0）");
        System.out.println("或\n\t-e <exercisefile>.txt -a <answerfile>.txt");
    }

    private static void intPutExpression(String[] args) throws Exception {
        List<Integer> errorList = new ArrayList<Integer>();
        List<Integer> correctList = new ArrayList<Integer>();
        int errorNum = 0;
        int correctNum = 0;
        String exerciseFileName = null;
        String answerFileName = null;
        try{
            for(int i = 0;i < args.length;i++){
                if(args[i].equals("-e")){
                    exerciseFileName = args[i+1];
                }
                if(args[i].equals("-a")){
                    answerFileName = args[i+1];
                }
            }
            if(exerciseFileName==null||answerFileName==null){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println("输入的参数有误，请输入\n\t-e <exercisefile>.txt -a <answerfile>.txt");
            return ;
        }
        Path exercisesPath = FileSystems.getDefault().getPath(exerciseFileName);
        Path answersPath = FileSystems.getDefault().getPath(answerFileName);
        BufferedReader exercisesBufferedWriter = Files.newBufferedReader(exercisesPath, Charset.forName("UTF-8"));
        BufferedReader answersBufferedWriter = Files.newBufferedReader(answersPath, Charset.forName("UTF-8"));
        String expressionLine = null;
        String answer = null;
        String realResult = null;
        while ((expressionLine=exercisesBufferedWriter.readLine())!=null){
              int index  = expressionLine.indexOf(".");
              String realExpression = expressionLine.substring(index+2,expressionLine.length()-2);
              Expression expression;
              try {
                 expression  = new Expression(realExpression.trim());
              }catch (Exception e){
                  System.out.println(expressionLine.toString());
                  return ;
              }
              answer = answersBufferedWriter.readLine();
              answer = answer.substring(index+2,answer.length());
              realResult = expression.getResult().toString();
              if(answer.equals(realResult)){
                    correctList.add(Integer.parseInt(expressionLine.substring(0,index)));
                    correctNum++;
              }
              else{
                  errorList.add(Integer.parseInt(expressionLine.substring(0,index)));
                  errorNum++;
              }
        }//2'2/7 ÷ （ 7 ÷ （ 4 - 1 ）） =
        exercisesBufferedWriter.close();
        answersBufferedWriter.close();
        Path gradePath = FileSystems.getDefault().getPath("Grade.txt");
        BufferedWriter gradeBufferedWriter = Files.newBufferedWriter(gradePath, Charset.forName("UTF-8"));
        gradeBufferedWriter.write("Correct:"+correctNum+"("+ correctList.toString() +")\n");
        gradeBufferedWriter.write("Wrong:"+errorNum+"("+ errorList.toString() +")\n");
        gradeBufferedWriter.close();
        //Correct: 5 (1, 3, 5, 7, 9)
        //Wrong: 5 (2, 4, 6, 8, 10)
    }

    public static void outPutExpression(String[] args) throws Exception {
        int maxNum = 0;
        int expressionNum = 0;
        try{
            for(int i = 0; i<args.length; i++){
                if(args[i].equals("-n")){
                    expressionNum = Integer.parseInt(args[i+1]);
                }
                if(args[i].equals("-r")){
                    maxNum = Integer.parseInt(args[i+1]);
                }
            }
            if(expressionNum==0||maxNum==0){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println("输入有误，请输入：\n\t-r 四则运算表达式中最大的自然数（不能为0） -n 要生成的表达式数量（不能为0）");
            return;
        }

        Set<Expression> expressions = new HashSet<Expression>();
        Path exercisesPath = FileSystems.getDefault().getPath("Exercises.txt");
        Path answersPath = FileSystems.getDefault().getPath("Answers.txt");
        BufferedWriter exercisesBufferedWriter = Files.newBufferedWriter(exercisesPath, Charset.forName("UTF-8"));
        BufferedWriter answersBufferedWriter = Files.newBufferedWriter(answersPath, Charset.forName("UTF-8"));
        for(int i = 1;i<=expressionNum;){
            try {
                Expression expression = new Expression(maxNum);
                //如果表达式不重复，则写入输出文件
                if(expressions.add(expression)){
                    exercisesBufferedWriter.write(i +". "+expression.getExpression()+"\n");
                    answersBufferedWriter.write(i +". "+expression.getResult().toString()+"\n");
                    i++;
                }
            } catch (Exception e) {

            }
        }
        exercisesBufferedWriter.flush();
        exercisesBufferedWriter.close();
        answersBufferedWriter.flush();
        answersBufferedWriter.close();
    }
}
