// Course management functionality for Brelinx EduSuite

// Course functions
async function loadCourses() {
    const data = await apiCall('/api/courses');
    displayData('coursesData', data, 'Courses');
}

function showAddCourseForm() {
    document.getElementById('addCourseForm').style.display = 'block';
}

function hideAddCourseForm() {
    document.getElementById('addCourseForm').style.display = 'none';
}

async function addCourse() {
    const courseData = {
        name: document.getElementById('courseName').value,
        courseCode: document.getElementById('courseCode').value,
        description: document.getElementById('courseDescription').value,
        credits: parseInt(document.getElementById('courseCredits').value),
        startDate: new Date().toISOString().split('T')[0],
        endDate: new Date(Date.now() + 90 * 24 * 60 * 60 * 1000).toISOString().split('T')[0]
    };
    
    const result = await apiCall('/api/courses', 'POST', courseData);
    displayMessage('coursesData', result, 'success');
    hideAddCourseForm();
    loadCourses();
}