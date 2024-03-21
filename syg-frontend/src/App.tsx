import React from 'react';
import './App.scss';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Login from './modules/login/Login';

function App() {

  const router = createBrowserRouter([
    {
      path: "/",
      element: <Login/>,
    },
  ]);

  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
