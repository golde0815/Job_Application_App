import React, { Component }  from "react";
import { Link } from "react-router-dom";
import "./postedJob.css";

class PostedJob extends Component {
    constructor(props) {
        super(props)

        this.state = {
            'jobID': 1,
            'position': 'Software Engineer',
            'postedDate': '23rd March, 2023',
            'location': 'Vancouver, BC',
            'description': 'You will need to be build a job portal',
            'salary': 10000,
            'recruiterEmail': 'recruiter@gmail.com',
            'category': 'Software',
            'skill': 'Python'
        }
    }

    componentDidMount() {
        // TODO: based on this.props.jobID, GET the job info
    }

    handleClickJob = () => {

  
    }


  render() {
    const {jobID, position, postedDate, location, description, salary, recruiterEmail, category, skill} = this.state

    return (
        <div>
            <div className="page-header">
                <h2>{jobID}:{position}</h2>
            </div>
            <p>Posted: {postedDate}</p>
            <div className="posted-job-info">
                <p>Salary: 
                    <input
                        type="number"
                        id="posted-job-salary"
                        readOnly
                        value={salary}
                    />
                </p>
                <p>Location: 
                    <input
                        type="text"
                        id="posted-job-location"
                        readOnly
                        value={location}
                    />
                </p>
                <p>Recruiter Email: 
                    <input
                        type="text"
                        id="posted-job-email"
                        readOnly
                        value={recruiterEmail}
                    />
                </p>
                <p>Category: 
                    <input
                        type="text"
                        id="posted-job-category"
                        readOnly
                        value={category}
                    />
                </p>
                <p>Skills: 
                    <input
                        type="text"
                        id="posted-job-skill"
                        readOnly
                        value={skill}
                    />
                </p>
                <p>Description: 
                    <input
                        type="text"
                        id="posted-job-description"
                        readOnly
                        value={description}
                    />
                </p>
            </div>
        </div>
        
    );
  }
};

export default PostedJob;