package org.apache.rocketmq.test.leech;

import org.apache.rocketmq.common.constant.LoggerName;
import org.apache.rocketmq.logging.InternalLogger;
import org.apache.rocketmq.logging.InternalLoggerFactory;

public class LeeMsgService extends LeeThread{

    private static final InternalLogger log = InternalLoggerFactory.getLogger(LoggerName.STORE_LOGGER_NAME);

    @Override
    public String getServiceName() {
        return LeeMsgService.class.getSimpleName();
    }

    private void doReput(){
        System.out.println("doReput invoke...");
    }

    @Override
    public void run() {
        LeeMsgService.log.info(this.getServiceName() + " service started");

        while (!this.isStopped()) {
            try {
                Thread.sleep(1);
                this.doReput();
            } catch (Exception e) {
                LeeMsgService.log.warn(this.getServiceName() + " service has exception. ", e);
            }
        }

        LeeMsgService.log.info(this.getServiceName() + " service end");
    }
}
