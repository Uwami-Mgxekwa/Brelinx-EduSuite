// School management functions
async function loadSchools() {
    const data = await apiCall('/api/schools');
    displayData('schoolsData', data, 'Schools');
}

function showAddSchoolForm() {
    document.getElementById('addSchoolForm').style.display = 'block';
}

function hideAddSchoolForm() {
    document.getElementById('addSchoolForm').style.display = 'none';
    clearSchoolForm();
}

function clearSchoolForm() {
    document.getElementById('schoolName').value = '';
    document.getElementById('schoolAddress').value = '';
    document.getElementById('schoolContact').value = '';
}

async function addSchool() {
    const schoolData = {
        name: document.getElementById('schoolName').value,
        address: document.getElementById('schoolAddress').value,
        contact: document.getElementById('schoolContact').value
    };
    
    if (!schoolData.name || !schoolData.address || !schoolData.contact) {
        displayMessage('schoolsData', 'Please fill in all fields', 'error');
        return;
    }
    
    const result = await apiCall('/api/schools', 'POST', schoolData);
    if (result.error) {
        displayMessage('schoolsData', result.error, 'error');
    } else {
        displayMessage('schoolsData', 'School added successfully!', 'success');
        hideAddSchoolForm();
        loadSchools();
    }
}