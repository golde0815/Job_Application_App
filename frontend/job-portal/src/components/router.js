import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MenuBar from './menuBar/menuBar';
import Jobs from './pages/jobs/jobs';
import UserProfile from './pages/userProfile/userProfile'
import CompanyProfile from './pages/companyProfile/companyProfile';
import PostedJobs from './pages/postedJobs/postedJobs';

class RouterComponent extends Component {
  render() {
    return (
        <Router>
            <MenuBar />
            <Routes>
                <Route exact path="/" element={<Jobs />} />
                <Route path="/userProfile" element={<UserProfile />} />
                <Route path="/companyProfile" element={<CompanyProfile />} />
                <Route path="/postedJobs" element={<PostedJobs />} />
            </Routes>
        </Router>
    )
  }
}

export default RouterComponent;