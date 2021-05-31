package jdk;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/24 22:52
 * Modified By:
 */
public class Test {
    public static class A{
        public void test1() {
            System.out.println("A");
        }
    }
    public static class B extends A{
        @Override
        public void test1() {
            System.out.println("B");
        }
    }
    public static void main(String[] args) {

        String s = "{\"response\":{\"Data\":[\"{\"date\":\"2021-02-25\",\"latestReadyDate\":\"2021-02-25\",\"pgc_author_all_comment_cnt\":1,\"pgc_author_all_danmaku_cnt\":0,\"pgc_author_all_digg_cnt\":5,\"pgc_author_all_play_cnt\":135,\"pgc_author_all_play_cnt_ac\":1973229,\"pgc_author_aweme_new_fans_cnt\":910,\"pgc_author_aweme_play_cnt\":12,\"pgc_author_aweme_play_cnt_ac\":78,\"pgc_author_net_new_fans_cnt\":-5}\"],\"Meta\":[{\"name\":\"pgc_author_all_play_cnt_ac\",\"type\":\"float\"},{\"name\":\"pgc_author_aweme_play_cnt\",\"type\":\"int\"},{\"name\":\"pgc_author_aweme_new_fans_cnt\",\"type\":\"int\"},{\"name\":\"pgc_author_all_play_cnt\",\"type\":\"float\"},{\"name\":\"pgc_author_aweme_play_cnt_ac\",\"type\":\"int\"},{\"name\":\"pgc_author_net_new_fans_cnt\",\"type\":\"int\"},{\"name\":\"pgc_author_all_digg_cnt\",\"type\":\"float\"},{\"name\":\"pgc_author_all_danmaku_cnt\",\"type\":\"float\"},{\"name\":\"pgc_author_all_comment_cnt\",\"type\":\"float\"},{\"name\":\"date\",\"type\":\"string\"},{\"name\":\"latestReadyDate\",\"type\":\"string\"}],\"baseResp\":{\"StatusCode\":0,\"StatusMessage\":\"成功\"}},\"address\":\"[fdbd:dc02:ff:1:1:225:122:135]:9972\",\"log_id\":\"02161484410298100000000000000000000ffff0ae1429843e893\"}";
        
        System.out.println(s.toString());
    }
}
