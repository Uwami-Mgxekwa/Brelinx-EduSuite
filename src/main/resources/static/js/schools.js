// School management functionality for Brelinx EduSuite

// School functions
async function loadSchools() {
    const data = await apiCall('/api/schools');
    displayData('schoolsData', data, 'Schools');
}

function showAddSchoolForm() {
    document.getElementById('addSchoolForm').style.display = 'block';
}

function hideAddSchoolForm() {
    document.getElementById('addSchoolForm').style.display = 'none';
}

async function addSchool() {
    const schoolData = {
        name: document.getElementById('schoolName').value,
        address: document.getElementById('schoolAddress').value,
        contact: document.getElementById('schoolContact').value
    };
    
    const result = await apiCall('/api/schools', 'POST', schoolData);
    displayMessage('schoolsData', result, 'success');
    hideAddSchoolForm();
    loadSchools();
}