import React, { useState } from 'react'

const Register = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const registerEndPoint = "http://localhost:8080/server/public/register";

    const handleRegister = async(e) => {
        e.preventDefault();

        const sendData = await fetch(registerEndPoint, {
            method: "POST",
            headers: {
                "Content-Type" : "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
    
        })
        if(Response.ok){
            console.log("Register successful")
        }
    }

  return (
    <div>
        <h1>Register</h1>
        <form onSubmit={handleRegister}>
            <p>Enter username: </p>
            <input value={username} onChange={(e) => setUsername(e.target.value)} 
            placeholder="Enter username" type="text"/>

            <br/>

            <p>Enter password: </p>
            <input value={password} onChange={(e) => setPassword(e.target.value)}  
            placeholder="Enter password" type="password"/>
            
            <br/>
            <br/>

            <button type="submit">Submit</button>
        </form>
        
    </div>
  )
}

export default Register