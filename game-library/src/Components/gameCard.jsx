import React from 'react'

const GameCard = ({game = {}}) => {

  const {name, total_rating, total_rating_count, cover} = game || {};
  const url = cover?.url;
  const gameCover = url ? url.replace("t_thumb", "t_1080p") : "NO PICTURE";

  return (
    <div>
        <h3 className="text">{name}</h3>
        <img src={gameCover} alt= {`${name} Cover image`} className="gameImg text"/>
        <p className="text">{total_rating ? Number(total_rating).toFixed(0) + "/100": "N/A"}</p>
        <p className="text">Total Ratings: {total_rating_count ? total_rating_count: "N/A"}</p>
    </div>
  );
};

export default GameCard