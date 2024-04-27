import { screen, render, fireEvent } from "@testing-library/react"
import UserInfoCard from "./UserInfoCard";
import i18n from '../../translation/i18n';

describe('UserInfoCard', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('UserInfoCard renders correctly', () => {
        render(<UserInfoCard title={"TotalGames"} info={"4"}/>)
        
        const element = screen.getByTestId('syg-historic-card-container')
        expect(element).toBeInTheDocument();
    })

    test('UserInfoCard components renders correctly', () => {
        render(<UserInfoCard title={"TotalGames"} info={"4"}/>)
        
        const historicHeaderCard = screen.getByTestId('syg-historic-card-header')
        expect(historicHeaderCard).toBeInTheDocument();

        const historicCardHeaderContent = screen.getByTestId('syg-historic-card-header-content')
        expect(historicCardHeaderContent).toBeInTheDocument();

        const historicCardHeaderTitle = screen.getByTestId('syg-historic-card-header-title')
        expect(historicCardHeaderTitle).toBeInTheDocument();

        const historicCardContent = screen.getByTestId('syg-historic-card-content')
        expect(historicCardContent).toBeInTheDocument();

        const historicCardContentInfo = screen.getByTestId('syg-historic-card-content-info')
        expect(historicCardContentInfo).toBeInTheDocument();
    })

    test('UserInfoCard header and content text', () => {
        render(<UserInfoCard title={"Juegos totales"} info={"4"}/>)
        
        const historicHeader = screen.getByTestId('syg-historic-card-header-title')
        expect(historicHeader).toHaveTextContent("Juegos totales");

        const historicCardContent = screen.getByTestId('syg-historic-card-content-info')
        expect(historicCardContent).toHaveTextContent("4");

    })
})