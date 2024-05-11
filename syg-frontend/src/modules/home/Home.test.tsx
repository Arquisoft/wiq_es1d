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
        
        const homeDescription = screen.getByTestId('syg-home-description')
        expect(homeDescription).toBeInTheDocument();

        const homeDescriptionImage = screen.getByTestId('syg-home-description-image')
        expect(homeDescriptionImage).toBeInTheDocument();

        const homeDescriptionContent = screen.getByTestId('syg-home-description-content')
        expect(homeDescriptionContent).toBeInTheDocument();

        const homeInstructions = screen.getByTestId('syg-home-instructions')
        expect(homeInstructions).toBeInTheDocument();

        const homeInstructionsImage = screen.getByTestId('syg-home-instructions-image')
        expect(homeInstructionsImage).toBeInTheDocument();

        const homeInstructionsContent = screen.getByTestId('syg-home-instructions-content')
        expect(homeInstructionsContent).toBeInTheDocument();

    })
})