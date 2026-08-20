import { useState, useEffect } from 'react'

import './App.css'
import NavBar from './Components/NavBar';
import Search from './Components/Search';
import GameCard from './Components/GameCard';

const serverURL = "http://localhost:8080/server/games";


const App = () => {

  

  const [isSearching, setIsSearching] = useState('');
  const [gameList, setGameList] = useState([]);

  useEffect(() => {
    async function fetchGames(){
    const response = await fetch(serverURL);
    const games = await response.json();
    console.log(games);
    setGameList(games || []);
  } 
  fetchGames();
  }, []);
  
  

  return(
    <main>
      <div className="wrapper">
        <NavBar />
        <Search />
       
        <section>
          <ul className="allGames">
            {gameList.map((game) => (
              <GameCard key={game.id} game={game} />
            ))}            
          </ul>
        </section>

      </div>
    </main>
  )
}



export default App
