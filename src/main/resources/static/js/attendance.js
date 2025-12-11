// Attendance management functionality for Brelinx EduSuite

// Attendance functions
async function loadAttendance() {
    const data = await apiCall('/api/attendance');
    displayData('attendanceData', data, 'Attendance Records');
}

function showAddAttendanceForm() {
    document.getElementById('addAttendanceForm').style.display = 'block';
    document.getElementById('attendanceDate').value = new Date().toISOString().split('T')[0];
}

function hideAddAttendanceForm() {
    document.getElementById('addAttendanceForm').style.display = 'none';
}

async function addAttendance() {
    const attendanceData = {
        date: document.getElementById('attendanceDate').value,
        status: document.getElementById('attendanceStatus').value,
        notes: document.getElementById('attendanceNotes').value
    };
    
    const result = await apiCall('/api/attendance', 'POST', attendanceData);
    displayMessage('attendanceData', result, 'success');
    hideAddAttendanceForm();
    loadAttendance();
}

async function quickCheckIn() {
    const attendanceData = {
        notes: 'Quick check-in from dashboard'
    };
    
    const result = await apiCall('/api/attendance/checkin', 'POST', attendanceData);
    displayMessage('attendanceData', result, 'success');
    loadAttendance();
}