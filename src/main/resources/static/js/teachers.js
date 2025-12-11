// Teacher management functions
async function loadTeachers() {
    const data = await apiCall('/api/teachers');
    displayData('teachersData', data, 'Teachers');
}

function showAddTeacherForm() {
    document.getElementById('addTeacherForm').style.display = 'block';
}

function hideAddTeacherForm() {
    document.getElementById('addTeacherForm').style.display = 'none';
    clearTeacherForm();
}

function clearTeacherForm() {
    document.getElementById('teacherFirstName').value = '';
    document.getElementById('teacherLastName').value = '';
    document.getElementById('teacherEmail').value = '';
    document.getElementById('teacherEmployeeId').value = '';
    document.getElementById('teacherDepartment').value = '';
    document.getElementById('teacherPhone').value = '';
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
    
    if (!teacherData.firstName || !teacherData.lastName || !teacherData.email || !teacherData.employeeId) {
        displayMessage('teachersData', 'Please fill in required fields (Name, Email, Employee ID)', 'error');
        return;
    }
    
    const result = await apiCall('/api/teachers', 'POST', teacherData);
    if (result.error) {
        displayMessage('teachersData', result.error, 'error');
    } else {
        displayMessage('teachersData', 'Teacher added successfully!', 'success');
        hideAddTeacherForm();
        loadTeachers();
    }
}