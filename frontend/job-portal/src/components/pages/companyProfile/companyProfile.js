import React, { Component } from 'react';
import "./companyProfile.css"
import { Link } from "react-router-dom";

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
      const defaultCompanyId = 5;
      fetch(`http://localhost:8080/companies/${defaultCompanyId}`).then(response => response.json()).then(company => {
          this.setState({
            company: {
              id: company.companyId,
              name: company.name
            }
          })
      }).catch(error => console.error(error))
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
      // TODO: Choose default company_id
      // 2. DELETE
      const defaultCompanyId = 5;
      fetch(`http://localhost:8080/companies/${defaultCompanyId}`, {
        method: 'DELETE'
      }).then(response => console.log(response)).catch(error => console.error(error))

      window.alert("Company has been deleted")
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
              {
                !isEdit && 
                // TODO: maybe remove this?
                <p>Industry: {company.industry}</p>
              }

              {
                isEdit && 
                  <div>
                    <p>Industry:</p>
                    <select id="companyprofile-industry-dropdown">
                      <option value="0">{company.industry}</option>
                      {industries.map((industry, index) => (
                        <option key={index} value={industry}>
                          {industry}
                        </option>
                      ))}
                    </select>
                  </div>
              }
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