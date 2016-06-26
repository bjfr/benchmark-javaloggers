/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package se.cuemind;

import static org.openjdk.jmh.annotations.Level.Trial;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.slf4j.LoggerFactory;

@State(Scope.Benchmark)
@Threads(1)
@Fork(value=1)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
public class LoggerBenchmark {
  
  org.apache.logging.log4j.Logger log4j2;
  org.apache.log4j.Logger log4j1;
  org.slf4j.Logger logback;

  private AtomicInteger counter;
  
  @Setup(Trial)
  public void setUp() {
    log4j2 = LogManager.getLogger("se.cuemind.logbenchmark");
    log4j1 = org.apache.log4j.Logger.getLogger("se.cuemind.logbenchmark");
    logback = LoggerFactory.getLogger("se.cuemind.logbenchmark");
    counter = new AtomicInteger();
  }

  @TearDown(Trial)
  public void tearDown() {
    System.out.println("Logged rows: " + counter.get());
  }

  @Benchmark
  public void log4j1(Blackhole bh) {
    log4j1.debug("This is log message " + counter.get());
    counter.incrementAndGet();
  }

  @Benchmark()
  public void log4j2(Blackhole bh) {
    log4j2.fatal("This is log message " + counter.get());
    counter.incrementAndGet();
  }

  @Benchmark()
  public void logback(Blackhole bh) {
    logback.debug("This is log message " + counter.get());
    counter.incrementAndGet();
  }
}