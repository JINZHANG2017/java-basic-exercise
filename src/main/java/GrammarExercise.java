import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";
        System.out.println("请输入两列字符串");
        //接收字符串
        Scanner sc=new Scanner(System.in);
        firstWordList=sc.nextLine().trim();
        secondWordList=sc.nextLine().trim();
        //验证字符串合法性，非字母或者连续逗号，则抛出运行时异常input not valid
        String regex1="[a-zA-Z,]+"; //匹配字母或逗号
        String regex2=".*,{2}.*";  //匹配两个或以上连续逗号
        //满足regex1且不满足regex2即为有效
        boolean isFirstWordValid = Pattern.matches(regex1, firstWordList)&&!Pattern.matches(regex2, firstWordList);
        boolean isSecondWordValid = Pattern.matches(regex1, secondWordList)&&!Pattern.matches(regex2, secondWordList);
        if(!isFirstWordValid||!isSecondWordValid){
            throw new RuntimeException("input not valid");
        }
        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        System.out.println(result);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        String regex1="[a-zA-Z,]+"; //匹配字母或逗号
        String regex2=".*,{2}.*";  //匹配两个或以上连续逗号
        //满足regex1且不满足regex2即为有效
        boolean isFirstWordValid = Pattern.matches(regex1, firstWordList)&&!Pattern.matches(regex2, firstWordList);
        boolean isSecondWordValid = Pattern.matches(regex1, secondWordList)&&!Pattern.matches(regex2, secondWordList);
        if(!isFirstWordValid||!isSecondWordValid){
            throw new RuntimeException("input not valid");
        }

        List<String> list1= Arrays.asList(firstWordList.split(","));
        List<String> list2= Arrays.asList(secondWordList.split(","));
        //都转成小写
        List<String> upperCaseList1 = list1.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> upperCaseList2 = list2.stream().map(String::toUpperCase).collect(Collectors.toList());
        //找出都包含的字符串
        List<String> commonList = upperCaseList1.stream().filter(upperCaseList2::contains).collect(Collectors.toList());
        //去掉重复的元素,排序，返回
        return commonList.stream().map(s-> s.replace("", " ").trim()).distinct().sorted().collect(Collectors.toList());
    }
}
