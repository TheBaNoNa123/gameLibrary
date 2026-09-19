import React, { useState } from 'react'

const GameCard = ({game = {}}) => {
  
  const [ save, setSave ] = useState(false);

  const token = localStorage.getItem("token");
  const serverURL = "http://localhost:8080/server/auth/savedGame";

  const {name, total_rating, total_rating_count, cover} = game || {};
  const url = cover?.url;
  const gameCover = url ? url.replace("t_thumb", "t_1080p") : "NO PICTURE";

  const likeGameHandler = async(e) => {
     e.preventDefault();
    const data = await fetch(serverURL,{
      method: "POST",
      headers: {"Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"},
      body: JSON.stringify({
        "name": name,
        "cover": gameCover
      })
    })    
  }

  return (
    <div>
        <h3 className="text">{name} <form onSubmit={likeGameHandler}><button onClick={() => !save ? setSave(true): setSave(false)}>
          {save ? "✖️": "➕"}</button></form></h3>
        <img src={gameCover} alt= {`${name} Cover image`} className="gameImg text"/>
        <p className="text">{total_rating ? Number(total_rating).toFixed(0) + "/100": "N/A"}</p>
        <p className="text">Total Ratings: {total_rating_count ? total_rating_count: "N/A"}</p>
    </div>
  );
};

export default GameCard