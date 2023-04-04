import React, { Component } from 'react';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import './filterBar.css';

class FilterBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      postedAfter: '',
      location: null,
      salary: null, 
      locationOptions: [],
    };
  }

  componentDidMount() {
    fetch('http://localhost:8080/locations').then(response => response.json()).then(locationOptions => {
        this.setState({
          locationOptions: locationOptions
        })
    }).catch(error => console.error(error))
  }

  handlePostedAfterChange = event => {
    this.setState({ postedAfter: event.target.value });

  };

  handleLocationChange = selectedOption => {
    this.setState({ location: selectedOption.value });
  };

  handleSalaryChange = event => {
    this.setState({ salary: event.target.value });
  };

  handleApplyFilters = () => {
    const { postedAfter, location, salary } = this.state

    if (!(postedAfter !== '' && location === null && salary === null) &&
    !(postedAfter === '' && location !== null && salary === null) &&
    !(postedAfter === '' && location === null && salary !== null) &&
    !(postedAfter === '' && location === null && salary === null)) {
      window.alert("Please only select on filter at a time.")
      return
    }

    this.props.handleApplyFilters(postedAfter, location, salary)
  };

  handleClearFilters = () => {
    this.setState({
      postedAfter: '',
      location: null, 
      salary: null
    })

    document.getElementById('salary').value = null
    document.getElementById('posted-after').value = ''

    this.props.handleClearFilters()
  }

  render() {

    const {locationOptions} = this.state
    
    return (
      <div className="filter-bar">
        <label htmlFor="posted-after">Posted After:</label>
        <input id="posted-after" type="date" value={this.state.postedAfter} onChange={this.handlePostedAfterChange} />
        <label htmlFor="location">Location:</label>
        <Dropdown id="location" options={locationOptions} value={this.state.location} onChange={this.handleLocationChange} placeholder="Select an option" />
        <label htmlFor="salary">Minimum Salary:</label>
        <input id="salary" type="number" value={this.state.salary} onChange={this.handleSalaryChange} />
        <button id="filter-apply-button" onClick={this.handleApplyFilters}>Apply Filters</button>
        <button id="filter-apply-button" onClick={this.handleClearFilters}>Clear Filters</button>
      </div>
    );
  }
}

export default FilterBar;
