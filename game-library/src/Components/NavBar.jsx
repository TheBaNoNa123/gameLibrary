import React from 'react'
import profile_logo from '../assets/profile_logo.svg'
import { useNavigate } from 'react-router-dom'

const NavBar = () => {
  const navigate = useNavigate();

  const goToProfile = () => {
    navigate("/Profile")
  }
  return (
    <header>
        <nav className="navBar">
          <button className="navBut navBut-Mid">Search</button>
          <button className="navProfileBut" onClick={goToProfile}>
            <img src={profile_logo} alt="profile_logo" className="profileLogo" />
          </button>
        </nav>
    </header>
  )
}

export default NavBar