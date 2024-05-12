import { screen, render, waitFor } from "@testing-library/react"
import Ranking from "./Ranking"
import i18n from '../../translation/i18n';
import { MemoryRouter } from "react-router-dom";
import { act } from "react";

describe('Ranking', () => {
  beforeAll(() => {
    i18n.changeLanguage('Spain');
  });

  beforeEach(() => {
    const mockUser = {
      id: '123',
      name: 'Alvaro',
      totalGames: 1,
      correctAnswers: 8,
      inCorrectAnswers: 24,
      totalQuestionAnswered: 15,
      lastCategoryPlayed: 'Deportes',
    };

    const mockUsers = [
      {
        id: '1234',
        name: 'Pablo',
        totalGames: 1,
        correctAnswers: 9,
        inCorrectAnswers: 22,
        totalQuestionAnswered: 15,
        lastCategoryPlayed: 'Deportes',
      },
      {
        id: '123',
        name: 'Alvaro',
        totalGames: 1,
        correctAnswers: 8,
        inCorrectAnswers: 24,
        totalQuestionAnswered: 15,
        lastCategoryPlayed: 'Deportes',
      }
    ];

    // Creación de una respuesta simulada
    const mockUsersResponse = new Response(JSON.stringify(mockUsers), {
      status: 200,
      statusText: 'OK',
      headers: { 'Content-Type': 'application/json' },
    });

    // Creación de una respuesta simulada
    const mockUserResponse = new Response(JSON.stringify(mockUser), {
      status: 200,
      statusText: 'OK',
      headers: { 'Content-Type': 'application/json' },
    });



    jest.spyOn(global, 'fetch').mockImplementation((url) => {
      if (url === 'http://localhost:8080/user') {
        return Promise.resolve(mockUsersResponse);
      } else if (url === 'http://localhost:8080/user/userId?id=') {
        return Promise.resolve(mockUserResponse);
      } else {
        return Promise.reject(new Error('Unexpected URL'));
      }
    });
  });

  afterEach(() => {
    // Limpia el espía después de cada prueba
    jest.restoreAllMocks();
  });

  test('Ranking renders correctly', async () => {
    await act(async () => {
      render(<Ranking />);
    });

    const element = screen.getByTestId('syg-ranking-container')
    expect(element).toBeInTheDocument();
  })

  test('Ranking components renders correctly', () => {
    render(<Ranking />)

    const headerContainer = screen.getByTestId('syg-ranking-content')
    expect(headerContainer).toBeInTheDocument();
  })

  test('checks DataGrid pagination and sorting', async () => {
    render(
      <MemoryRouter>
        <Ranking />
      </MemoryRouter>
    );

    // Esperar a que se complete la carga
    await waitFor(() => {
      expect(screen.getByTestId('syg-ranking-container')).toBeInTheDocument();
    });

    screen.getByRole('grid'); // Localizar el DataGrid
  });
})