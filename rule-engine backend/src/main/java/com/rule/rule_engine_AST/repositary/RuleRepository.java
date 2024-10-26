package com.rule.rule_engine_AST.repositary;

import com.rule.rule_engine_AST.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
