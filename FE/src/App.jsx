import React from 'react';
import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './pages/layout';
import EmployeeList from './pages/employee/employeeList';

class App extends React.Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
        <Routes>
        <Route path="/" element={<Layout />}>
            <Route path="employees" element={<EmployeeList />} />
        </Route>
      </Routes>
        </header>
      </div>
    );
  }
}

export default App;
