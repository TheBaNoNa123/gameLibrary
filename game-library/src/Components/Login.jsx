import React, { useState } from 'react'

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const loginEndPoint = "http://localhost:8080/server/login";

    const handleLogin = async(e) =>{
        e.preventDefault();

        const sendLogin = await fetch(loginEndPoint, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
                
            
        })
        
        const token = await sendLogin.json();

        console.log(JSON.stringify(token));
    }


  return (
    <div>
        <form onSubmit={handleLogin}>
            <h1>Login</h1>
            <h3>Enter Username: </h3>
            <input value={username} onChange={(e) => setUsername(e.target.value)} />

            <br/>

            <h3>Enter Password: </h3>
            <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" />

            <button type="submit" >Submit</button>
        </form>
    </div>
  )
}

export default Login