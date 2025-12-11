// Student management functions
async function loadStudents() {
    const data = await apiCall('/api/students');
    displayData('studentsData', data, 'Students');
}

function showAddStudentForm() {
    document.getElementById('addStudentForm').style.display = 'block';
}

function hideAddStudentForm() {
    document.getElementById('addStudentForm').style.display = 'none';
    clearStudentForm();
}

function clearStudentForm() {
    document.getElementById('studentFirstName').value = '';
    document.getElementById('studentLastName').value = '';
    document.getElementById('studentEmail').value = '';
    document.getElementById('studentId').value = '';
}

async function addStudent() {
    const studentData = {
        firstName: document.getElementById('studentFirstName').value,
        lastName: document.getElementById('studentLastName').value,
        email: document.getElementById('studentEmail').value,
        studentId: document.getElementById('studentId').value
    };
    
    if (!studentData.firstName || !studentData.lastName || !studentData.email || !studentData.studentId) {
        displayMessage('studentsData', 'Please fill in all fields', 'error');
        return;
    }
    
    const result = await apiCall('/api/students', 'POST', studentData);
    if (result.error) {
        displayMessage('studentsData', result.error, 'error');
    } else {
        displayMessage('studentsData', 'Student added successfully!', 'success');
        hideAddStudentForm();
        loadStudents();
    }
}