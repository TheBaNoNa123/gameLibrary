import { useState, useEffect } from 'react'

import './App.css'
import NavBar from './Components/NavBar';
import Search from './Components/Search';


const App = () => {

  const [isSearching, setIsSearching] = useState('');

  return(
    <main>
      <div className="wrapper">
        <NavBar />
        <Search />
       

        <section className="allGames">


        </section>

      </div>
    </main>
  )
}



export default App
