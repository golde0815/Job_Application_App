import React, { Component }  from "react";
import "./postNewJob.css";
import { DEFAULT_COMPANY_ID } from "../../../constants";

class PostNewJob extends Component {
    constructor(props) {
        super(props)

        this.state = {
            'new-job-position': "",
            'new-job-location': "",
            'new-job-description': "",
            'new-job-salary': 0,
            'new-job-recruiterEmail': "",
        }
    }

    handlePostJob = () => {
        if (window.confirm("Are you sure you want to post this job?")) {
            const job = {
                'companyId': DEFAULT_COMPANY_ID,
                'position': this.state['new-job-position'],
                'location': this.state['new-job-location'],
                'description': this.state['new-job-description'],
                'salary': this.state['new-job-salary'],
                'recruiterEmail': this.state['new-job-email'],
            }

            // 1. INSERT
            fetch('http://localhost:8080/jobs', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(job)
            }).then(response => {
                if (!response.ok) {
                    return Promise.reject(response)
                } else {
                    return response.json()
                }
            }).then(response => {
                console.log(response)
                window.alert("New job has been successfully posted.")
            }).catch(errorResponse => {
                errorResponse.json().then(error => {
                    console.error('Error: ', error)
                    window.alert(`An error ${error.error} occurred with message ${error.message}`)
                })
            })
        }
    }

    handleInputChange = (event) => {
        const { id, value } = event.target;

        this.setState({ [id]: value });
    }
    

  render() {
    return (
        <div>
            <div className="page-header">
                <h2>Create New Job Posting</h2>
            </div>
            <div className="new-job-info">
                <p>Position: 
                    <input
                        type="text"
                        id="new-job-position"
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Salary: 
                    <input
                        type="number"
                        id="new-job-salary"
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Location: 
                    <input
                        type="text"
                        id="new-job-location"
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Recruiter Email: 
                    <input
                        type="text"
                        id="new-job-email"
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Description: 
                    <input
                        type="text"
                        id="new-job-description"
                        onChange={this.handleInputChange}
                    />
                </p>
            </div>
            <button onClick={this.handlePostJob} className="new-job-submit">
                SUBMIT
            </button>
        </div>
        
    );
  }
};

export default PostNewJob;