import React from 'react';
import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { logout } from '../../secure/keycloak';
import LogoutIcon from '@mui/icons-material/Logout';
import './Aside.scss';

interface AsideProps {
  elements: {
    text: string;
    icon: JSX.Element
    onClickAsideElement: () => void
  }[];
  username: string;
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
        {props.elements.map((element) => (
          <div className='syg-aside-navigation-menu-element' onClick={()=>handleOnClickAsideElement(element.onClickAsideElement)}>
          {element.icon}
          <div className='syg-aside-navigation-menu-element-text'>
            {element.text}
          </div>
          </div>
        ))}
      </div>
      <div id='syg-aside-login-menu'>
        <div id='syg-aside-username'>
          <div id='syg-aside-username-initial'>{props.username.substring(0, 1).toUpperCase()}</div>
          <span id='syg-aside-username-complete'>{props.username}</span>
        </div>
        <div id='syg-aside-logout' onClick={handleOnClickLogoutElement}>
          <LogoutIcon/>
          <div className='aside-element'>Logout</div>
        </div>
      </div>
    </div>
  );
};

export default Aside;