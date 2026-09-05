import React, { useState } from 'react'

const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const url = "https"

    const handleLogin = async(e) =>{
        e.preventDefault();

        const sendLogin = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
                
            
        }) 
    }


  return (
    <div>
        <form action="onSubmit">
            <h1>Login</h1>
            <h3>Enter Username: </h3>
            <input value={username} onChange={(e) => setUsername(e.target.value)} />

            <br/>

            <h3>Enter Password: </h3>
            <input value={password} onChange={(e) => setPassword(e.target.value)}/>

            <button type="submit">Submit</button>
        </form>
    </div>
  )
}

export default Login