import React from 'react'

const GameCard = ({game: {name, total_rating, total_rating_count, cover: {url} = {}}}) => {

  return (
    <div>
        <h3>{name}</h3>
        <img src={url.replace("t_thumb", "t_1080p")} alt= {`${name} Cover image`} className="gameImg"/>
        <p>{total_rating ? Number(total_rating).toFixed(0) + "/100": "N/A"}</p>
        <p>Total Ratings: {total_rating_count ? total_rating_count: "N/A"}</p>
    </div>
  );
};

export default GameCard