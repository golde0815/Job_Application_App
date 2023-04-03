import React, { Component }  from "react";
import "./userPostedJob.css";

class UserPostedJob extends Component {
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
            'skill': 'Python',
            'numberApplied': 34
        }
    }

    componentDidMount() {
        // TODO: based on this.props.jobID, GET the job info
        // please also get the GROUP BY requirement (no. of people who have applied to the job)
    }


  render() {
    const {jobID, position, postedDate, location, description, salary, 
        recruiterEmail, category, skill, numberApplied} = this.state

    return (
        <div>
            <div className="page-header">
                <h2>{jobID}:{position}</h2>
            </div>
            <button className="go-back" onClick={this.props.handleGoBack}>
                GO BACK
            </button>
            <p className="user-job-number-applied">{numberApplied} have applied to this job.</p>
            <div className="user-job-info">
                <p>
                    Posted: {postedDate}
                </p>
                <p>
                    Position: {position}
                </p>
                <p>
                    Salary: {salary}
                </p>
                <p>
                    Location: {location}
                </p>
                <p>
                    Recruiter Email: {recruiterEmail}
                </p>
                <p>
                    Category: {category}
                </p>
                <p>
                    Skills: {skill}
                </p>
                <p>
                    Description: {description}
                </p>
            </div>
            <button className={"user-apply"}>
                APPLY
            </button>
        </div>
        
    );
  }
};

export default UserPostedJob;