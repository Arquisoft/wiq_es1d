import { Category, Question, User } from "../types/types";

export function getUser(id: number): Promise<User> {
    return fetch(`http://localhost:8080/user?id=${id}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function updateUser(user: User): Promise<User> {
    return fetch(`http://localhost:8080/user`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user) 
    })
    .then(response => {
        if (!response.ok) { 
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function getQuestions():Promise<Question[]> {
    return fetch(`http://localhost:8080/question`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function getQuestionsByCategory(categoryId: number):Promise<Question[]> {
    return fetch(`http://localhost:8080/question/category?categoryId=${categoryId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function getCategories():Promise<Category[]> {
    return fetch(`http://localhost:8080/category`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}