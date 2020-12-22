package Proxy;

/**
 * @author: Luo
 * @description:
 * @time: 2020/10/10 16:00
 * Modified By:
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
