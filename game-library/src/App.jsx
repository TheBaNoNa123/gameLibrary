import { useState, useEffect } from 'react'

import './App.css'
import NavBar from './Components/NavBar';
import Search from './Components/Search';
import GameCard from './Components/GameCard';
import Register from './Components/Register';

const serverURL = "http://localhost:8080/server/auth/games";


const App = () => {

  

  const [isSearching, setIsSearching] = useState('');
  const [gameList, setGameList] = useState([]);


  useEffect(() => {
    async function fetchGames(){
    const token = localStorage.getItem("token");

    const response = await fetch(serverURL,{
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });
    if(!response.ok){
      console.log(`STATUS ERROR ${response.status}`);
      return;
    }


    const games = await response.json();
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
