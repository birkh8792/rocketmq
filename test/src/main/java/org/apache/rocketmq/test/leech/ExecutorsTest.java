package org.apache.rocketmq.test.leech;

import org.apache.rocketmq.common.ThreadFactoryImpl;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest {

    private static final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl(
            "LeechScheduledThread"));

    //https://juejin.cn/post/6914204878621376525
    //https://juejin.cn/post/6844904199667318798
    //容器都是采用 Host 模式，所以容器的网络跟宿主机是完全一致的
    public static void main(String[] args) {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("scan invoke..." + new Date());
            }
        }, 5, 10, TimeUnit.SECONDS);

        // rocketmq存储
        //https://blog.51cto.com/AntzUhl/2864095
        //而ConsumeQueue的存储单元根据我们上面那副图的计算，可以得到每个消息在ConsumeQueue中的大小是20Byte。
        /**
         * CommitLog Offset : 8 Byte
         * Size : 4 Byte
         * Message Tag Hashcode : 8 Byte
         */
        //MappedFile它将内存和文件创建了映射关系 顺序写，随机读，保证读写都是高效的O(1)
    }

}
