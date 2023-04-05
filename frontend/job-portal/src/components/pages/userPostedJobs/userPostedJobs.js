import React, { Component }  from "react";
import FilterBar from "./filterBar";

class CompanyPostedJobs extends Component {
    constructor(props) {
        super(props)

        this.state = {
            jobs: [],
            allJobs: [],
            selectedJob: null
        }

    }
    
    componentDidMount = () => {
        fetch('http://localhost:8080/jobs').then(response => {
            if (!response.ok) {
                return Promise.reject(response)
            } else {
                return response.json()
            }
        }).then(fetchedJobs => {
            this.setState({
                allJobs: fetchedJobs,
                jobs: fetchedJobs
            })
        }).catch(errorResponse => {
            errorResponse.json().then(error => {
                console.error('Error: ', error)
                window.alert(`An error ${error.error} occurred with message ${error.message}`)
            })
        })
    }

    handleApplyFilters = (postedAfter, location, salary) => {
        var filteredJobs = []

        // 4. SELECT
        let params = null;
        if (postedAfter === '' && location === null && salary == null) {
            filteredJobs = this.state.allJobs
        } else if (postedAfter === '' && location === null) {
            params = {
                attribute: 'salary',
                value: salary
            }
        } else if (postedAfter === '' & salary === null) {
            params = {
                attribute: 'location',
                value: location
            }
        } else if (location === null && salary === null) {
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
            fetch(`http://localhost:8080/jobs?${queryString}`).then(response => {
                if (!response.ok) {
                    return Promise.reject(response)
                } else {
                    return response.json()
                }
            }).then(filteredJobs => {
                this.setState({
                    jobs: 
                    this.state.allJobs.filter((job) => {
                        if (filteredJobs.some((obj) => obj.jobId === job.jobId)) {
                            return true;
                        } else {
                            return false;
                        }
                    })
                })
            }).catch(errorResponse => {
                errorResponse.json().then(error => {
                    console.error('Error: ', error)
                    window.alert(`An error ${error.error} occurred with message ${error.message}`)
                })
            })
        }
    }

    handleClearFilters = () => {
        this.setState({
            jobs: this.state.allJobs
        })
    }


  render() {
    return (
        <div>
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
                            
                            <button className="posted-job">
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{job.jobId}:{job.position}</p>
                                <p className="posted-job-date">Posted: {job.postedDate}</p>
                                <p className="posted-job-location">Location: {job.location}</p>
                                <p className="posted-job-salary">Salary: {job.salary}</p>
                                <p className="posted-job-num-applied">{job.numApplicants} users have applied for this job</p>
                            </button>
                        ))
                    }

                </div>
            </div>
        </div>
        
    );
  }
};

export default CompanyPostedJobs;