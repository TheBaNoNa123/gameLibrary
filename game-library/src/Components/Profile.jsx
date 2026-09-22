import React, { useState, useEffect } from 'react'
import GameCard from './gameCard';

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
      <h1>Your Games</h1>
      <ul className="allGames">
        {games.map((game) => (
            <p>{game.name}</p>
        ))}
        </ul>
    </div>
  )
}

export default Profile
