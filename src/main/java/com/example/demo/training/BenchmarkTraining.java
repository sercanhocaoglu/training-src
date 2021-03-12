package com.example.demo.training;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class BenchmarkTraining {

    public int add1(int x, int y) {
        return x + y;
    }
}
