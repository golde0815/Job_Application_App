import React, { Component }  from "react";
import { Link } from "react-router-dom";
import "./menuBar.css";
import ViewToggle from "./viewToggle";

class MenuBar extends Component {
  state = {
    isUserView: true 
  }

  handleViewToggle = () => {
    this.setState(prevState => ({
      isUserView: !prevState.isUserView 
    }));
  };

  render() {
    const isUserView = this.state.isUserView

      return (
      <header className="menubar-container">
        <div className="menubar-left">
          <h1 className="menubar-title">MyJobPortal</h1>
          <ViewToggle 
            isUserView={isUserView}
            handleViewToggle={this.handleViewToggle}
          />
        </div>
        <nav>
          <ul className="nav-list">
            {isUserView && 
              <li className="nav-item">
                <Link to="/">Jobs</Link>
              </li>
            }
            {isUserView && 
              <li className="nav-item">
                <Link to="/userProfile">UserProfile</Link>
              </li>
            }
            {!isUserView &&
              <li className="nav-item">
                <Link to="/companyProfile">CompanyProfile</Link>
              </li>
            }
            {isUserView && 
              <li className="nav-item">
                <a href="/companies">Companies</a>
              </li>
            }
          </ul>
        </nav>
    </header>
  );
  }
};

export default MenuBar;