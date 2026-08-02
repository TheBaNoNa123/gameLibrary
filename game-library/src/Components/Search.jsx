import React from 'react'

const Search = ({isSearching, setIsSearching}) => {
  return (
    <div className="searchBar">
        <input type="text" placeholder="Search through thousands of games" 
        value={isSearching} onChange={(e) => setIsSearching(e.target.value)}/>
    </div>
  )
}

export default Search