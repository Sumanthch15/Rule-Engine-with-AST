package com.rule.rule_engine_AST.services;

import com.rule.rule_engine_AST.services.Node;
import com.rule.rule_engine_AST.services.OperandNode;
import com.rule.rule_engine_AST.services.OperatorNode;

import java.util.Stack;

public class RuleParser {

    public Node parse(String ruleString) {
        // Tokenize by AND/OR operators first, keeping operands as whole expressions
        String[] tokens = ruleString.split(" (?=AND|OR) | (?<=AND|OR) ");
        Stack<Node> stack = new Stack<>();

        for (String token : tokens) {
            token = token.trim();

            if (token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR")) {
                // It's an operator
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(new OperatorNode(token.toUpperCase(), left, right));
            } else {
                // Assume it's an operand (e.g., age > 35)
                String[] parts = token.split("(?<=[<>=])|(?=[<>=])");

                if (parts.length < 3) {
                    throw new IllegalArgumentException("Invalid rule format: " + token);
                }

                String attribute = parts[0];
                String comparator = parts[1];
                String value = parts[2].replace("'", "");
                Object parsedValue;

                try {
                    parsedValue = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    parsedValue = value;
                }

                stack.push(new OperandNode(attribute, comparator, parsedValue));
            }
        }


        return stack.pop();
    }

}