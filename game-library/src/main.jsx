import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router-dom";

import './index.css'
import App from './App.jsx'
import Register from './Components/Register.jsx';
import Login from './Components/Login.jsx';
import AuthenticatedRoute from './Components/AuthenticatedRoute.jsx';


const router = createBrowserRouter([
    {path: "/", element: <AuthenticatedRoute> <App/> </AuthenticatedRoute>},
    {path: "/Register", element: <Register />},
    {path: "/Login", element: <Login />}

])

createRoot(document.getElementById('root')).render(

    <RouterProvider router={router} />
  
)
