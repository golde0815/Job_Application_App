import React, { Component } from "react";
import Multiselect from 'multiselect-react-dropdown';
import "./admin.css"

class Admin extends Component {
 
  constructor(props) {
    super(props);

    this.state = {
      allTables: [],
      tables: [],
      allAttributes: [],
      attributes: [],
      queryResponse: ""
    }
  }

  componentDidMount() {
    // TODO: Get all table names 

    const allTables = [{name:'Table1', id:1}, {name:'Table2', id:2}, {name:'Table3', id:3},
                            {name:'Table4', id:4}, {name:'Table5', id:5}]
    this.setState({
        allTables: allTables
    })
  }

  handleChooseTables = (selectedList, _) => {
    // TODO: Get all the attributes from the selected tables 

    const attributes = [{name:'attr1', id:1}, {name:'attr2', id:2}, {name:'attr3', id:3},
    {name:'attr4', id:4}, {name:'attr5', id:5}]

    this.setState({
        tables:selectedList, 
        allAttributes: attributes
    })
  }

  handleChooseAttr = (selectedList, _) => {
    this.setState({
        attributes: selectedList
    })
  }

  handleRun = () => {
    if (this.state.tables === [] || this.state.attributes === []) {
        window.alert("Please select at least 1 table and attribute to run the query.")
        return
    }

    // TODO: Run the query 
    const response = "Response from tables " + this.state.tables.map(table => table.name) +
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
        <Multiselect
            options={this.state.allTables} 
            selectedValues={this.state.tables} 
            onSelect={this.handleChooseTables}
            onRemove={this.handleChooseTables}
            displayValue="name" 
        />
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