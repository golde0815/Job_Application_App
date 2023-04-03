import React, { Component }  from "react";
import "./postNewJob.css";

class PostNewJob extends Component {
    constructor(props) {
        super(props)

        this.state = {
            'new-job-position': "",
            'new-job-location': "",
            'new-job-description': "",
            'new-job-salary': 0,
            'new-job-recruiterEmail': "",
            'new-job-category': "",
            'new-job-skill': "",
        }
    }

    handlePostJob = () => {
        if (window.confirm("Are you sure you want to post this job?")) {
            // TODO: POST / INSERT posted job, make sure the not nulls are checked - if a value is not null but is empty, warn the user
            const job = {
                'position': this.state['new-job-position'],
                'postedDate': Date.now(),
                'location': this.state['new-job-location'],
                'description': this.state['new-job-description'],
                'salary': this.state['new-job-salary'] || 0,
                'recruiterEmail': this.state['new-job-recruiterEmail'],
                'category': this.state['new-job-category'],
                'skill': this.state['new-job-skill'],
            }

            window.alert('New job has been posted with ID ***' + JSON.stringify(job))
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
                <p>Category: 
                    <input
                        type="text"
                        id="new-job-category"
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Skills: 
                    <input
                        type="text"
                        id="new-job-skill"
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