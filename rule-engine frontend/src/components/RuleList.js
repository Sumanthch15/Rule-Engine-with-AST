import React from 'react';

const RuleList = ({ rules }) => {
    return (
        <div>
            <h3>Existing Rules</h3>
            <div>
                {rules.length > 0 ? (
                    rules.map((rule, index) => (
                        <div key={index} className="rule-item">
                            Rule: {rule.ruleString}
                        </div>
                    ))
                ) : (
                    <p>No rules available</p>
                )}
            </div>
        </div>
    );
};

export default RuleList;
