import { Category, Question, User } from "../types/types";
import { keycloak } from '../secure/keycloak';

export function getUser(id: string): Promise<User> {
    return fetch(`http://localhost:8080/user?id=${id}`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function registryUser(user: User): Promise<User> {
    return fetch(`http://localhost:8080/user`, {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${keycloak.token}`,
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

export function updateUser(user: User): Promise<User> {
    return fetch(`http://localhost:8080/user`, {
        method: 'PUT',
        headers: {
            Authorization: `Bearer ${keycloak.token}`,
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
    return fetch(`http://localhost:8080/question`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function getQuestionsByCategory(categoryId: number):Promise<Question[]> {
    return fetch(`http://localhost:8080/question/category?categoryId=${categoryId}`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}

export function getCategories():Promise<Category[]> {
    return fetch(`http://localhost:8080/category`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}