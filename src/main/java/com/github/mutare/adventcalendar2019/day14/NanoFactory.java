package com.github.mutare.adventcalendar2019.day14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class NanoFactory {

    private final Map<String, Reaction> reactions;
    private List<Factor> wastes;

    public NanoFactory(Map<String, Reaction> reactions) {
        this.reactions = reactions;
    }

    public long findOreQuantity() {
        return findOreQuantityFor(reactions.get("FUEL").output);
    }

    public long findOreQuantityFor(Factor f) {
        List<Factor> factors = new ArrayList<>(Collections.singletonList(new Factor(f)));
        while (reduceFactors(factors) == 0) {
            factors = mergeFactors(factors);
        }
        wastes = factors.stream().filter(factor -> !factor.type.equals("ORE")).collect(Collectors.toList());
        return factors.stream().map(this::toOre).reduce(Double::sum).orElseThrow().longValue();
    }

    private double toOre(Factor factor) {
        return factor.type.equals("ORE") ?
                factor.quantity :
                ((int) (reactions.get(factor.type).factors.get(0).quantity * Math.ceil((double) factor.quantity / reactions.get(factor.type).output.quantity)));
    }

    private List<Factor> mergeFactors(List<Factor> factors) {
        return factors.stream()
                .filter(factor -> factor.quantity != 0)
                .collect(Collectors.toMap(factor -> factor.type, factor -> factor.quantity, Long::sum))
                .entrySet()
                .stream()
                .map(stringLongEntry -> new Factor(stringLongEntry.getKey(), stringLongEntry.getValue()))
                .collect(Collectors.toList());
    }

    private int reduceFactors(List<Factor> factors) {
        for (Factor factor : factors) {
            if (factor.type.equals("ORE")) {
                continue;
            }
            Reaction reaction = reactions.get(factor.type);
            if (factor.quantity > 0) {
                long m = (long) Math.ceil((double) factor.quantity / reaction.output.quantity);
                factor.quantity -= m * reaction.output.quantity;
                for (Factor f : reaction.factors) {
                    factors.add(new Factor(f.type, f.quantity * m));
                }
                return 0;
            }
        }
        return 1;
    }

    public long findMaxFuel(long oreLimit) {
        long orePerFuel = findOreQuantity();
        long min = oreLimit / orePerFuel;
        long max = min * 3;

        return searchOreQuantityFor("FUEL", min, max, oreLimit);
    }

    private long searchOreQuantityFor(String type, long min, long max, long oreLimit) {
        long avg = (long)Math.ceil((max + min) / 2);
        long fuel1 = findOreQuantityFor(new Factor(type, avg));
        if (max - min < 2) {
            return avg;
        }

        return fuel1 > oreLimit ?
                searchOreQuantityFor(type, min, avg, oreLimit) :
                searchOreQuantityFor(type, avg, max, oreLimit);
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
        Factor(String type, long quantity) {
            this.type = type;
            this.quantity = quantity;
        }

        String type;
        long quantity;

        public Factor(Factor f) {
            this.quantity = f.quantity;
            this.type = f.type;
        }
    }

}
