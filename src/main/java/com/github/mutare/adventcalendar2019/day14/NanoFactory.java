package com.github.mutare.adventcalendar2019.day14;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class NanoFactory {

    private final Map<String, Reaction> reactions;

    public NanoFactory(Map<String, Reaction> reactions) {
        this.reactions = reactions;
    }

    public int findOreQuantity() {
        Reaction fuel = reactions.get("FUEL");

        List<Factor> factors = new ArrayList<>(fuel.factors);
        while(reduceFactors(factors, reactions) == 0) {
            factors = mergeFactors(factors);
        }

        return factors.stream().map(this::toOre).reduce(Integer::sum).orElseThrow();
    }

    private int toOre(Factor factor) {
        return factor.type.equals("ORE") ?
                factor.quantity :
                ((int) (reactions.get(factor.type).factors.get(0).quantity * Math.ceil((double) factor.quantity / reactions.get(factor.type).output.quantity)));
    }

    private List<Factor> mergeFactors(List<Factor> factors) {
        return factors.stream()
                .filter(factor -> factor.quantity != 0)
                .collect(Collectors.toMap(factor -> factor.type, factor -> factor.quantity, Integer::sum))
                .entrySet()
                .stream()
                .map(stringIntegerEntry -> new Factor(stringIntegerEntry.getKey(), stringIntegerEntry.getValue()))
                .collect(Collectors.toList());
    }

    private int reduceFactors(List<Factor> factors, Map<String, Reaction> reactions) {
        for (Factor factor : factors) {
            if (factor.type.equals("ORE")) {
                continue;
            }
            Reaction reaction = reactions.get(factor.type);
            if (factor.quantity > 0) {
                factor.quantity -= reaction.output.quantity;
                factors.addAll(reaction.factors);
                return 0;
            }
        }
        return 1;
    }

    static class Reaction {
        Reaction(Factor output, Factor... factors) {
            this.output = output;
            this.factors = asList(factors);
            this.isOreReaction = this.factors.stream().allMatch(factor -> factor.type.equals("ORE"));
        }
        List<Factor> factors;
        Factor output;
        boolean isOreReaction;
    }

    static class Factor {
        Factor(String type, int quantity) {
            this.type = type;
            this.quantity = quantity;
        }

        String type;
        int quantity;
    }

}
