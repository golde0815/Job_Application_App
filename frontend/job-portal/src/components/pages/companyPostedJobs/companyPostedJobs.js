import React, { Component }  from "react";
import "./companyPostedJobs.css";
import CompanyPostedJob from "./companyPostedJob";
import { DefaultCompanyContext } from "../../../DefaultCompanyContext";

class CompanyPostedJobs extends Component {
    static contextType = DefaultCompanyContext;

    constructor(props) {
        super(props)

        this.state = {
            jobs: [],
            starUsers: '',
            isSeeingJob: false,
            selectedJob: null,
        }
    }

    componentDidMount = () => {
        // TODO: choose what the default company_id should be
        const [ defaultCompanyId ] = this.context;
        this.fetchCompanyData(defaultCompanyId)
    }

    fetchCompanyData(defaultCompanyId) {
        const queryString = new URLSearchParams({
            attribute: 'company_id',
            value: defaultCompanyId
        }).toString()
        fetch(`http://localhost:8080/jobs?${queryString}`).then(response => {
            if (!response.ok) {
                return Promise.reject(response)
            } else {
                return response.json()
            }
        }).then(jobs => {
            this.setState({
                jobs: jobs
            })
        }).catch(errorResponse => {
            errorResponse.json().then(error => {
                console.error('Error: ', error)
                window.alert(`An error ${error.error} occurred with message ${error.message}`)
            })
        })

        // 10. Division
        fetch(`http://localhost:8080/users/applications/${defaultCompanyId}`).then(response => {
            if (!response.ok) {
                return Promise.reject(response)
            } else {
                return response.json()
            }
        }).then(users => {
            this.setState({
                starUsers: users.map(user => user.email).join(', ')
            })
        }).catch(errorResponse => {
            errorResponse.json().then(error => {
                console.error('Error: ', error)
                window.alert(`An error ${error.error} occurred with message ${error.message}`)
            })
        })
    }

    handleClickJob = (jobId) => {
        this.setState(prevState => ({
            isSeeingJob: !prevState.isSeeingJob,
            selectedJob: jobId
        }))
    }

    
    handleGoBack = () => {
        this.setState({
            isSeeingJob: false, 
            selectedJob: null
        })
    }


  render() {
    return (
        <div>
        {
            !this.state.isSeeingJob &&
            <div>
                <div className="page-header">
                    <h2>Company Posted Jobs</h2>
                </div>
                <div className="posted-job-staruser">
                    <p>Users that have applied to all jobs: {this.state.starUsers}</p>
                </div>
                <div>
                    {
                        this.state.jobs.map((job, index) => (
                            
                            <button className="posted-job" onClick={() => this.handleClickJob(job.jobId)}>
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{job.jobId}:{job.position}</p>
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
                <CompanyPostedJob
                    jobId={this.state.selectedJob}
                    handleGoBack={this.handleGoBack}
                ></CompanyPostedJob>

        }
        </div>
        
    );
  }
};

export default CompanyPostedJobs;