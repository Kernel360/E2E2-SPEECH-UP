async function getMe() {

    return fetch('/users/me')
        .then(response => response.json())
        .then(me  => me)
        .catch(error => console.error('Error loading scripts:', error));
}

getMe();