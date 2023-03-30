import React, { Component }  from "react";
import { Link } from "react-router-dom";
import "./postedJobs.css";

class PostedJob extends Component {
    constructor(props) {
        super(props)


    }

    handleClickJob = () => {

        this.setState(prevState => ({
            isSeeingJob: !prevState.isSeeingJob
        }))
    }


  render() {

    return (
            <div>{this.props.jobID}</div>
        
    );
  }
};

export default PostedJob;