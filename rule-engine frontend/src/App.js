import React, { useState, useEffect } from 'react';
import RuleForm from './components/RuleForm';
import RuleList from './components/RuleList';
import RuleTester from './components/RuleTester';
import './App.css';
import axios from 'axios';

const App = () => {
    const [rules, setRules] = useState([]);
    const [result, setResult] = useState(null);

    useEffect(() => {
        fetchRules();
    }, []);

    const fetchRules = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/rules');
            setRules(response.data);
        } catch (error) {
            console.error('Error fetching rules:', error);
        }
    };

    const handleCreateRule = async (ruleString) => {
        try {
            await axios.post('http://localhost:8080/api/rules', { rule: ruleString });
            fetchRules();
        } catch (error) {
            console.error('Error creating rule:', error);
        }
    };

    const handleTestRule = async (attributes, ruleString) => {
        try {
            const response = await axios.post('http://localhost:8080/api/test-rule', {
                attributes,
                ruleString,
            });
            setResult(response.data.result);
        } catch (error) {
            console.error('Error testing rule:', error);
        }
    };

    return (
        <div className="container">
            <h1>Rule Engine UI</h1>
            <RuleForm onCreateRule={handleCreateRule} />
            <RuleList rules={rules} />
            <RuleTester onTestRule={handleTestRule} result={result} />
        </div>
    );
};

export default App;
