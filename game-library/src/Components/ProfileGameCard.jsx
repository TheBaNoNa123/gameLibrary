import React from 'react'
import { useState } from 'react';
const ProfileGameCard = ({game: {name, cover}}) => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:8080/server/";

  const [ userRating, setUserRating ] = useState("");
  const [ userReview, setUserReview ] = useState("");

  const reviewHandler = async() => {
    

    const sendReview = await fetch(url, {
      method: "POST",
      headers:{"Authorization" : `Bearer ${token}`,
    "Content-Type": "application/json"},
      body: JSON.stringify({
      rating : userRating,
      review : userReview
    })
    })

  }

  return (
    <div>
      <h3 className="text">{name}</h3>
        <img src={cover} alt= {`${name} Cover image`} className="gameImg text"/>
        <form onSubmit={reviewHandler}>
          <p className="text">Personal Rating: </p>
          <input type="text" value={userRating} 
          onChange={(e) => setUserRating(e.target.value)}></input>
          
          <br/>

          <p className="text">Personal Review: </p>
          <input type="text" value={userReview} 
          onChange={(e) => setUserReview(e.target.value)}></input>
        </form>
    </div>
  )
}

export default ProfileGameCard
