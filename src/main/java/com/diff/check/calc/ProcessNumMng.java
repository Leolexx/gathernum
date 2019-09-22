package com.diff.check.calc;

import com.diff.check.gathernum.Consts;

import java.util.Optional;

public interface ProcessNumMng {

    Optional<Integer> getMax();

    Optional<Integer> getMin();

    Optional<Double> getAverage();

    Consts.Result add(String str);
}
