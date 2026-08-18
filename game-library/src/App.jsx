import { useState, useEffect } from 'react'

import './App.css'
import NavBar from './Components/NavBar';
import Search from './Components/Search';

const serverURL = "http://localhost:8080/server/games";
const GET = {
  method: "GET",
  headers: {
    "Content-Type": "application/json"
  }
}

const App = () => {

  

  const [isSearching, setIsSearching] = useState('');
  const [gameList, setGameList] = useState([]);

  useEffect(() => {
    async function fetchGames(){
    const response = await fetch(serverURL);
    const games = await response.json();
    console.log(games);
  } 
  fetchGames();
  }, []);
  
  

  
  
  


  
  

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
