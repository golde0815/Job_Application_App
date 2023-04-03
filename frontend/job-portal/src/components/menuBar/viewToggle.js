import React, { Component } from "react";
import './viewToggle.css'

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
        <button id="USER" className={this.state.selected === "USER" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>USER</button>
        <button id="COMPANY" className={this.state.selected === "COMPANY" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>COMPANY</button>
        <button id="ADMIN" className={this.state.selected === "ADMIN" ? "view-toggle on": "view-toggle"} onClick={this.handleToggle}>ADMIN</button>
      </div>
    );

  }
};

export default ViewToggle;