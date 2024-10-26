package com.rule.rule_engine_AST.services;

import com.rule.rule_engine_AST.entity.Rule;
import com.rule.rule_engine_AST.repositary.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    private RuleParser ruleParser = new RuleParser();

    public Rule createRule(String ruleString) {
        Rule rule = new Rule(ruleString);
        return ruleRepository.save(rule);
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public boolean evaluateRule(Map<String, Object> attributes, String ruleString) {
        Node ast = ruleParser.parse(ruleString);
        return ast.evaluate(attributes);
    }
}
