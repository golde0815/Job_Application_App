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
        // 7. Aggregation with GROUP BY
        // fetchedJobs already has numApplicants for each job
        fetch('http://localhost:8080/jobs').then(response => response.json()).then(fetchedJobs => {
            this.setState({
                allJobs: fetchedJobs,
                jobs: fetchedJobs
            })
        }).catch(error => console.error(error))
    }

    handleClickJob = (jobId) => {
        this.setState(prevState => ({
            isSeeingJob: !prevState.isSeeingJob,
            selectedJob: jobId
        }))
    }

    handleApplyFilters = (postedAfter, location, salary) => {
        var filteredJobs = []

        // 4. SELECT
        let params = null;
        if (postedAfter === '' && location === null && salary == null) {
            filteredJobs = this.state.allJobs
        } else if (postedAfter === '' && location === null) {
            window.alert("Applying filters with salary = " + salary)
            params = {
                attribute: 'salary',
                value: salary
            }
        } else if (postedAfter === '' & salary === null) {
            window.alert("Applying filters with location = " + location)
            params = {
                attribute: 'location',
                value: location
            }
        } else if (location === null && salary === null) {
            window.alert("Applying filters with postedAfter = " + postedAfter)
            params = {
                attribute: 'posted_date',
                value: postedAfter
            }
        }

        const queryString = new URLSearchParams(params).toString()
        if (params === null) {
            this.setState({
                jobs: filteredJobs
            })
        } else {
            fetch(`http://localhost:8080/jobs?${queryString}`).then(response => response.json()).then(filteredJobs => {
                this.setState({
                    jobs: filteredJobs
                })
            }).catch(error => console.error(error))
        }
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
                            
                            <button className="posted-job" onClick={() => this.handleClickJob(job.jobId)}>
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{job.jobId}:{job.position}</p>
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
                    jobId={this.state.selectedJob}
                    handleGoBack={this.handleGoBack}
                ></UserPostedJob>

        }
        </div>
        
    );
  }
};

export default CompanyPostedJobs;