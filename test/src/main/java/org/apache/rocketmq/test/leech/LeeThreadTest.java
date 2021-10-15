package org.apache.rocketmq.test.leech;

public class LeeThreadTest {
    public static void main(String[] args) {
        //https://blog.51cto.com/AntzUhl/2864091
        new LeeMsgService().start();
    }

}
