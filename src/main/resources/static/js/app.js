// Tab switching functionality
function showTab(tabName) {
    // Hide all tabs
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });
    document.querySelectorAll('.nav-tab').forEach(tab => {
        tab.classList.remove('active');
    });
    
    // Show selected tab
    document.getElementById(tabName).classList.add('active');
    event.target.classList.add('active');
}

// API utility function
async function apiCall(endpoint, method = 'GET', data = null) {
    try {
        const options = {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            }
        };
        
        if (data) {
            options.body = JSON.stringify(data);
        }
        
        const response = await fetch(endpoint, options);
        return await response.json();
    } catch (error) {
        console.error('API Error:', error);
        return { error: error.message };
    }
}

// Utility functions for displaying data
function displayData(elementId, data, title) {
    const element = document.getElementById(elementId);
    element.innerHTML = `
        <h4>${title} Results:</h4>
        <pre style="background: #f8f9fa; padding: 15px; border-radius: 5px; overflow-x: auto;">${JSON.stringify(data, null, 2)}</pre>
    `;
}

function displayMessage(elementId, message, type) {
    const element = document.getElementById(elementId);
    const className = type === 'success' ? 'success' : 'error';
    element.innerHTML = `<div class="${className}">${typeof message === 'string' ? message : JSON.stringify(message)}</div>`;
}

// Dashboard functions
async function loadDashboardData() {
    try {
        const stats = await apiCall('/api/dashboard/stats');
        
        if (stats && !stats.error) {
            document.getElementById('schoolCount').textContent = stats.totalSchools || '0';
            document.getElementById('studentCount').textContent = stats.totalStudents || '0';
            document.getElementById('teacherCount').textContent = stats.totalTeachers || '0';
            document.getElementById('courseCount').textContent = stats.totalCourses || '0';
            document.getElementById('gradeCount').textContent = stats.totalGrades || '0';
        } else {
            // Fallback to individual API calls
            const schools = await apiCall('/api/schools');
            const students = await apiCall('/api/students');
            const teachers = await apiCall('/api/teachers');
            const courses = await apiCall('/api/courses');
            const grades = await apiCall('/api/grades');
            
            document.getElementById('schoolCount').textContent = Array.isArray(schools) ? schools.length : '0';
            document.getElementById('studentCount').textContent = Array.isArray(students) ? students.length : '0';
            document.getElementById('teacherCount').textContent = Array.isArray(teachers) ? teachers.length : '0';
            document.getElementById('courseCount').textContent = Array.isArray(courses) ? courses.length : '0';
            document.getElementById('gradeCount').textContent = Array.isArray(grades) ? grades.length : '0';
        }
    } catch (error) {
        console.error('Error loading dashboard data:', error);
    }
}

// MongoDB Info function
function showMongoInfo() {
    alert('Connected to MongoDB Atlas Cloud Database\nDatabase: brelinx_edusuite\nData is persistent and secure!');
}

// Load initial data when page loads
window.addEventListener('load', function() {
    loadDashboardData();
});