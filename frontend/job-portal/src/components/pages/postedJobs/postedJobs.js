import React, { Component }  from "react";
import "./postedJobs.css";
import PostedJob from "./postedJob";

class PostedJobs extends Component {
    constructor(props) {
        super(props)

        this.state = {
        // TODO: Fetch posted jobs of the company
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
            starUser: 'Arya',
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


  render() {

    return (
        <div>
        {
            !this.state.isSeeingJob &&
            <div>
                <div className="page-header">
                    <h2>Posted Jobs</h2>
                </div>
                <div className="posted-job-staruser">
                    <p>Users that have applied to all jobs: {this.state.starUser}</p>
                </div>
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
                <PostedJob
                    jobID={this.state.selectedJob}
                ></PostedJob>

        }
        </div>
        
    );
  }
};

export default PostedJobs;