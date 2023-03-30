import React, { Component }  from "react";
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
            'skill': 'Python',
            isEdit: false,


            'positionTemp': 'Software Engineer',
            'postedDateTemp': '23rd March, 2023',
            'locationTemp': 'Vancouver, BC',
            'descriptionTemp': 'You will need to be build a job portal',
            'salaryTemp': 10000,
            'recruiterEmailTemp': 'recruiter@gmail.com',
            'categoryTemp': 'Software',
            'skillTemp': 'Python',

        }
    }

    componentDidMount() {
        // TODO: based on this.props.jobID, GET the job info
    }

    handleToggleEdit = () => {
        const salary = document.getElementById("posted-job-salary");
        const position = document.getElementById("posted-job-position");

        this.setState((prevState) => ({
            isEdit: !prevState.isEdit
        }), () => {
            if(!this.state.isEdit) {
                // TODO: UPDATE posted job
                // on success, refresh the page to update values
                window.alert(this.state['posted-job-description'])

                window.alert('Job Posting has been updated')
                
            }
        })
    }

    handleInputChange = (event) => {
        const { id, value } = event.target;

        this.setState({ [id]: value });
    }
    

  render() {
    const {jobID, position, postedDate, location, description, salary, 
        recruiterEmail, category, skill, isEdit} = this.state

    return (
        <div>
            <div className="page-header">
                <h2>{jobID}:{position}</h2>
            </div>
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
                <p>Category: 
                    <input
                        type="text"
                        id="posted-job-category"
                        readOnly={!isEdit}
                        defaultValue={category}
                        onChange={this.handleInputChange}
                    />
                </p>
                <p>Skills: 
                    <input
                        type="text"
                        id="posted-job-skill"
                        readOnly={!isEdit}
                        defaultValue={skill}
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

export default PostedJob;