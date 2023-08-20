package com.badbones69.common.builder.commands.args.types;

import com.badbones69.common.builder.commands.args.ArgumentType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FloatArgument extends ArgumentType {

    private final float cap;

    public FloatArgument(float cap) {
        if (cap <= 0f) {
            this.cap = 100f;
            return;
        }

        this.cap = cap;
    }

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("0.0f");

        for (float value = 0.1f; value <= this.cap; value += 0.1f) {
            String formattedValue = decimalFormat.format(value);
            numbers.add(formattedValue);
        }

        return numbers;
    }
}