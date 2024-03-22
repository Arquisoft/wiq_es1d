import React from 'react';
import './Aside.scss';


const Aside: React.FC = () => {
  return (
    <div id='syg-aside-container'>
        <div id='syg-aside-navigation-menu'>
            <div>Game</div>
            <div>Historico</div>
        </div>
        <div id='syg-aside-login-menu'>
            <div>Info</div>
            <div>Logout/Login</div>
        </div>
    </div>
  );
};

export default Aside;