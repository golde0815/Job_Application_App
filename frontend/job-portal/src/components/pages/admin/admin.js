import React, { Component } from "react";
import Multiselect from 'multiselect-react-dropdown';
import "./admin.css"
import Dropdown from 'react-dropdown';

// 5. Projection
class Admin extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
      allTables: [],
      table: null,
      allAttributes: [],
      attributes: [],
      queryResponse: ""
    }
  }

  componentDidMount() {
    fetch('http://localhost:8080/tables').then(response => response.json()).then(tables => {
      const allTables = []
      for (let i = 0; i < tables.length; i++) {
        allTables.push({label: tables[i], value: tables[i]})
      }
      this.setState({
        allTables: allTables
      })
    }).catch(error => console.error(error))
  }

  handleChooseTable = (selected) => {
    fetch(`http://localhost:8080/tables/${selected.value}`).then(response => response.json()).then(attributes => {
      const allAttributes = []
      for (let i = 1; i <= attributes.length; i++) {
        allAttributes.push({name: attributes[i-1], id: i})
      }
      this.setState({
        table: selected.value,
        allAttributes: allAttributes
      })
    }).catch(error => console.error(error))
  }

  handleChooseAttr = (selectedList, _) => {
    this.setState({
        attributes: selectedList
    })
  }

  handleRun = () => {
    if (this.state.table === null || this.state.attributes === []) {
        window.alert("Please select at least 1 table and attribute to run the query.")
        return
    }

    const queryParams = new URLSearchParams()
    this.state.attributes.forEach(attribute => queryParams.append('column', attribute.name));
    fetch(`http://localhost:8080/project/${this.state.table}?${queryParams.toString()}`)
      .then(response => response.json()).then(queryResponse => {
        this.setState({
          queryResponse: queryResponse
        })
      }).catch(error => console.error(error))
  }

  render() {
    return (
      <div>
        <div className="page-header">
            <h2>Admin View</h2>
        </div>
        <p>Please select the tables you wish to get FROM:</p>
        <Dropdown options={this.state.allTables} value={this.state.table} onChange={this.handleChooseTable} placeholder="Select a table" />
        <p>Please select the attributes you wish to SELECT:</p>
        <Multiselect
            options={this.state.allAttributes} 
            selectedValues={this.state.attributes} 
            onSelect={this.handleChooseAttr}
            onRemove={this.handleChooseAttr}
            displayValue="name" 
        />
        <button className="admin-run" onClick={this.handleRun}>RUN</button>
        <p>Result: {this.state.queryResponse}</p>
      </div>
    );

  }
};

export default Admin;