package com.gabrielaraujo.iaax;

import com.gabrielaraujo.iaax.tags.Infrastructure;
import com.gabrielaraujo.iaax.tags.Variable;
import lombok.Getter;

import java.util.Dictionary;
import java.util.Hashtable;

@Getter
public class IaaxVariables {
    private final Dictionary<String, String> variables = new Hashtable<>();

    public static IaaxVariables INSTANCE = new IaaxVariables();

    public void loadVariables(Infrastructure<?> infrastructure) {
        var environment = infrastructure.getEnvironment();

        if (infrastructure.getEnvironment() != null) {
            for (Variable variable : environment.getVariables()) {
                if (
                        (variable.getKey() != null && !variable.getKey().isEmpty()) &&
                                (variable.getValue() != null && !variable.getValue().isEmpty())
                ) {
                    variables.put(variable.getKey(), variable.getValue());
                }
            }
        }
    }

    public Boolean hasVariables() {
        return !variables.isEmpty();
    }

    public String getVariable(String key) {
        return variables.get(key);
    }
}
