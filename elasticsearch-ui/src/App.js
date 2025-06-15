import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [autoQuery, setAutoQuery] = useState('');
  const [autoResults, setAutoResults] = useState([]);

  const [fuzzyQuery, setFuzzyQuery] = useState('');
  const [fuzzyResults, setFuzzyResults] = useState([]);

  // Autocomplete request
  useEffect(() => {
    if (autoQuery.length > 1) {
      fetch(`http://localhost:8081/products/search/autocomplete?prefix=${autoQuery}`)
        .then(res => res.json())
        .then(data => setAutoResults(data))
        .catch(err => console.error('Autocomplete Error:', err));
    } else {
      setAutoResults([]);
    }
  }, [autoQuery]);

  // Fuzzy search request
  useEffect(() => {
    if (fuzzyQuery.length > 1) {
      fetch(`http://localhost:8081/products/search/fuzzy?keyword=${fuzzyQuery}`)
        .then(res => res.json())
        .then(data => setFuzzyResults(data))
        .catch(err => console.error('Fuzzy Search Error:', err));
    } else {
      setFuzzyResults([]);
    }
  }, [fuzzyQuery]);

  return (
    <div className="container">
      {/* Autocomplete */}
      <div className="box">
        <h2>🔎 Autocomplete Search</h2>
        <input
          type="text"
          placeholder="Enter product name..."
          value={autoQuery}
          onChange={(e) => setAutoQuery(e.target.value)}
        />
        <ul>
          {autoResults.length === 0 && autoQuery.length > 1 && <li>No results found.</li>}
          {autoResults.map((item) => (
            <li key={item.id}>
              <strong>{item.name}</strong> – {item.description}
            </li>
          ))}
        </ul>
      </div>

      {/* Fuzzy */}
      <div className="box">
        <h2>🧠 Fuzzy Search</h2>
        <input
          type="text"
          placeholder="Enter product name..."
          value={fuzzyQuery}
          onChange={(e) => setFuzzyQuery(e.target.value)}
        />
        <ul>
          {fuzzyResults.length === 0 && fuzzyQuery.length > 1 && <li>No results found.</li>}
          {fuzzyResults.map((item) => (
            <li key={item.id}>
              <strong>{item.name}</strong> – {item.description}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;
