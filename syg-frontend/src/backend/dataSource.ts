import { Category, Question, User } from "../types/types";
import { keycloak } from '../secure/keycloak';


export function getUsers(): Promise<User[]> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/user`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('There has been an error in the search for users through the api');
        }
        return response.json();
    })
}

export function getUser(id: string): Promise<User> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/user/userId?id=${id}`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`An error occurred when searching for the user with id ${id} through the api.`);
        }
        return response.json();
    })
}

export function registryUser(user: User): Promise<User> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/user`, {
        method: 'POST',
        headers: {
            Authorization: `Bearer ${keycloak.token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user) 
    })
    .then(response => {
        if (!response.ok) { 
            throw new Error('An error occurred while registering a user through the api');
        }
        return response.json();
    })
}

export function updateUser(user: User): Promise<User> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/user`, {
        method: 'PUT',
        headers: {
            Authorization: `Bearer ${keycloak.token}`,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user) 
    })
    .then(response => {
        if (!response.ok) { 
            throw new Error('An error occurred when updating a users data through the api');
        }
        return response.json();
    })
}

export function getQuestions():Promise<Question[]> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/question`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('An error occurred when getting the questions with their answers through the api');
        }
        return response.json();
    })
}

export function getQuestionsByCategory(categoryId: number):Promise<Question[]> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/question/category?categoryId=${categoryId}`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('An error occurred when getting the questions with their answers according to their category through the api.');
        }
        return response.json();
    })
}

export function getCategories():Promise<Category[]> {
    return fetch(`http://${process.env.REACT_APP_BACKEND_HOST}/category`, {
        headers: {
            Authorization: `Bearer ${keycloak.token}`
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('An error occurred when getting the categories through the api');
        }
        return response.json();
    })
}