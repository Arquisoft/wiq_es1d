import React from 'react';
import { logout } from '../../secure/keycloak';
import LogoutIcon from '@mui/icons-material/Logout';
import { useTranslation } from 'react-i18next';
import './Aside.scss';
import { useNavigate } from 'react-router-dom';

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
  const { t } = useTranslation();

  function handleOnClickAsideElement(onClickAsideElement: () => void) {
    onClickAsideElement();
  }

  function handleOnClickLogoutElement() {
    navigate('/');
    logout()
  }

  return (
    <div id='syg-aside-container' data-testid='syg-aside-container'>
      <div id='syg-aside-navigation-menu' data-testid='syg-aside-navigation-menu'>
        {props.elements.map((element, index) => (
          <div key={'syg-aside-navigation-menu-element' + index} className='syg-aside-navigation-menu-element' onClick={()=>handleOnClickAsideElement(element.onClickAsideElement)}>
            {element.icon}
          <div key={'syg-aside-navigation-menu-element-text' + index} className='syg-aside-navigation-menu-element-text'>
            {t(`aside.${element.text}`)}
          </div>
          </div>
        ))}
      </div>
      <div id='syg-aside-login-menu' data-testid='syg-aside-login-menu'>
        <div id='syg-aside-username' data-testid='syg-aside-username'>
          <div id='syg-aside-username-initial' data-testid='syg-aside-username-initial'>{props.username.substring(0, 1).toUpperCase()}</div>
          <span id='syg-aside-username-complete' data-testid='syg-aside-username-complete'>{props.username}</span>
        </div>
        <div id='syg-aside-logout' onClick={handleOnClickLogoutElement} data-testid='syg-aside-logout'>
          <LogoutIcon/>
          <div id='syg-aside-logout-text' className='aside-element' data-testid='syg-aside-logout-text'>{t('aside.logout')}</div>
        </div>
      </div>
    </div>
  );
};

export default Aside;