import React, { Component, useContext }  from "react";
import { Link } from "react-router-dom";
import "./menuBar.css";
import ViewToggle from "./viewToggle";
import Dropdown from "react-dropdown";
import { DefaultCompanyContext } from '../../DefaultCompanyContext';

class MenuBar extends Component {
  static contextType = DefaultCompanyContext;

  state = {
    view: "USER",
    selectedCompany: null,
    allCompanies: []
  }

  componentDidMount() {
    fetch('http://localhost:8080/companies').then(response => {
      if (!response.ok) {
          return Promise.reject(response)
      } else {
          return response.json()
      }
    }).then(companies => {
      const allCompanies = []
      for (let i = 0; i < companies.length; i++) {
        allCompanies.push({label: companies[i].name, value: companies[i].companyId})
      }
      this.setState({
        allCompanies: allCompanies,
        selectedCompany: allCompanies.find(obj => obj.value === this.context.defaultCompanyId)
      })
    }).catch(errorResponse => {
      errorResponse.json().then(error => {
          console.error('Error: ', error)
          window.alert(`An error ${error.error} occurred with message ${error.message}`)
      })
    })
  }
  

  handleViewToggle = (view) => {
    this.setState(() => ({
      view: view
    }));
  };

  handleCompanyChange = (selected) => {
    const [defaultCompanyId, setdefaultCompanyId] = this.context;

    this.setState({
      selectedCompany: selected,
    }, () => {
      setdefaultCompanyId(selected.value);
    })

  }

  render() {
    const { view } = this.state
  
    return (
    <header className="menubar-container">
      <div className="menubar-left">
        <h1 className="menubar-title">MyJobPortal</h1>
        <ViewToggle 
          handleViewToggle={this.handleViewToggle}
        />
      </div>
      <nav>
        <ul className="nav-list">
          {view === "USER" && 
            <li className="nav-item">
              <Link to="/userPostedJobs">Jobs</Link>
            </li>
          }
          {view === "COMPANY" &&
            <Dropdown options={this.state.allCompanies} value={this.state.selectedCompany} onChange={this.handleCompanyChange} placeholder="Select a company to use for the view" />
          }
          {view === "COMPANY" &&
            <li className="nav-item">
              <Link to="/companyProfile">CompanyProfile</Link>
            </li>
          }
          {view === "COMPANY" &&
            <li className="nav-item">
              <Link to="/postNewJob">Post a Job</Link>
            </li>
          }
          {view === "USER" && 
            <li className="nav-item">
              <Link to="/companies">Companies</Link>
            </li>
          }
        </ul>
      </nav>
    </header>
    );
  }
};

export default MenuBar;