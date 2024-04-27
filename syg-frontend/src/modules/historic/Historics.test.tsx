import { screen, render, fireEvent } from "@testing-library/react"
import Historic from "./Historic"
import i18n from '../../translation/i18n';
import { getUser } from '../../backend/dataSource';


describe('Historic', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
        jest.mock('../../backend/dataSource', () => ({
            getUser: jest.fn().mockImplementation(() => {
                    return Promise.resolve({
                        id: '123',
                        name: 'Alvaro',
                        totalGames: 1,
                        correctAnswers: 8,
                        inCorrectAnswers: 24,
                        totalQuestionAnswered: 15,
                        lastCategoryPlayed: 'Deportes',
                    });
            })
        }));
    });

    test('Historic renders correctly', () => {
        render(<Historic/>)
        
        const element = screen.getByTestId('syg-historic-container')
        expect(element).toBeInTheDocument();
    })

    test('Historic components renders correctly', () => {
        render(<Historic/>)
        
        const historicContent = screen.getByTestId('syg-historic-content')
        expect(historicContent).toBeInTheDocument();

        const historicUserName = screen.getByTestId('syg-historic-user-name')
        expect(historicUserName).toBeInTheDocument();

        const historicUserInfo = screen.getByTestId('syg-historic-user-info')
        expect(historicUserInfo).toBeInTheDocument();

        const historicPrimaryInfo = screen.getByTestId('syg-header-primary-info')
        expect(historicPrimaryInfo).toBeInTheDocument();

        const historicSecondaryInfo = screen.getByTestId('syg-header-secondary-info')
        expect(historicSecondaryInfo).toBeInTheDocument();
    })

    test('Historic user name active', () => {
        render(<Historic/>)
        
        const userNameHistoric = screen.getByTestId('syg-historic-user-name')
        expect(userNameHistoric).toHaveTextContent("Datos de juego de:");
    })

    test('Historic get user active', () => {       
        render(<Historic/>)

        const userNameHistoric = screen.getByTestId('syg-historic-user-active-name')
        expect(userNameHistoric).toHaveTextContent("");
    })
})