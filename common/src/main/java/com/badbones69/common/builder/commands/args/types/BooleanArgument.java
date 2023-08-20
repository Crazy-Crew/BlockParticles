package com.badbones69.common.builder.commands.args.types;

import com.badbones69.common.builder.commands.args.ArgumentType;
import java.util.List;

public class BooleanArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        return List.of("true", "false");
    }
}