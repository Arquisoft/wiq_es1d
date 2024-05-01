import React from 'react';
import './Header.scss';
import { useTranslation } from 'react-i18next';
import { Button} from '@mui/material';

interface HeaderProps {
  info: string;
}

const lngs = {
  English: { nativeName: 'English' },
  Spain: { nativeName: 'Español' },
  France: { nativeName: 'France' },
  German: { nativeName: 'Allemande' }
};

const Header: React.FC<HeaderProps> = (props: HeaderProps) => {
  const { t, i18n } = useTranslation();

  return (
    <div id='syg-header-container' data-testid='syg-header-container'>
      <div id='syg-header-logo-container' data-testid='syg-header-logo-container'>
        <div id='syg-header-logo' data-testid='syg-header-logo'>
          <img src="/images/syg_logo.png" alt="Logo de syg_alvaro" />
        </div>
        <div id='syg-header-logo-letters' data-testid='syg-header-logo-letters'>
          <div id='syg-header-language-switch' data-testid='syg-header-language-switch'>
            {Object.keys(lngs).map((lng) => (
              <Button className={`syg-header-language-switch-element ${i18n.resolvedLanguage === lng ? 'active': ''}`} key={lng} onClick={() => i18n.changeLanguage(lng)} 
                data-testid={`syg-header-language-switch-element-${i18n.resolvedLanguage === lng ? 'active': ''}`}>
                {t(`header.language.${lng}`)}
              </Button>
            ))}
          </div>
          <img src="/images/syg_logo_letters.png" alt="Letrero de syg_alvaro" />
        </div>
      </div>
      <div id='syg-header-info' data-testid='syg-header-info'>
      {t(`header.subtitle.${props.info}`)}
      </div>
    </div>
  );
};

export default Header;