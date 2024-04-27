import { screen, render, fireEvent } from "@testing-library/react"
import StadisticsGame from "./StadisticsGame"
import i18n from '../../translation/i18n';

describe('StadisticsGame', () => {
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    test('StadisticsGame renders correctly', () => {
        render(<StadisticsGame correctAnswers={10} incorrectAnswers={20}/>)
        
        const element = screen.getByTestId('stadistic-game-container')
        expect(element).toBeInTheDocument();
    })

    test('StadisticsGame components renders correctly', () => {
        render(<StadisticsGame correctAnswers={10} incorrectAnswers={20}/>)
        
        const stadisticgameCorrect = screen.getByTestId('stadistic-game-correct')
        expect(stadisticgameCorrect).toBeInTheDocument();

        const stadisticgameCorrectInfo = screen.getByTestId('stadistic-game-correct-info')
        expect(stadisticgameCorrectInfo).toBeInTheDocument();

        const stadisticgameCorrectBar = screen.getByTestId('stadistic-game-correct-bar')
        expect(stadisticgameCorrectBar).toBeInTheDocument();

        const stadisticgameInCorrect = screen.getByTestId('stadistic-game-incorrect')
        expect(stadisticgameInCorrect).toBeInTheDocument();

        const stadisticgameInCorrectInfo = screen.getByTestId('stadistic-game-incorrect-info')
        expect(stadisticgameInCorrectInfo).toBeInTheDocument();

        const stadisticgameInCorrectBar = screen.getByTestId('stadistic-game-incorrect-bar')
        expect(stadisticgameInCorrectBar).toBeInTheDocument();
    })

    test('StadisticsGame correct aswers tests', () => {
        render(<StadisticsGame correctAnswers={10} incorrectAnswers={20}/>)
        
        const stadisticgameCorrectInfo = screen.getByTestId('stadistic-game-correct-info')
        expect(stadisticgameCorrectInfo).toHaveTextContent("Respuestas correctas:");

        const stadisticgameCorrectBar = screen.getByTestId('stadistic-game-correct-bar')
        expect(stadisticgameCorrectBar).toHaveTextContent("10");
    })

    test('StadisticsGame incorrect aswers tests', () => {
        render(<StadisticsGame correctAnswers={10} incorrectAnswers={20}/>)
        
        const stadisticgameInCorrectInfo = screen.getByTestId('stadistic-game-incorrect-info')
        expect(stadisticgameInCorrectInfo).toHaveTextContent("Respuestas incorrectas:");

        const stadisticgameInCorrectBar = screen.getByTestId('stadistic-game-incorrect-bar')
        expect(stadisticgameInCorrectBar).toHaveTextContent("20");
    })
})