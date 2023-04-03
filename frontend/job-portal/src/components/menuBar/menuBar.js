import React, { Component }  from "react";
import { Link } from "react-router-dom";
import "./menuBar.css";
import ViewToggle from "./viewToggle";

class MenuBar extends Component {
  state = {
    view: "USER" 
  }

  handleViewToggle = (view) => {
    this.setState(() => ({
      view: view
    }));
  };

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
          {view === "ADMIN" && 
            <li className="nav-item">
              <Link to="/admin">Admin</Link>
            </li>
          }
        </ul>
      </nav>
    </header>
    );
  }
};

export default MenuBar;