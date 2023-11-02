const url = 'http://localhost:8080/api/page/';


function getAllUsers() {
    fetch(url)
        .then(res => res.json())
        .then(data => {
            loadTable(data)
        })
}

function loadTable(listAllUsers) {
    let res = ``;
    for (let user of listAllUsers) {
        res +=
            `<tr>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
            <td>${user.roles.map(r => r.name.replace('ROLE_', '')).join(' ')}</td>
                <td>
                    <button class="btn btn-sm btn-primary" type="button"
                    data-bs-toggle="modal" data-bs-target="#editModal"
                    onclick="editModal(${user.id})">Edit</button></td>
                <td>
                    <button class="btn btn-sm btn-danger" type="button"
                    data-bs-toggle="modal" data-bs-target="#deleteModal"
                    onclick="deleteModal(${user.id})">Delete</button></td>
            </tr>`
    }
    document.getElementById('tableBodyAdmin').innerHTML = res;
}

function newUserTab() {
    document.getElementById('newUserForm').addEventListener('submit', (e) => {
        e.preventDefault()
        let role = document.getElementById('rolesNew')
        let rolesAddUser = []
        for (let i = 0; i < role.options.length; i++) {
            if (role.options[i].selected) {
                rolesAddUser.push({id: role.options[i].value, name: 'ROLE_' + role.options[i].innerHTML})
            }
        }
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                username: document.getElementById('usernameNew').value,
                name: document.getElementById('nameNew').value,
                lastName: document.getElementById('lastNameNew').value,
                age: document.getElementById('ageNew').value,
                password: document.getElementById('passwordNew').value,
                roles: rolesAddUser
            })
        })
            .then((response) => {
                if (response.ok) {
                    document.getElementById('usernameNew').value = '';
                    document.getElementById('nameNew').value = '';
                    document.getElementById('lastNameNew').value = '';
                    document.getElementById('ageNew').value = '';
                    document.getElementById('passwordNew').value = '';
                    document.getElementById('userTable-tab').click()
                    getAllUsers();
                }
            })
    })
}

function closeModal() {
    document.querySelectorAll(".btn-close").forEach((btn) => btn.click())
}


function editModal(id) {
    let editId = url + id;
    fetch(editId, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('editId').value = user.id;
            document.getElementById('editUsername').value = user.username;
            document.getElementById('editName').value = user.name;
            document.getElementById('editLastName').value = user.lastName;
            document.getElementById('editAge').value = user.age;
            document.getElementById('editPassword').value = '';
        })
    });

}

function validateNewUserForm() {
    let name = document.getElementById('nameNew').value;
    let lastName = document.getElementById('lastNameNew').value;
    let username = document.getElementById('usernameNew').value;
    let age = document.getElementById('ageNew').value;
    let password = document.getElementById('passwordNew').value;

    let nameError = document.getElementById('nameNewError');
    let lastNameError = document.getElementById('lastNameNewError');
    let usernameError = document.getElementById('usernameNewError');
    let ageError = document.getElementById('ageNewError');
    let passwordError = document.getElementById('passwordNewError');

    nameError.textContent = '';
    lastNameError.textContent = '';
    usernameError.textContent = '';
    ageError.textContent = '';
    passwordError.textContent = '';

    if (name.length < 2) {
        nameError.textContent = 'Name should be at least 2 characters long.';
        return false;
    }

    if (lastName.length < 2) {
        lastNameError.textContent = 'Name should be at least 2 characters long.';
        return false;
    }

    if (username.length < 2) {
        usernameError.textContent = 'Name should be at least 2 characters long.';
        return false;
    }

    if (age < 10 || age > 110) {
        ageError.textContent = 'Age should be between 10 and 110.';
        return false;
    }

    if (password.length < 6 || password.length > 20) {
        passwordError.textContent = 'Password should be between 6 and 20 characters.';
        return false;
    }

    return true;
}

function validateEditUserForm() {
    let name = document.getElementById('editName').value;
    let lastName = document.getElementById('editLastName').value;
    let username = document.getElementById('editUsername').value;
    let age = document.getElementById('editAge').value;
    let password = document.getElementById('editPassword').value;

    let nameEditError = document.getElementById('nameEditError');
    let lastNameEditError = document.getElementById('lastNameEditError');
    let usernameEditError = document.getElementById('usernameEditError');
    let ageEditError = document.getElementById('ageEditError');
    let passwordEditError = document.getElementById('passwordEditError');

    nameEditError.textContent = '';
    lastNameEditError.textContent = '';
    usernameEditError.textContent = '';
    ageEditError.textContent = '';
    passwordEditError.textContent = '';

    if (name.length < 2) {
        nameEditError.textContent = 'Name should be at least 2 characters long.';
        return false;
    }

    if (lastName.length < 2) {
        lastNameEditError.textContent = 'Last name should be at least 2 characters long.';
        return false;
    }

    if (username.length < 2) {
        usernameEditError.textContent = 'Username should be at least 2 characters long.';
        return false;
    }

    if (age < 10 || age > 110) {
        ageEditError.textContent = 'Age should be between 10 and 110.';
        return false;
    }

    if (password.length < 6 || password.length > 20) {
        passwordEditError.textContent = 'Password should be between 6 and 20 characters.';
        return false;
    }

    return true;
}





async function editUser() {
    let idValue = document.getElementById('editId').value;
    let usernameValue = document.getElementById('editUsername').value;
    let nameValue = document.getElementById('editName').value;
    let lastNameValue = document.getElementById('editLastName').value;
    let ageValue = document.getElementById('editAge').value;
    let passwordValue = document.getElementById('editPassword').value;
    let role = document.getElementById('editRole')
    let listOfRole = []
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            listOfRole.push({id: role.options[i].value, name: 'ROLE_' + role.options[i].innerHTML})
        }
    }
    let user = {
        id: idValue,
        username: usernameValue,
        name: nameValue,
        lastName: lastNameValue,
        age: ageValue,
        password: passwordValue,
        roles: listOfRole
    }
    await fetch(url, {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(user)
    });
    closeModal()
    getAllUsers()
}


function deleteModal(id) {
    let delId = url + id;
    fetch(delId, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            document.getElementById('deleteId').value = user.id;
            document.getElementById('deleteUsername').value = user.username;
            document.getElementById('deleteName').value = user.name;
            document.getElementById('deleteLastName').value = user.lastName;
            document.getElementById('deleteAge').value = user.age;
        })
    });
}

async function deleteUser() {
    const id = document.getElementById('deleteId').value
    let urlDel = url + id;

    let method = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    fetch(urlDel, method).then(() => {
        closeModal()
        getAllUsers()
    })

}
function getUserPage() {
    let url2 = url + 'user'
    fetch(url2).then(response => response.json()).then(user =>
        getInformationAboutUser(user))
}

function getInformationAboutUser(user) {
    user.roles.map(r => {
        if (r.name.replace('ROLE_', '') === 'ADMIN') {
            getAllUsers()
            newUserTab()
        }
    })
    document.getElementById('userTableBody').innerHTML = `<tr>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.roles.map(r => r.name.replace('ROLE_', '')).join(' ')}</td>
        </tr>`;

}

getUserPage();