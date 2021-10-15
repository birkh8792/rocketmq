package org.apache.rocketmq.test.leech;

public class ForTest {

    public static void main(String[] args) {
        final int maxRetries = 30;
        System.out.println("for begin...");
        for (int i = 0; i < maxRetries; i++) {
            System.out.println("current i = "+i);
            if(i == 20){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("for exit.");
    }
}
