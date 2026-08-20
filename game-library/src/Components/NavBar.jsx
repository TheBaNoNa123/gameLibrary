import React from 'react'
import profile_logo from '../assets/profile_logo.svg'

const NavBar = () => {
  return (
    <header>
        <nav className="navBar">
          <button className="navBut navBut-Mid">Search</button>
          <button className="navProfileBut">
            <img src={profile_logo} alt="profile_logo" className="profileLogo" />
          </button>
        </nav>
    </header>
  )
}

export default NavBar