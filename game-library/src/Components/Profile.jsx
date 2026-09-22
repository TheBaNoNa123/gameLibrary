import { useState, useEffect } from 'react'
import ProfileGameCard from './profileGameCard';
const Profile = () => {
    const token = localStorage.getItem("token");
    const url = "http://localhost:8080/server/auth/userProfileGames";
    const [games, setGames] = useState([]);

    useEffect(() => {
        async function fetchGames(){

        const getGames = await fetch(url, {
            headers: {"Authorization": `Bearer ${token}`}
        });
        const gameData = await getGames.json();
        setGames(gameData || []);


    }
    fetchGames();
    }, []);

  return (
    <div>
      <h1 className="text">Your Games</h1>
      <ul className="allGames">
        {games.map((game) => (
          <ProfileGameCard game={game}/>
        ))}
        </ul>
    </div>
  )
}

export default Profile
