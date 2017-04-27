package ru.otus.hw4;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rukbr on 26.04.2017.
 */
public class measureGC {
    private List<Runnable> registration = new ArrayList<>();

    private static boolean isListenerInstalled = false;

    private measureGC() {
        installGCMonitoring();
    }

    static void collect() {
        measureGC bgc = new measureGC();
    }

    public void installGCMonitoring() {
        if (isListenerInstalled) {
            return;
        }
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {

                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {

                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    StatisticGC statistic = (StatisticGC) handback;
                    statistic.setName(info.getGcName());
                    statistic.updateNumberOfCalls();
                    long duration = info.getGcInfo().getDuration();
                    statistic.updateTotalDuration(duration);
                    statistic.log();
                }
            };

            emitter.addNotificationListener(listener, null, new StatisticGC());

        }
        isListenerInstalled = true;
    }
}
