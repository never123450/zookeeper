package com.xwy.DistributedLock;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;

public class LockWatcher implements Watcher{

	private CountDownLatch latch;
	
	public LockWatcher(CountDownLatch countDownLatch) {
		this.latch = countDownLatch;
	}

	public void process(WatchedEvent event) {
		if (event.getType() == EventType.NodeDeleted) {
			latch.countDown();
		}
	}
	
	
	

}
