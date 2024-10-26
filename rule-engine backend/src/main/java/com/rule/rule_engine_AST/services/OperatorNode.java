package com.rule.rule_engine_AST.services;


import com.rule.rule_engine_AST.services.Node;

import java.util.Map;

public class OperatorNode extends Node {
    private String operator;
    private Node left;
    private Node right;

    public OperatorNode(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean evaluate(Map<String, Object> attributes) {
        boolean leftResult = left.evaluate(attributes);
        boolean rightResult = right.evaluate(attributes);
        switch (operator) {
            case "AND":
                return leftResult && rightResult;
            case "OR":
                return leftResult || rightResult;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
