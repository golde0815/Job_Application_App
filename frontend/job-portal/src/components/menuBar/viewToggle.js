import React, { Component } from "react";
import './viewToggle.css'

class ViewToggle extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
      selected: props.isUserView
    }
    this.handleToggle = this.handleToggle.bind(this);
  }

  handleToggle = () => {
    this.setState(prevState => ({
      selected: !prevState.selected
    }), () => {
      this.props.handleViewToggle(this.state.selected)
    });
  };

  render() {
    return (
      <button onClick={this.handleToggle} className={this.state.selected ? "view-toggle on" : "view-toggle off"}>
        {this.state.selected ? "USER" : "COMPANY"}
      </button>
    );

  }
};

export default ViewToggle;