import React, { useState } from 'react';

const RuleTester = ({ result, setResult }) => {
    const [ruleString, setRuleString] = useState('');
    const [message, setMessage] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/test-rule', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ ruleString }),
            });

            console.log("API Response:", response);
                
            const data = await response.json();            
            setMessage(data.result ? 'Matched' : 'Not Matched');
            setRuleString('');
            
        } catch (error) {
            alert('Error in fetching');
            setMessage('API ERROR');
        }
    };

    return (
        <div>
            <h3>Test Rule</h3>
            <form onSubmit={handleSubmit}>
                <textarea
                    value={ruleString}
                    onChange={(e) => setRuleString(e.target.value)}
                    placeholder="Enter rule string"
                    rows="2"
                />
                <button type="submit">Test Rule</button>
            </form>
            <div>
                <h4>Result: {message}</h4>
            </div>
        </div>
    );
};

export default RuleTester;
