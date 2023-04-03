import React, { Component } from "react";

class Companies extends Component {
    constructor(props) {
        super(props)

        this.state = {
            allCompanies: [],
            companies: [],
            topCompanies:[]
        }

    }

    componentDidMount() {
        // TODO: GET all companies for initialization and their ratings
        // replace the list below with the fetched values 
        
        const fetchedList = [
            {
                'companyID': 1,
                'name': 'Netflix Inc',
                'rating': '3'
            },
            {
                'companyID': 3,
                'name': 'Amazon.com',
                'rating': '5'
            },
            {
                'companyID': 5,
                'name': 'Microsoft',
                'rating': '4'
            },
            {
                'companyID': 9,
                'name': 'Snap Inc.',
                'rating': '2'
            },
        ]

        // TODO:  (GROUP BY requirement) Get all companies that have rating and salaries higher than avg of all
        const topList = [
            {
                'companyID': 1,
                'name': 'Netflix Inc',
                'rating': '3'
            },
            {
                'companyID': 3,
                'name': 'Amazon.com',
                'rating': '5'
            },
        ]

        this.setState({
            companies: fetchedList,
            allCompanies: fetchedList,
            topCompanies: topList
        })

    }

    handleGoBack = () => {
        this.setState({
            isSeeingCompany: false, 
            selectedCompany: null
        })
    }


  render() {

    return (
        <div>
            <div>
                <div className="page-header">
                    <h2>Posted Jobs</h2>
                </div>
                <p className="user-job-number-applied">Top Companies: {this.state.topCompanies.map(company => `${company.name}, `)}</p>
                <div>
                    {
                        this.state.companies.map((company, index) => (
                            
                            <button className="posted-job" onClick={() => this.handleClickCompany(company.companyID)}>
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{company.companyID}:{company.name}</p>
                                <p className="posted-job-date">Rating: {company.rating}/5</p>
                            </button>
                        ))
                    }

                </div>
            </div>
        </div>
        
    );
  }
};

export default Companies;
