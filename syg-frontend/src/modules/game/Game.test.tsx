import { screen, render, fireEvent, waitFor } from "@testing-library/react"
import Game from "./Game"
import i18n from '../../translation/i18n';
import { getUser, getCategories, getQuestions, updateUser } from '../../backend/dataSource';
import { MemoryRouter } from "react-router-dom";

describe('Game', () => {
    const mockUser = {
        id: '123',
        name: 'Alvaro',
        totalGames: 1,
        correctAnswers: 8,
        inCorrectAnswers: 24,
        totalQuestionAnswered: 15,
        lastCategoryPlayed: 'Deportes',
    };

    const mockCategories = [
        { id: '1', name: 'Deportes' },
        { id: '2', name: 'Ciencia' },
    ];

    const mockQuestions = [
        {
            text: '¿Cuál es el planeta más grande?',
            timeLimit: 10,
            answers: [
                { text: 'Júpiter', isCorrect: true },
                { text: 'Saturno', isCorrect: false },
                { text: 'Tierra', isCorrect: false },
                { text: 'Marte', isCorrect: false },
            ],
        },
    ];

    beforeAll(() => {
        i18n.changeLanguage('Spain'); 
    });

    // beforeEach(() => {
    //     // Mockear las funciones asíncronas
    //     jest.mock('../../backend/dataSource', () => ({
    //         getUser: jest.fn().mockResolvedValue(mockUser),
    //         getCategories: jest.fn().mockResolvedValue(mockCategories),
    //         getQuestions: jest.fn().mockResolvedValue(mockQuestions)
    //     }));
    // });

    test('Game renders correctly', () => {
        render(<Game/>)
        
        const element = screen.getByTestId('syg-game-container')
        expect(element).toBeInTheDocument();
    })

    test('Header components renders correctly', () => {
        render(<Game/>)
        
        const gameContent = screen.getByTestId('syg-game-content')
        expect(gameContent).toBeInTheDocument();

        // const gameStart = screen.getByTestId('syg-game-content-start-game')
        // expect(gameStart).toBeInTheDocument();

        // const gameStartOptions = screen.getByTestId('syg-game-content-start-game-options')
        // expect(gameStartOptions).toBeInTheDocument();

    })

    
    // test('starts game and answers questions', async () => {
    //     render(
    //         <MemoryRouter>
    //             <Game />
    //         </MemoryRouter>
    //     );

    //     // Esperar a que se carguen categorías
    //     await waitFor(() => {
    //         expect(screen.getByText('Deportes')).toBeInTheDocument();
    //     });

    //     // Iniciar el juego
    //     fireEvent.click(screen.getByText('Deportes')); // Elige una categoría para comenzar

    //     await waitFor(() => {
    //         expect(screen.getByText('¿Cuál es el planeta más grande?')).toBeInTheDocument();
    //     });

    //     // Responder una pregunta
    //     fireEvent.click(screen.getByText('Júpiter')); // Respuesta correcta

    //     await waitFor(() => {
    //         expect(updateUser).toHaveBeenCalled(); // Verificar que `updateUser` fue llamado
    //     });
    // });

    // test('finishes the game and shows results', async () => {
    //     render(
    //         <MemoryRouter>
    //             <Game />
    //         </MemoryRouter>
    //     );

    //     // Iniciar el juego
    //     fireEvent.click(screen.getByText('Deportes')); // Elegir una categoría

    //     await waitFor(() => {
    //         expect(screen.getByText('¿Cuál es el planeta más grande?')).toBeInTheDocument();
    //     });

    //     // Responder todas las preguntas y terminar el juego
    //     fireEvent.click(screen.getByText('Júpiter')); // Primera respuesta
    //     await waitFor(() => {
    //         expect(screen.getByText('game.results.title')).toBeInTheDocument(); // Verificar que muestra los resultados
    //     });
    // });

})