import React from 'react';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Login from '../modules/login/Login';
import Aside from '../components/aside/Aside';
import Header from '../components/header/Header';
import './Content.scss';
import Home from '../modules/home/Home';
import Historic from '../modules/historic/Historic';


const Content: React.FC = () => {
    const router = createBrowserRouter([
        {
            path: "/",
            element: <Home/>,
        },
        {
          path: "/login",
          element: <Login/>,
        },
        {
          path: "/historic",
          element: <Historic/>,
        }
      ]);

  return (
    <div id='syg-container'>
      <Aside/>
      <Header/>
      <div id='syg-content-container'>
        <RouterProvider router={router} />
      </div>
    </div>
  );
};

export default Content;