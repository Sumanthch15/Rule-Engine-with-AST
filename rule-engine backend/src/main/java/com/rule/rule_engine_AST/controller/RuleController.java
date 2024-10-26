package com.rule.rule_engine_AST.controller;

import com.rule.rule_engine_AST.entity.Rule;
import com.rule.rule_engine_AST.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/rules")
    public ResponseEntity<Rule> createRule(@RequestBody Map<String, String> payload) {
        String ruleString = payload.get("rule");
        Rule rule = ruleService.createRule(ruleString);
        return ResponseEntity.ok(rule);
    }

    @GetMapping("/rules")
    public ResponseEntity<List<Rule>> getAllRules() {
        List<Rule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }

//    @PostMapping("/test-rule")
//    public ResponseEntity<Map<String, Boolean>> testRule(@RequestBody Map<String, Object> payload) {
//        Map<String, Object> attributes = (Map<String, Object>) payload.get("attributes");
//        String ruleString = (String) payload.get("ruleString");
//
//        // Retrieve all rules from the service
//        List<Rule> rules = ruleService.getAllRules();
//
//        // Initialize result to false
//        boolean result = false;
//
//        // Iterate over each rule and check if it matches the provided ruleString and attributes
//        for (Rule rule : rules) {
//            String existingRuleString = rule.getRuleString(); // Assuming Rule has a getRuleString() method
//
//            // Check if the rule matches the provided ruleString and attributes
//            if (ruleService.evaluateRule(attributes, existingRuleString) && existingRuleString.equals(ruleString)) {
//                result = true;
//                break;  // Exit loop if a match is found
//            }
//        }
//
//        return ResponseEntity.ok(Map.of("result", result));
//    }
@PostMapping("/test-rule")
public ResponseEntity<Map<String, Boolean>> testRule(@RequestBody Map<String, Object> payload) {
    String ruleString = (String) payload.get("ruleString");

    List<Rule> rules = ruleService.getAllRules();

    // Initialize result to false
    boolean result = false;

    // Iterate over each rule and check if it matches the provided ruleString and attributes
    for (Rule rule : rules) {
        if (rule.getRuleString().equals(ruleString)) {
            result = true;
            break;  // Exit loop if a match is found
        }
    }

    return ResponseEntity.ok(Map.of("result", result));
}


}