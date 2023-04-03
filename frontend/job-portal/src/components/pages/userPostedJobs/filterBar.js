import React, { Component } from 'react';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import './filterBar.css';

class FilterBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      company: [],
      postedAfter: '',
      location: [],
      salary: '', 

      companyOptions: [],
      locationOptions: [],
    };
  }

  componentDidMount() {
    // TODO: GET all companies and all locations and make set these as the values in companyOptions and locationOptions, 
    // remove the code below after done

    this.setState({
        companyOptions: [
            { value: 'company1', label: 'Company 1' },
            { value: 'company2', label: 'Company 2' },
            { value: 'company3', label: 'Company 3' }
            // { value: 'companyID', label: 'companyName' }
          ], 
        locationOptions: [
            { value: 'location1', label: 'Location 1' },
            { value: 'location2', label: 'Location 2' },
            { value: 'location3', label: 'Location 3' }
          ]
    })
  
  }

  handleCompanyChange = selectedOption => {
    this.setState({ company: selectedOption.value });
  };

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
    const { company, postedAfter, location, salary } = this.state
    this.props.handleApplyFilters(company, postedAfter, location, salary)
  };

  render() {

    const {companyOptions, locationOptions} = this.state
    
    return (
      <div className="filter-bar">
        <label htmlFor="company">Company:</label>
        <Dropdown id="company" options={companyOptions} value={this.state.company} onChange={this.handleCompanyChange} placeholder="Select an option" />
        <label htmlFor="posted-after">Posted After:</label>
        <input id="posted-after" type="date" value={this.state.postedAfter} onChange={this.handlePostedAfterChange} />
        <label htmlFor="location">Location:</label>
        <Dropdown id="location" options={locationOptions} value={this.state.location} onChange={this.handleLocationChange} placeholder="Select an option" />
        <label htmlFor="salary">Minimum Salary:</label>
        <input id="salary" type="number" value={this.state.salary} onChange={this.handleSalaryChange} />
        <button id="filter-apply-button" onClick={this.handleApplyFilters}>Apply Filters</button>
      </div>
    );
  }
}

export default FilterBar;
