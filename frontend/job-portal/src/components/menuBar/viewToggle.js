import React, { Component } from "react";
import './viewToggle.css'
import { Link } from "react-router-dom";

class ViewToggle extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
      selected: "USER"
    }
    this.handleToggle = this.handleToggle.bind(this);
  }

  handleToggle = (event) => {
    this.setState(() => ({
      selected: event.target.id
    }), () => {
      this.props.handleViewToggle(this.state.selected)
    });
  };

  render() {
    return (
      <div>
        <Link to="/userPostedJobs">
          <button id="USER" className={this.state.selected === "USER" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>USER</button>
        </Link>
        <Link to="/companyProfile">
          <button id="COMPANY" className={this.state.selected === "COMPANY" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>COMPANY</button>
        </Link>
        <Link to="/admin">
          <button id="ADMIN" className={this.state.selected === "ADMIN" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>ADMIN</button>
        </Link>
      </div>
    );

  }
};

export default ViewToggle;