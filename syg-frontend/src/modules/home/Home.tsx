import React from 'react';
import Header from '../../components/header/Header';
import './Home.scss';


const Home: React.FC = () => {
  return (
    <div id='syg-home-container' data-testid='syg-home-container'>
        <Header info='Home' />
        <div id='syg-home-content' data-testid='syg-home-content'>
           PANTALLA HOME
        </div>
    </div>
  );
};

export default Home;