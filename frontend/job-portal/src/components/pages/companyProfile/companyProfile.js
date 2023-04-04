import React, { Component } from 'react';
import "./companyProfile.css"
import { Link } from "react-router-dom";
import "../../../constants"
import { DEFAULT_COMPANY_ID } from '../../../constants';

class CompanyProfile extends Component {
  constructor(props) {
    super(props)

    this.state = {
      industries: [
        // TODO: Fetch industries - but dont need for demo??
        "Software",
        "Entertainment",
        "Healthcare",
        "Education",
        "Retail"
      ],
      company: {},
      isEdit: false
    }
  }

  componentDidMount = () => {
      const defaultCompanyId = DEFAULT_COMPANY_ID;
      fetch(`http://localhost:8080/companies/${defaultCompanyId}`).then(response => {
        if (!response.ok) {
            return Promise.reject(response)
        } else {
            return response.json()
        }
      }).then(company => {
        this.setState({
          company: {
            id: company.companyId,
            name: company.name
          }
        })
      }).catch(errorResponse => {
        errorResponse.json().then(error => {
            console.error('Error: ', error)
            window.alert(`An error ${error.error} occurred with message ${error.message}`)
        })
      })
  }

  handleToggleEdit = () => {
    this.setState(prevState => ({
      isEdit: !prevState.isEdit
    }), () => {
      if (this.state.isEdit) {
        // TODO:save new info, give confirmation message, but we dont need this for the demo??
      }
    });
  };

  handleDelete = () => {
    if (window.confirm("Are you sure you want to delete this company?")) {
      // 2. DELETE
      const defaultCompanyId = DEFAULT_COMPANY_ID;
      fetch(`http://localhost:8080/companies/${defaultCompanyId}`, {
        method: 'DELETE'
      }).then(response => {
        if (!response.ok) {
            return Promise.reject(response)
        } else {
            return response.json()
        }
      }).then(response => {
        console.log(response)
        window.alert("Company has been successfully deleted")
      }).catch(errorResponse => {
        errorResponse.json().then(error => {
            console.error('Error: ', error)
            window.alert(`An error ${error.error} occurred with message ${error.message}`)
        })
      })
    }
  }


  render() {
    const {industries, company, isEdit} = this.state

    return (
        <div>
          <div className="page-header">
              <h2>MyCompanyProfile</h2>
          </div>
          <div className="companyprofile-delete">
              <button onClick={this.handleDelete}>Delete Company</button>
          </div>
          <div className="companyprofile-panel-container">
            <div className="companyprofile-info-container">
              <p>ID: {company.id}</p>
              <p>Name: {company.name}</p>
              <button onClick={this.handleToggleEdit} className={isEdit ? "companyprofile-edit-toggle on" : "companyprofile-edit-toggle off"}>
                {isEdit ? "SAVE" : "EDIT"}
              </button>
            </div>
        
        <div className="companyprofile-action-container">
            <h2>View Company Information</h2>
            <div className="companyprofile-actions">
              <button className="companyprofile-action">
                  <Link className= "companyprofile-action-link" to='/companyPostedJobs'>Posted Jobs</Link>
                </button>
              <button className="companyprofile-action">Ratings</button>
            </div>
        </div>

      </div>
    </div>
    )
  }
}

export default CompanyProfile;