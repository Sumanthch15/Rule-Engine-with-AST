import React, { useState } from 'react';

const RuleForm = ({ onCreateRule }) => {
    const [rule, setRule] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (rule) {
            onCreateRule(rule);
            setRule('');
        }
    };

    return (
        <div>
            <h3>Create a New Rule</h3>
            <form onSubmit={handleSubmit}>
                <textarea
                    value={rule}
                    onChange={(e) => setRule(e.target.value)}
                    placeholder="Enter rule (e.g., age > 30 AND department = 'Sales')"
                    rows="3"
                />
                <button type="submit">Create Rule</button>
            </form>
        </div>
    );
};

export default RuleForm;
