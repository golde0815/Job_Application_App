import React, { Component } from 'react';

class FilterBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
        rating: null,
        postedAfter: '',
    };
  }


  handleRatingChange = event => {
    this.setState({ rating: event.target.value });
  };

  handlePostedAfterChange = event => {
    this.setState({ postedAfter: event.target.value });

  };

  handleApplyFilters = () => {
    const { postedAfter, rating } = this.state

    if (!(postedAfter !== '' && rating === null) &&
    !(postedAfter === '' && rating !== null) &&
    !(postedAfter === '' && rating === null)) {
      window.alert("Please only select on filter at a time.")
      return
    }

    if (rating < 0 || rating > 10) {
        window.alert("Rating can only have be a value between 0 and 5")
        return
    }

    this.props.handleApplyFilters(postedAfter, rating)
  };

  handleClearFilters = () => {
    this.setState({
      postedAfter: '',
      rating: null, 
    })

    document.getElementById('rating-comp').value = null
    document.getElementById('posted-after-comp').value = ''

    this.props.handleClearFilters()
  }

  render() {    
    return (
      <div className="filter-bar" style={{display: 'block'}}>
        <label htmlFor="posted-after-comp">Posted After:</label>
        <input id="posted-after-comp" type="date" value={this.state.postedAfter} onChange={this.handlePostedAfterChange} />
        <label htmlFor="rating-comp" style={{marginLeft: '20px'}}>Minimum Avg Rating:</label>
        <input id="rating-comp" type="number" value={this.state.rating} onChange={this.handleRatingChange} />
        <button id="filter-apply-button" onClick={this.handleApplyFilters}>Apply Filters</button>
        <button id="filter-apply-button" onClick={this.handleClearFilters}>Clear Filters</button>
      </div>
    );
  }
}

export default FilterBar;
