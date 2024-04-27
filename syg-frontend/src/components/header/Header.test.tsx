import { screen, render, fireEvent } from "@testing-library/react"
import Header from "./Header"
import i18n from '../../translation/i18n';

describe('Header', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('Header renders correctly', () => {
        render(<Header info={"game"}/>)
        
        const element = screen.getByTestId('syg-header-container')
        expect(element).toBeInTheDocument();
    })

    test('Header components renders correctly', () => {
        render(<Header info={"historics"}/>)
        
        const headerContainer = screen.getByTestId('syg-header-logo-container')
        expect(headerContainer).toBeInTheDocument();

        const headerLogo = screen.getByTestId('syg-header-logo')
        expect(headerLogo).toBeInTheDocument();

        const headerLogoLetters = screen.getByTestId('syg-header-logo-letters')
        expect(headerLogoLetters).toBeInTheDocument();

        const laguageSwitch = screen.getByTestId('syg-header-language-switch')
        expect(laguageSwitch).toBeInTheDocument();

        const subHeaderText = screen.getByTestId('syg-header-info')
        expect(subHeaderText).toBeInTheDocument();
    })

    test('Header active language', () => {
        render(<Header info={"game"}/>)
        
        const languageActive = screen.getByTestId('syg-header-language-switch-element-active')
        expect(languageActive).toHaveTextContent('Español');
    })

    test('Header subtitle text', () => {
        render(<Header info={"ranking"}/>)
        
        const subHeader = screen.getByTestId('syg-header-info')
        expect(subHeader).toHaveTextContent('Ranking');
    })

    test('Header subtitle text in another language', () => {
        i18n.changeLanguage('English'); 

        render(<Header info={"game"}/>)
        
        const subHeader = screen.getByTestId('syg-header-info')
        expect(subHeader).toHaveTextContent('Game');
    })

    test('Header button chagne language', () => {
        i18n.changeLanguage('Spain'); 

        render(<Header info={"game"}/>)
        
        const button = screen.getByText('Ingles'); // Encuentra el botón que cambia el idioma

        fireEvent.click(button);
 
        const languageActive = screen.getByText('English');
        expect(languageActive).toBeInTheDocument();
    })
})