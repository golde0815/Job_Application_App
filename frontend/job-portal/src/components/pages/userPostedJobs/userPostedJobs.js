import React, { Component }  from "react";
// import "./companyPostedJobs.css";
import UserPostedJob from "./userPostedJob";
import FilterBar from "./filterBar";

class CompanyPostedJobs extends Component {
    constructor(props) {
        super(props)

        this.state = {
            jobs: [],
            allJobs: [],
            isSeeingJob: false,
            selectedJob: null
        }

    }
    
    componentDidMount = () => {
        // TODO: GET ALL posted jobs for initialization 

        const fetchedJobs = [
            {
                'jobID': 1,
                'position': 'Software Engineer',
                'postedDate': '23rd March, 2023',
                'location': 'Vancouver, BC', 
            },
            {
                'jobID': 2, 
                'position': 'Software Engineer Coop',
                'postedDate': '23rd March, 2023',
                'location': 'Vancouver, BC',
            },
            {
                'jobID': 3, 
                'position': 'UI/UX Designer',
                'postedDate': '23rd March, 2023',
                'location': 'Vancouver, BC',
            }
        ]

        this.setState({
            allJobs: fetchedJobs, 
            jobs: fetchedJobs
        })
    }

    handleClickJob = (jobID) => {
        this.setState(prevState => ({
            isSeeingJob: !prevState.isSeeingJob,
            selectedJob: jobID
        }))
    }

    handleApplyFilters = (postedAfter, location, salary) => {
        // TODO: Apply filters, run SELECT query, Update the value of this.state.jobs based on the response

        var filteredJobs = []

        if (postedAfter === '' && location === null && salary == null) {
            filteredJobs = this.state.allJobs
        } else if (postedAfter === '' && location === null) {
            window.alert("Applying filters with salary = " + salary)
            filteredJobs = [
                {
                    'jobID': 1,
                    'position': 'Software Engineer',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC', 
                },
            ]
        } else if (postedAfter === '' & salary === null) {
            window.alert("Applying filters with location = " + location)
            filteredJobs = [
                {
                    'jobID': 1,
                    'position': 'Software Engineer',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC', 
                },
            ]
        } else if (location === null && salary === null) {
            window.alert("Applying filters with postedAfter = " + postedAfter)
            filteredJobs = [
                {
                    'jobID': 1,
                    'position': 'Software Engineer',
                    'postedDate': '23rd March, 2023',
                    'location': 'Vancouver, BC', 
                },
            ]
        } 

        this.setState({
            jobs: filteredJobs
        })

    }

    handleGoBack = () => {
        this.setState({
            isSeeingJob: false, 
            selectedJob: null
        })
    }

    handleClearFilters = () => {
        this.setState({
            jobs: this.state.allJobs
        })
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
                    handleClearFilters={this.handleClearFilters}
                ></FilterBar>
                <div>
                    {
                        this.state.jobs.map((job, index) => (
                            
                            <button className="posted-job" onClick={() => this.handleClickJob(job.jobID)}>
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{job.jobID}:{job.position}</p>
                                <p className="posted-job-date">{job.postedDate}</p>
                                <p className="posted-job-location">{job.location}</p>
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
                    handleGoBack={this.handleGoBack}
                ></UserPostedJob>

        }
        </div>
        
    );
  }
};

export default CompanyPostedJobs;