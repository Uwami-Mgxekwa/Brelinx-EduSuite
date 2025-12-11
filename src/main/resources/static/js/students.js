// Student management functionality for Brelinx EduSuite

// Student functions
async function loadStudents() {
    const data = await apiCall('/api/students');
    displayData('studentsData', data, 'Students');
}

function showAddStudentForm() {
    document.getElementById('addStudentForm').style.display = 'block';
}

function hideAddStudentForm() {
    document.getElementById('addStudentForm').style.display = 'none';
}

async function addStudent() {
    const studentData = {
        firstName: document.getElementById('studentFirstName').value,
        lastName: document.getElementById('studentLastName').value,
        email: document.getElementById('studentEmail').value,
        studentId: document.getElementById('studentId').value
    };
    
    const result = await apiCall('/api/students', 'POST', studentData);
    displayMessage('studentsData', result, 'success');
    hideAddStudentForm();
    loadStudents();
}