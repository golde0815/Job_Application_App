import './App.css';
import {useState} from 'react';
import RouterComponent from './components/router';
import { DefaultCompanyProvider } from './DefaultCompanyContext';

function App() {
  return (
    <div className="App">
      <DefaultCompanyProvider>
        <RouterComponent></RouterComponent>
      </DefaultCompanyProvider>
    </div>
  );
}

export default App;
