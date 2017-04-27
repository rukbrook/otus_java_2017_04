MEMORY="-Xms2G -Xmx2G -XX:MaxMetaspaceSize=256m"

GC1="-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=10 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark -XX:+UseParNewGC"
GC2="-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+UseAdaptiveSizePolicy -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=10"
GC3="-XX:+UseSerialGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=10"
GC4="-XX:+UseG1GC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=10"

java $MEMORY $GC1 -jar target/HW4-rukbrook.jar #ParNew
java $MEMORY $GC2 -jar target/HW4-rukbrook.jar #ParallelOld
java $MEMORY $GC3 -jar target/HW4-rukbrook.jar #Serial
java $MEMORY $GC4 -jar target/HW4-rukbrook.jar #G1