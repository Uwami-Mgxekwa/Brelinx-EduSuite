// Attendance management functions
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
    clearAttendanceForm();
}

function clearAttendanceForm() {
    document.getElementById('attendanceDate').value = '';
    document.getElementById('attendanceStatus').value = 'PRESENT';
    document.getElementById('attendanceNotes').value = '';
}

async function addAttendance() {
    const attendanceData = {
        date: document.getElementById('attendanceDate').value,
        status: document.getElementById('attendanceStatus').value,
        notes: document.getElementById('attendanceNotes').value
    };
    
    if (!attendanceData.date) {
        displayMessage('attendanceData', 'Please select a date', 'error');
        return;
    }
    
    const result = await apiCall('/api/attendance', 'POST', attendanceData);
    if (result.error) {
        displayMessage('attendanceData', result.error, 'error');
    } else {
        displayMessage('attendanceData', 'Attendance recorded successfully!', 'success');
        hideAddAttendanceForm();
        loadAttendance();
    }
}

async function quickCheckIn() {
    const attendanceData = {
        notes: 'Quick check-in from dashboard'
    };
    
    const result = await apiCall('/api/attendance/checkin', 'POST', attendanceData);
    if (result.error) {
        displayMessage('attendanceData', result.error, 'error');
    } else {
        displayMessage('attendanceData', 'Check-in successful!', 'success');
        loadAttendance();
    }
}