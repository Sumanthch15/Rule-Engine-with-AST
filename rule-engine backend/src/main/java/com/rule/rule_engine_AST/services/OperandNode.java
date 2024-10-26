package com.rule.rule_engine_AST.services;


import com.rule.rule_engine_AST.services.Node;

import java.util.Map;

public class OperandNode extends Node {
    private String attribute;
    private String comparator;
    private Object value;

    public OperandNode(String attribute, String comparator, Object value) {
        this.attribute = attribute;
        this.comparator = comparator;
        this.value = value;
    }

    @Override
    public boolean evaluate(Map<String, Object> attributes) {
        Object attributeValue = attributes.get(attribute);

        if (attributeValue == null) {
            return false;
        }

        switch (comparator) {
            case ">":
                return ((Comparable) attributeValue).compareTo(value) > 0;
            case "<":
                return ((Comparable) attributeValue).compareTo(value) < 0;
            case "=":
                return attributeValue.equals(value);
            default:
                throw new IllegalArgumentException("Unknown comparator: " + comparator);
        }
    }
}
