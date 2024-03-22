import React from 'react';
import './Header.scss';

interface HeaderProps {
  info: string;
}

const Header: React.FC<HeaderProps> = (props: HeaderProps) => {
  return (
    <div id='syg-header-container'>
      <div id='syg-header-logo-container'>
        <div id='syg-header-logo'>
          <img src="/images/syg_logo.png" alt="Logo de syg_alvaro" />
        </div>
        <div id='syg-header-logo-letters'>
          <img src="/images/syg_logo_letters.png" alt="Letrero de syg_alvaro" />
        </div>
      </div>
      <div id='syg-header-info'>
        {props.info}
      </div>
    </div>
  );
};

export default Header;