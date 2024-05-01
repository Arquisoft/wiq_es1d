import { screen, render, fireEvent, waitFor } from "@testing-library/react"
import Game from "./Game"
import i18n from '../../translation/i18n';
import { getCategories, getQuestions, getUser, updateUser } from "../../backend/dataSource";
import { act } from "react";


describe('Game', () => {

    beforeAll(() => {
        i18n.changeLanguage('Spain');
        jest.useFakeTimers();
    });

    afterAll(() => {
        jest.useRealTimers();
    });

    beforeEach(() => {
        const mockCategories = [
            { id: '1', name: 'Deportes' },
            { id: '2', name: 'Category 2' },
        ];

        // Creación de una respuesta simulada
        const mockCategoriesResponse = new Response(JSON.stringify(mockCategories), {
            status: 200,
            statusText: 'OK',
            headers: { 'Content-Type': 'application/json' },
        });

        const mockUser = {
            id: '123',
            name: 'Alvaro',
            totalGames: 1,
            correctAnswers: 8,
            inCorrectAnswers: 24,
            totalQuestionAnswered: 15,
            lastCategoryPlayed: 'Deportes',
        };

        // Creación de una respuesta simulada
        const mockUsersResponse = new Response(JSON.stringify(mockUser), {
            status: 200,
            statusText: 'OK',
            headers: { 'Content-Type': 'application/json' },
        });

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

        // Creación de una respuesta simulada
        const mockQuestionsResponse = new Response(JSON.stringify(mockQuestions), {
            status: 200,
            statusText: 'OK',
            headers: { 'Content-Type': 'application/json' },
        });

        const mockUserUpdate = {
            id: '123',
            name: 'Alvaro',
            totalGames: 2,
            correctAnswers: 18,
            inCorrectAnswers: 24,
            totalQuestionAnswered: 15,
            lastCategoryPlayed: 'Deportes',
        };

        // Creación de una respuesta simulada
        const mockUsersUpdateResponse = new Response(JSON.stringify(mockUserUpdate), {
            status: 200,
            statusText: 'OK',
            headers: { 'Content-Type': 'application/json' },
        });


        jest.spyOn(global, 'fetch').mockImplementation((url) => {
            if (url === 'http://localhost:8080/category') {
                return Promise.resolve(mockCategoriesResponse);
            } else if (url === 'http://localhost:8080/user/userId?id=') {
                return Promise.resolve(mockUsersResponse);
            } else if (url === 'http://localhost:8080/question') {
                return Promise.resolve(mockQuestionsResponse);
            } else if (url === 'http://localhost:8080/question/category?categoryId=1') {
                return Promise.resolve(mockQuestionsResponse);
            } else if (url === 'http://localhost:8080/user') {
                return Promise.resolve(mockUsersUpdateResponse);
            } else {
                return Promise.reject(new Error('Unexpected URL'));
            }
        });
    });

    afterEach(() => {
        // Limpia el espía después de cada prueba
        jest.restoreAllMocks();
    });

    test('Game renders correctly', () => {
        render(<Game />)

        const element = screen.getByTestId('syg-game-container')
        expect(element).toBeInTheDocument();
    })

    test('Header components renders correctly', () => {
        render(<Game />)

        const gameContent = screen.getByTestId('syg-game-content')
        expect(gameContent).toBeInTheDocument();
    })


    test('starts standard game', async () => {
        await act(async () => {
            render(<Game />);
        });

        const gameStart = screen.getByTestId('syg-game-content-start-game')
        expect(gameStart).toBeInTheDocument();

        const gameStartOptions = screen.getByTestId('syg-game-content-start-game-options')
        expect(gameStartOptions).toBeInTheDocument();

        const standardButton = screen.getByTestId('syg-game-content-start-game-standard-button')
        expect(standardButton).toBeInTheDocument();

        // Iniciar el juego
        await act(async () => {
            fireEvent.click(standardButton);
        });

        const questionGame = screen.getByTestId('syg-game-content-question-game')
        expect(questionGame).toBeInTheDocument();

        const questionGameTime = screen.getByTestId('syg-game-content-question-time-limit')
        expect(questionGameTime).toBeInTheDocument();
        
        const questionGameTitle = screen.getByTestId('syg-game-content-question-title')
        expect(questionGameTitle).toBeInTheDocument();

        const questionGameContent = screen.getByTestId('syg-game-content-question-content')
        expect(questionGameContent).toBeInTheDocument();
    });

    test('starts category game', async () => {
        await act(async () => {
            render(<Game />);
        });

        const gameStart = screen.getByTestId('syg-game-content-start-game')
        expect(gameStart).toBeInTheDocument();

        const gameStartOptions = screen.getByTestId('syg-game-content-start-game-options')
        expect(gameStartOptions).toBeInTheDocument();

        const categoryButton = screen.getByText('Deportes')
        expect(categoryButton).toBeInTheDocument();

        // Iniciar el juego
        await act(async () => {
            fireEvent.click(categoryButton);
        });

        const questionGame = screen.getByTestId('syg-game-content-question-game')
        expect(questionGame).toBeInTheDocument();

        const questionGameTime = screen.getByTestId('syg-game-content-question-time-limit')
        expect(questionGameTime).toBeInTheDocument();
        
        const questionGameTitle = screen.getByTestId('syg-game-content-question-title')
        expect(questionGameTitle).toBeInTheDocument();

        const questionGameContent = screen.getByTestId('syg-game-content-question-content')
        expect(questionGameContent).toBeInTheDocument();

        const questionAnswer = screen.getByTestId('syg-game-question-answer-answer-number-1');
        expect(questionAnswer).toBeInTheDocument();

        // Iniciar el juego
        await act(async () => {
            fireEvent.click(questionAnswer);
        });

        // Avanza el tiempo simulado por 60 segundos
        act(() => {
            jest.advanceTimersByTime(80000); // 60 segundos en milisegundos
        });
    });

    test('end Game component', () => {
        render(<Game />)

        const gameEndContent = screen.getByTestId('syg-game-content-game-finish')
        expect(gameEndContent).toBeInTheDocument();
    })
})