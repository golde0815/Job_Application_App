import React, { Component } from "react";
import FilterBar from "./filterBar";

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
        fetch('http://localhost:8080/companies').then(response => response.json()).then(fetchedList => {
            this.setState({
                companies: fetchedList,
                allCompanies: fetchedList
            })
        }).catch(error => console.error(error))
    }

    handleGoBack = () => {
        this.setState({
            isSeeingCompany: false, 
            selectedCompany: null
        })
    }

    handleApplyFilters = (postedAfter, minRating) => {
        var filteredCompanies = []

        let params = null;
        if (postedAfter === '' && minRating === null) {
            filteredCompanies = this.state.allCompanies
        } else if (postedAfter === '') {
            // 8. Aggregation with HAVING
            params = {
                'minimumRating': minRating
            }
        } else if (minRating === null) {
            // 6. JOIN
            params = {
                'postedAfter': postedAfter
            }
        }

        const queryString = new URLSearchParams(params).toString()
        if (params === null) {
            this.setState({
                companies: filteredCompanies
            })
        } else {
            fetch(`http://localhost:8080/companies?${queryString}`).then(response => {
                if (!response.ok) {
                    return Promise.reject(response)
                } else {
                    return response.json()
                }
            }).then(filteredCompanies => {
                this.setState({
                    companies: filteredCompanies
                })
            }).catch(errorResponse => {
                errorResponse.json().then(error => {
                    console.error('Error: ', error)
                    window.alert(`An error ${error.error} occurred with message ${error.message}`)
                })
            })
        }

        if (minRating !== null) {
            // 9. Nested Aggregation with GROUP BY
            const params = {
                minimumAverageRating: minRating
            }
            const queryString = new URLSearchParams(params).toString()
            fetch(`http://localhost:8080/topcompanies?${queryString}`).then(response => {
                if (!response.ok) {
                    return Promise.reject(response)
                } else {
                    return response.json()
                }
            }).then(topList => {
                this.setState({
                    topCompanies: topList
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
            companies: this.state.allCompanies
        })
    }

  render() {

    return (
        <div>
            <div>
                <div className="page-header">
                    <h2>Companies</h2>
                </div>
                <FilterBar
                    handleApplyFilters={this.handleApplyFilters}
                    handleClearFilters={this.handleClearFilters}
                ></FilterBar>
                <p className="user-job-number-applied">Top Companies: {this.state.topCompanies.map(company => company.name).join(', ')}</p>
                <div>
                    {
                        this.state.companies.map((company, index) => (
                            
                            <button className="posted-job">
                                <p className="posted-job-index">{index+1}</p>
                                <p className="posted-job-name">{company.companyId}:{company.name}</p>
                                <p className="posted-job-date">Rating: {company.rating}/10</p>
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
