import { useState, useEffect } from 'react'

import './App.css'
import NavBar from './Components/NavBar';
import Search from './Components/Search';


const App = () => {
  

  const [isSearching, setIsSearching] = useState('');
  const [ gameList, setGameList] = useState([]);

  


  return(
    <main>
      <div className="wrapper">
        <NavBar />
        <Search />
       

        <section className="allGames">
          <ul>
            
          </ul>
        </section>

      </div>
    </main>
  )
}



export default App
