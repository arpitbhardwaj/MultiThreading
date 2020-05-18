package com.ab.multithread.deadlock;

import java.lang.management.ThreadInfo;
import java.util.Map;

/**
 * @author Arpit Bhardwaj
 */
public class DeadlockConsoleHandler implements DeadlockHandler {
    @Override
    public void handleDeadLock(ThreadInfo[] deadlockThreads) {
        if (deadlockThreads != null){
            System.out.println("Deadlock detected!!");
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();

            for (ThreadInfo threadInfo:
                 deadlockThreads) {
                if (threadInfo != null){
                    for (Thread t:
                         allStackTraces.keySet()) {
                        if (t.getId() == threadInfo.getThreadId()){
                            System.err.println(threadInfo.toString());
                        }
                        for (StackTraceElement traceElement:
                             t.getStackTrace()) {
                            System.err.println("\t" + traceElement.toString());
                        }
                    }
                }
            }
        }
    }
}
