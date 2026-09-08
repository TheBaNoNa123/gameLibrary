import React, { useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom';
const Login = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const loginEndPoint = "http://localhost:8080/server/login";
    const navigate = useNavigate();

    const handleLogin = async(e) => {
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
        if(!sendLogin.ok){
            console.log(`HTTP ERROR: STATUS ${sendLogin.status}`);
            return;
        }
        
        const token = await sendLogin.json();
        localStorage.setItem("token", JSON.stringify(token));
        navigate("/");
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