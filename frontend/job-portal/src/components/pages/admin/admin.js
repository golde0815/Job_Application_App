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
      queryResponse: []
    }
  }

  componentDidMount() {
    fetch('http://localhost:8080/tables').then(response => {
      if (!response.ok) {
          return Promise.reject(response)
      } else {
          return response.json()
      }
    }).then(tables => {
      const allTables = []
      for (let i = 0; i < tables.length; i++) {
        allTables.push({label: tables[i], value: tables[i]})
      }
      this.setState({
        allTables: allTables
      })
    }).catch(errorResponse => {
      errorResponse.json().then(error => {
          console.error('Error: ', error)
          window.alert(`An error ${error.error} occurred with message ${error.message}`)
      })
    })
  }

  handleChooseTable = (selected) => {
    fetch(`http://localhost:8080/tables/${selected.value}`).then(response => {
      if (!response.ok) {
          return Promise.reject(response)
      } else {
          return response.json()
      }
    }).then(attributes => {
      this.handleChooseAttr([])  

      const allAttributes = []
      for (let i = 1; i <= attributes.length; i++) {
        allAttributes.push({name: attributes[i-1], id: i})
      }
      this.setState({
        table: selected.value,
        allAttributes: allAttributes
      })
    }).catch(errorResponse => {
      errorResponse.json().then(error => {
          console.error('Error: ', error)
          window.alert(`An error ${error.error} occurred with message ${error.message}`)
      })
    })
  }

  handleChooseAttr = (selectedList, _) => {
    this.setState({
        attributes: selectedList
    })
  }

  handleRun = () => {
    if (this.state.table === null) {
        window.alert("Please select a table to run the query.")
        return
    }

    if (this.state.attributes.length === 0) {
      window.alert("Please select at least 1 attribute to run the query.")
      return
  }

    const queryParams = new URLSearchParams()
    this.state.attributes.forEach(attribute => queryParams.append('column', attribute.name));
    fetch(`http://localhost:8080/project/${this.state.table}?${queryParams.toString()}`).then(response => {
        if (!response.ok) {
            return Promise.reject(response)
        } else {
            return response.json()
        }
      }).then(queryResponse => {
        this.setState({
          queryResponse: queryResponse
        })
      }).catch(errorResponse => {
        errorResponse.json().then(error => {
            console.error('Error: ', error)
            window.alert(`An error ${error.error} occurred with message ${error.message}`)
        })
      })
  }

  render() {
    const cols = [];
    for (const key in this.state.queryResponse[0] || {}) {
      cols.push(<th key={key}>{key}</th>);
    }
  
    const rows = this.state.queryResponse.map((row, index) => {
      const dataCells = [];
      for (const key in row) {
        dataCells.push(<td key={key}>{row[key]}</td>);
      }
      return <tr key={index}>{dataCells}</tr>;
    });

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
        <table>
          <thead>
            <tr>{cols}</tr>
          </thead>
          <tbody>{rows}</tbody>
        </table>
      </div>
    );

  }
};

export default Admin;