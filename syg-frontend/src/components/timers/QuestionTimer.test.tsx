import { screen, render, fireEvent, act } from "@testing-library/react"
import Header from "./QuestionTimer"
import i18n from '../../translation/i18n';
import QuestionTimer from "./QuestionTimer";

describe('QuestionTimer', () => {
    
    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
        jest.useFakeTimers(); // Configura Jest para usar temporizadores falsos
      });
      
      afterAll(() => {
        jest.useRealTimers(); // Devuelve a temporizadores reales después de todas las pruebas
      });

    test('QuestionTimer renders correctly', () => {
        render(<QuestionTimer seconds={60} onTimeout={function (): void {
            throw new Error("Function not implemented.");
        } }/>)
        
        const element = screen.getByTestId('syg-interval-question-progress')
        expect(element).toBeInTheDocument();
    })

    test('QuestionTimer components renders correctly', () => {
        render(<QuestionTimer seconds={60} onTimeout={function (): void {
            throw new Error("Function not implemented.");
        } }/>)
        
        const questionTimerlinearProgress = screen.getByTestId('syg-interval-question-linear-progress')
        expect(questionTimerlinearProgress).toBeInTheDocument();

        const questionTimertext = screen.getByTestId('syg-interval-question-text')
        expect(questionTimertext).toBeInTheDocument();
    })

    test('QuestionTimer time at init render', () => {
        render(<QuestionTimer seconds={60} onTimeout={function (): void {
            throw new Error("Function not implemented.");
        } }/>)

        const questionTimertext = screen.getByTestId('syg-interval-question-text')
        expect(questionTimertext).toHaveTextContent('60');
    })

    test('QuestionTimer init function been called', () => {
        const onTimeoutMock = jest.fn();

        render(<QuestionTimer seconds={1} onTimeout={onTimeoutMock}/>)

        // Simula el paso del tiempo de un segundo para que la inicialización sea 0 y llamar a la función
        act(() => {
            jest.advanceTimersByTime(1000);
        });
      
        expect(onTimeoutMock).toHaveBeenCalledTimes(1);
    })
})