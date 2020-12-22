package lambda;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Luo
 * @description:
 * 找出2011年发生的所有交易，并按交易额排序
 *
 * 交易员在哪些不同的城市工作过
 *
 * 查找所有来自剑桥的交易员，并按姓名排序
 *
 * 返回所有交易员的姓名字符串，并按字母顺序排序
 *
 * 有没有交易员在米兰工作的？
 *
 * 打印生活在剑桥的交易员的所有交易额
 *
 * 所有交易中，最高的交易额是多少
 *
 * 找到交易额最小的交易
 * @time: 2020/6/7 13:24
 * Modified By:
 */
public class lambdaTest {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 找出2011年发生的所有交易，并按交易额排序
        List<Transaction> list1 = transactions.stream().filter(transaction -> transaction.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(list1);
        // 交易员在哪些不同的城市工作过 .map 已知一个对象集合,按需求将部分属性抽取出来组成新的集合
        List<String> city = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(city);
        // 查找所有来自剑桥的交易员，并按姓名排序https://blog.csdn.net/Zereao/article/details/90208606
        List<String> name = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getName())).sorted(Comparator.comparing(transaction -> transaction.getTrader().getName())).map(transaction -> transaction.getTrader().getName()).collect(Collectors.toList());
        System.out.println(name);
        // 返回所有交易员的姓名字符串，并按字母顺序排序,reduce可以实现从Stream中生成一个值，其生成的值不是随意的，而是根据指定的计算模型。
        String string = transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce((n1,n2) -> n1 + n2).get();
        System.out.println(string);
        // 有没有交易员在米兰工作的？
        //anyMatch表示，判断的条件里，任意一个元素成功，返回true
        //allMatch表示，判断条件里的元素，所有的都是，返回true
        //noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
        boolean milanBased = transactions.stream().anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(milanBased);
        //打印生活在剑桥的交易员的所有交易额
        long money = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity())).map(transaction -> transaction.getValue()).reduce((n1,n2)-> n1 + n2).get();
        System.out.println(money);
        // 所有交易中，最高的交易额是多少
        int highValue = transactions.stream().max(Comparator.comparing(Transaction::getValue)).get().getValue();
        System.out.println(highValue);
        // 找到交易额最小的交易
        Transaction transaction = transactions.stream().min(Comparator.comparing(Transaction::getValue)).get();
        System.out.println(transaction);
    }
}
