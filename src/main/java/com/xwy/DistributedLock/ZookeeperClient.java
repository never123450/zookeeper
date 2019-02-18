package com.xwy.DistributedLock;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperClient {
	
	//这里填你自己的机器ip地址和端口号
    private final static String CONNECTSTRING="127.0.0.1";
    
    private static int sessionTimeout = 500;
    
    public static ZooKeeper getInstance() throws Exception {
    		final CountDownLatch conectStatus = new CountDownLatch(1);
    		ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeout, new Watcher() {
				
				public void process(WatchedEvent event) {
					if (event.getState() == Event.KeeperState.SyncConnected) {
						conectStatus.countDown();
					}
				}
			});
    		conectStatus.await();
    		return zooKeeper;
    }
    
    public static int getSessionTimeout() {
		return sessionTimeout;
	}

}
