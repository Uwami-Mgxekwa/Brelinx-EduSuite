// Teacher management functionality for Brelinx EduSuite

// Teacher functions
async function loadTeachers() {
    const data = await apiCall('/api/teachers');
    displayData('teachersData', data, 'Teachers');
}

function showAddTeacherForm() {
    document.getElementById('addTeacherForm').style.display = 'block';
}

function hideAddTeacherForm() {
    document.getElementById('addTeacherForm').style.display = 'none';
}

async function addTeacher() {
    const teacherData = {
        firstName: document.getElementById('teacherFirstName').value,
        lastName: document.getElementById('teacherLastName').value,
        email: document.getElementById('teacherEmail').value,
        employeeId: document.getElementById('teacherEmployeeId').value,
        department: document.getElementById('teacherDepartment').value,
        phoneNumber: document.getElementById('teacherPhone').value,
        hireDate: new Date().toISOString().split('T')[0]
    };
    
    const result = await apiCall('/api/teachers', 'POST', teacherData);
    displayMessage('teachersData', result, 'success');
    hideAddTeacherForm();
    loadTeachers();
}