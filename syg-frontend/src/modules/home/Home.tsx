import React from 'react';
import Header from '../../components/header/Header';
import './Home.scss';
import { useTranslation } from 'react-i18next';


const Home: React.FC = () => {
  const { t } = useTranslation();
  
  return (
    <div id='syg-home-container' data-testid='syg-home-container'>
        <Header info='Home' />
        <div id='syg-home-content' data-testid='syg-home-content'>
          <div id='syg-home-description' data-testid='syg-home-description'>
            <img id="syg-home-description-image" data-testid='syg-home-description-image' src="/images/syg_description.png" alt="Logo de syg_alvaro" />
            <div id='syg-home-description-content' data-testid='syg-home-description-content'>
              <h3>{t('home.description.title')}</h3>
              <p>{t('home.description.content')}</p>
            </div>
          </div>
          <div id='syg-home-instructions' data-testid='syg-home-instructions'>
            <div id='syg-home-instructions-content' data-testid='syg-home-instructions-content'>
              <h3>{t('home.instructions.title')}</h3>
              <ul>
                <li>{t('home.instructions.access')}</li>
                <li>{t('home.instructions.category')}</li>
                <li>{t('home.instructions.categorySelection')}</li>
                <li>{t('home.instructions.response')}</li>
                <li>{t('home.instructions.answered')}</li>
                <li>{t('home.instructions.historicRanking')}</li>
              </ul>
            </div>
            <img id="syg-home-instructions-image" data-testid='syg-home-instructions-image' src="/images/syg_instructions.png" alt="Logo de syg_alvaro" />
          </div>
        </div>
    </div>
  );
};

export default Home;