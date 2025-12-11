// Dashboard functionality for Brelinx EduSuite

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