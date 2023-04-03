import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MenuBar from './menuBar/menuBar';
import Jobs from './pages/jobs/jobs';
import UserProfile from './pages/userProfile/userProfile'
import CompanyProfile from './pages/companyProfile/companyProfile';
import CompanyPostedJobs from './pages/companyPostedJobs/companyPostedJobs';
import PostNewJob from './pages/postNewJob/postNewJob';
import UserPostedJobs from './pages/userPostedJobs/userPostedJobs';
import Companies from './pages/companies/companies';

class RouterComponent extends Component {
  render() {
    return (
        <Router>
            <MenuBar />
            <Routes>
                <Route exact path="/" element={<Jobs />} />
                <Route path="/userProfile" element={<UserProfile />} />
                <Route path="/companyProfile" element={<CompanyProfile />} />
                <Route path="/companyPostedJobs" element={<CompanyPostedJobs />} />
                <Route path="/postNewJob" element={<PostNewJob />} />
                <Route path="/userPostedJobs" element={<UserPostedJobs />} />
                <Route path="/companies" element={<Companies />} />
            </Routes>
        </Router>
    )
  }
}

export default RouterComponent;