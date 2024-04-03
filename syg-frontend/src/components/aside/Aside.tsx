import React from 'react';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../secure/keycloak';
import './Aside.scss';

interface AsideProps {
  elements: {
    text: string;
    onClickAsideElement: () => void
  }[];
  username: React.ReactNode;
}

const Aside: React.FC<AsideProps> = (props: AsideProps) => {
  const navigate = useNavigate();

  function handleOnClickAsideElement(onClickAsideElement: () => void) {
    onClickAsideElement();
  }

  function handleOnClickLogoutElement() {
    logout()
  }

  return (
    <div id='syg-aside-container'>
      <div id='syg-aside-navigation-menu'>
        {props.elements.map((element, index) => (
          <Button
            key={index}
            className='aside-element'
            onClick={() => handleOnClickAsideElement(element.onClickAsideElement)}
          >
            {element.text}
          </Button>
        ))}
      </div>
      <div id='syg-aside-login-menu'>
        <div id='syg-aside-username'>{props.username}</div>
        <Button
          className='aside-element'
          onClick={handleOnClickLogoutElement}
        >
          Logout
        </Button>
      </div>
    </div>
  );
};

export default Aside;