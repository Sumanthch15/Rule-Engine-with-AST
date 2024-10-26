package com.rule.rule_engine_AST.services;

import java.util.Map;

public abstract class Node {
    public abstract boolean evaluate(Map<String, Object> attributes);
}