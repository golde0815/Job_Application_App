import React, { Component } from "react";
import Multiselect from 'multiselect-react-dropdown';
import "./admin.css"
import Dropdown from 'react-dropdown';

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
    // TODO: Get all table names 

    const allTables = [{label:'Table1', value:1}, {label:'Table2', value:2}, {label:'Table3', value:3},
                            {label:'Table4', value:4}, {label:'Table5', value:5}]
    this.setState({
        allTables: allTables
    })
  }

  handleChooseTable = (selected) => {
    // TODO: Get all the attributes from the selected tables 

    const attributes = [{name:'attr1', id:1}, {name:'attr2', id:2}, {name:'attr3', id:3},
    {name:'attr4', id:4}, {name:'attr5', id:5}]

    this.setState({
        table: selected, 
        allAttributes: attributes
    })
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

    // TODO: Run the query 
    const response = "Response from table" + this.state.table +
    " with attributes " + this.state.attributes.map(attr => attr.name)

    this.setState({
        queryResponse: response
    })
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