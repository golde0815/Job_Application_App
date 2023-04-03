import React, { Component } from 'react';

class FilterBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
        rating: 0
    };
  }


  handleRatingChange = event => {
    this.setState({ rating: event.target.value });
  };

  handleApplyFilters = () => {
    const { rating } = this.state

    if (rating < 0 || rating > 5) {
        window.alert("Rating can only have be a value between 0 and 5")
        return
    }

    this.props.handleApplyFilters(rating)
  };

  render() {    
    return (
      <div className="filter-bar" style={{display: 'block'}}>
        <label htmlFor="salary">Minimum Rating:</label>
        <input id="salary" type="number" value={this.state.rating} onChange={this.handleRatingChange} />
        <button id="filter-apply-button" onClick={this.handleApplyFilters}>Apply Filters</button>
      </div>
    );
  }
}

export default FilterBar;
