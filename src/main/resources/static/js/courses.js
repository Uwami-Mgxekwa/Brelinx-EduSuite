// Course management functions
async function loadCourses() {
    const data = await apiCall('/api/courses');
    displayData('coursesData', data, 'Courses');
}

function showAddCourseForm() {
    document.getElementById('addCourseForm').style.display = 'block';
}

function hideAddCourseForm() {
    document.getElementById('addCourseForm').style.display = 'none';
    clearCourseForm();
}

function clearCourseForm() {
    document.getElementById('courseName').value = '';
    document.getElementById('courseCode').value = '';
    document.getElementById('courseDescription').value = '';
    document.getElementById('courseCredits').value = '';
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
    
    if (!courseData.name || !courseData.courseCode || !courseData.credits) {
        displayMessage('coursesData', 'Please fill in required fields (Name, Code, Credits)', 'error');
        return;
    }
    
    const result = await apiCall('/api/courses', 'POST', courseData);
    if (result.error) {
        displayMessage('coursesData', result.error, 'error');
    } else {
        displayMessage('coursesData', 'Course added successfully!', 'success');
        hideAddCourseForm();
        loadCourses();
    }
}