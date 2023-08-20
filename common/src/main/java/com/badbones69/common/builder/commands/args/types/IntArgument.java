package com.badbones69.common.builder.commands.args.types;

import com.badbones69.common.builder.commands.args.ArgumentType;
import java.util.ArrayList;
import java.util.List;

public class IntArgument extends ArgumentType {

    private final int cap;

    public IntArgument(int cap) {
        if (cap <= 0) {
            this.cap = 100;
            return;
        }

        this.cap = cap;
    }

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        for (int value = 1; value <= this.cap; value += 1) numbers.add(String.valueOf(value));

        return numbers;
    }
}