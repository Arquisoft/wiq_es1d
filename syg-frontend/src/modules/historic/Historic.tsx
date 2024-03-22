import React from 'react';
import './Historic.scss';
import Header from '../../components/header/Header';

const Historic: React.FC = () => {
    return (
        <div id='syg-historic-container'>
            <Header info='Historicos' />
            <div id='syg-historic-content'>
                <div>logo</div>
                <div>Info</div>
            </div>
        </div>
    );
};

export default Historic;