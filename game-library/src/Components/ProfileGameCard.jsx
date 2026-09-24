import React, { useEffect } from 'react'
import { useState } from 'react';
const ProfileGameCard = ({game: {name, cover, rating, review}}) => {
  const token = localStorage.getItem("token");
  const url = "http://localhost:8080/server/auth/savedGame";
  const deleteURL = "http://localhost:8080/server/auth/deleteGame";

  const [ userRating, setUserRating ] = useState(rating);
  const [ userReview, setUserReview ] = useState(review);

  

  const reviewHandler = async(e) => {
    e.preventDefault();
    
    const sendReview = await fetch(url, {
      method: "POST",
      headers:{"Authorization" : `Bearer ${token}`,
    "Content-Type": "application/json"},
      body: JSON.stringify({
      name : name,  
      rating : userRating,
      review : userReview
    })
    })
  }

  const deleteHandler = async() => {

    const sendDelete = await fetch(`${deleteURL}?gameName=${name}`,{
      method: "DELETE",
      headers: {"Authorization" : `Bearer ${token}`}
  })
    const response = sendDelete.response();

    if(response.ok){
      console.log(`${name} has been deleted.`);
    }

  }

  return (
    <div>
      <button onClick={deleteHandler}></button>
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

          <br/>

          <button type="submit">Save</button>
        </form>
        
    </div>
  )
}

export default ProfileGameCard
