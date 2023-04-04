import React, { Component }  from "react";
import "./companyPostedJob.css";

class CompanyPostedJob extends Component {
    constructor(props) {
        super(props)

        this.state = {
            'jobId': this.props.jobId,
            'position': null,
            'postedDate': null,
            'location': null,
            'description': null,
            'salary': null,
            'recruiterEmail': null,
            isEdit: false,
        }
    }

    componentDidMount() {
        const queryString = new URLSearchParams({
            attribute: 'job_id',
            value: this.props.jobId
        }).toString()
        fetch(`http://localhost:8080/jobs?${queryString}`).then(response => response.json()).then(jobs => {
            const job = jobs[0]
            this.setState({
                'jobId': job.jobId,
                'position': job.position,
                'postedDate': job.postedDate,
                'location': job.location,
                'description': job.description,
                'salary': job.salary,
                'recruiterEmail': job.recruiterEmail,
            })
        }).catch(error => console.error(error))
    }

    handleToggleEdit = () => {
        this.setState((prevState) => ({
            isEdit: !prevState.isEdit
        }), () => {
            if(!this.state.isEdit) {
                // 3. UPDATE
                const toUpdate = []
                if (this.state['posted-job-position']) {
                    toUpdate.push({
                        'attribute': 'position',
                        'value': this.state['posted-job-position']
                    })
                }
                if (this.state['posted-job-salary']) {
                    toUpdate.push({
                        'attribute': 'salary',
                        'value': this.state['posted-job-salary']
                    })
                }
                if (this.state['posted-job-location']) {
                    toUpdate.push({
                        'attribute': 'location',
                        'value': this.state['posted-job-location']
                    })
                }
                if (this.state['posted-job-email']) {
                    toUpdate.push({
                        'attribute': 'recruiter_email',
                        'value': this.state['posted-job-email']
                    })
                }
                if (this.state['posted-job-description']) {
                    toUpdate.push({
                        'attribute': 'description',
                        'value': this.state['posted-job-description']
                    })
                }
                const requestBody = {
                    toUpdate: toUpdate,
                    jobId: this.props.jobId
                }

                fetch('http://localhost:8080/jobs', {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestBody)
                }).then(response => {
                    console.log(response)
                    window.alert('Job Posting has been successfully updated')
                }).catch(error => {
                    console.error(error)
                    window.alert('An error occured while updating the job posting. ' + error)
                })                
            }
        })
    }

    handleInputChange = (event) => {
        const { id, value } = event.target;

        this.setState({ [id]: value });
    }
    

  render() {
    const {jobId, position, postedDate, location, description, salary, 
        recruiterEmail, isEdit} = this.state

    return (
        <div>
            <div className="page-header">
                <h2>{jobId}:{position}</h2>
            </div>
            <button className="go-back" onClick={this.props.handleGoBack}>
                GO BACK
            </button>
            <p>Posted: {postedDate}</p>
            <div className="posted-job-info">
                <p>Position: 
                    <input
                        type="text"
                        id="posted-job-position"
                        readOnly={!isEdit}
                        defaultValue={position}
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Salary: 
                    <input
                        type="number"
                        id="posted-job-salary"
                        readOnly={!isEdit}
                        defaultValue={salary}
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Location: 
                    <input
                        type="text"
                        id="posted-job-location"
                        readOnly={!isEdit}
                        defaultValue={location}
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Recruiter Email: 
                    <input
                        type="text"
                        id="posted-job-email"
                        readOnly={!isEdit}
                        defaultValue={recruiterEmail}
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Description: 
                    <input
                        type="text"
                        id="posted-job-description"
                        readOnly={!isEdit}
                        defaultValue={description}
                        onChange={this.handleInputChange}
                    />
                </p>
            </div>
            <button onClick={this.handleToggleEdit} className={isEdit ? "postedjob-edit-toggle on" : "postedjob-edit-toggle off"}>
                {isEdit ? "SAVE" : "EDIT"}
            </button>
        </div>
        
    );
  }
};

export default CompanyPostedJob;