import React, { Component }  from "react";
// import "./companyPostedJobs.css";
import UserPostedJob from "./userPostedJob";
import FilterBar from "./filterBar";

class CompanyPostedJobs extends Component {
    constructor(props) {
        super(props)

        this.state = {
        // TODO: GET all posted jobs with WHERE based on filters, fill this list with it instead
            jobs: [
                {
                    'jobID': 1,
                    'position': 'Software Engineer',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC'
                },
                {
                    'jobID': 2, 
                    'position': 'Software Engineer Coop',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC'
                },
                {
                    'jobID': 3, 
                    'position': 'UI/UX Designer',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC'
                }
            ],
            isSeeingJob: false,
            selectedJob: null
        }

    }

    handleClickJob = (jobID) => {
        this.setState(prevState => ({
            isSeeingJob: !prevState.isSeeingJob,
            selectedJob: jobID
        }))
    }

    handleApplyFilters = (company, postedAfter, location, salary) => {
        console.warn(company)
        console.warn(postedAfter)
        console.warn(location)
        console.warn(salary)
        // TODO: Apply filters, run SELECT query if no company filter, run JOIN query if compnay filter selected
        // Update the value of this.state.jobs based on the response
    }


  render() {

    return (
        <div>
        {
            !this.state.isSeeingJob &&
            <div>
                <div className="page-header">
                    <h2>Posted Jobs</h2>
                </div>
                <FilterBar
                    handleApplyFilters={this.handleApplyFilters}
                ></FilterBar>
                <div>
                    {
                        this.state.jobs.map((job, index) => (
                            
                            <button className="posted-job" onClick={() => this.handleClickJob(job.jobID)}>
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{job.jobID}:{job.position}</p>
                                <p className="posted-job-date">{job.postedDate}</p>
                                <p className="">{job.location}</p>
                            </button>
                        ))
                    }

                </div>
            </div>
        }
        {
            this.state.isSeeingJob && 
                <UserPostedJob
                    jobID={this.state.selectedJob}
                ></UserPostedJob>

        }
        </div>
        
    );
  }
};

export default CompanyPostedJobs;