package com.badbones69.common.builder.commands.args.types;

import com.badbones69.common.builder.commands.args.ArgumentType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DoubleArgument extends ArgumentType {

    private final double cap;

    public DoubleArgument(double cap) {
        if (cap <= 0.0) {
            this.cap = 100.0;
            return;
        }

        this.cap = cap;
    }

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        for (double value = 0.1; value <= this.cap; value += 0.1) {
            String formattedNumber = decimalFormat.format(value);
            numbers.add(formattedNumber);
        }

        return numbers;
    }
}