Microbenchmark - Logging frameworks
-----------------------------------

This is a microbenchmark built using the JMH microbenchmark toool (http://tutorials.jenkov.com/java-performance/jmh.html)
The tested frameworks are:

 * Log4j1 - https://logging.apache.org/log4j/1.2/
 * Log4j2 - http://logging.apache.org/log4j/2.x/
 * Logback - http://logback.qos.ch/
 * JUL - https://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html

Here are the results from the tests run on my machine

```
MacBook Pro (Retina, 15-inch, Late 2013)
Processor: 2 GHz Intel Core i7, 4 cores
Memory: 8 GB 1600 MHz DDR3
SSD Speed: 640 MB/s
```

### 1 thread
```
Benchmark                 Mode  Cnt       Score       Error  Units
LoggerBenchmark.jul      thrpt   10   48626.845 ±   992.677  ops/s
LoggerBenchmark.log4j1   thrpt   10   36700.909 ±   541.328  ops/s
LoggerBenchmark.log4j2   thrpt   10  374227.596 ±  6620.071  ops/s
LoggerBenchmark.logback  thrpt   10  395833.449 ± 10418.417  ops/s
```

### 4 threads
```
Benchmark                 Mode  Cnt       Score      Error  Units
LoggerBenchmark.jul      thrpt   10   40079.589 ± 1960.554  ops/s
LoggerBenchmark.log4j1   thrpt   10   34820.625 ± 3600.144  ops/s
LoggerBenchmark.log4j2   thrpt   10  247208.514 ± 2171.982  ops/s
LoggerBenchmark.logback  thrpt   10  127680.978 ± 1913.399  ops/s```
```

### 16 threads
```
Benchmark                 Mode  Cnt       Score        Error  Units
LoggerBenchmark.jul      thrpt   10  127112.190 ± 108074.958  ops/s
LoggerBenchmark.log4j1   thrpt   10  190675.226 ±  86994.185  ops/s
LoggerBenchmark.log4j2   thrpt   10  234393.138 ±   1441.052  ops/s
LoggerBenchmark.logback  thrpt   10  123712.425 ±   1463.203  ops/s
```
