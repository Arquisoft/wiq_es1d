import { screen, render, fireEvent } from "@testing-library/react"
import Home from "./Home"
import i18n from '../../translation/i18n';

describe('Home', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('Home renders correctly', () => {
        render(<Home/>)
        
        const element = screen.getByTestId('syg-home-container')
        expect(element).toBeInTheDocument();
    })

    test('Home components renders correctly', () => {
        render(<Home/>)
        
        const homeContent = screen.getByTestId('syg-home-content')
        expect(homeContent).toBeInTheDocument();
    })
})