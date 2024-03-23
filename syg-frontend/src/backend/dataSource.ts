export function getUser(id: number) {
    return fetch(`http://localhost:8080/user?id=${id}`)
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
}