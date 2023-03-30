import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MenuBar from './menuBar/menuBar';
import JobsPage from './pages/jobs/jobs';
import UserProfile from './pages/userProfile/userProfile'

class RouterComponent extends Component {
  render() {
    return (
        <Router>
            <MenuBar />
            <Routes>
                <Route exact path="/" component={JobsPage} />
                <Route path="/userProfile" component={UserProfile} />
            </Routes>
        </Router>
    )
  }
}

export default RouterComponent;