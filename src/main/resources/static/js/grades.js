// Grade management functionality for Brelinx EduSuite

// Grade functions
async function loadGrades() {
    const data = await apiCall('/api/grades');
    displayData('gradesData', data, 'Grades');
}

function showAddGradeForm() {
    document.getElementById('addGradeForm').style.display = 'block';
}

function hideAddGradeForm() {
    document.getElementById('addGradeForm').style.display = 'none';
}

async function addGrade() {
    const gradeData = {
        assignmentName: document.getElementById('gradeAssignmentName').value,
        assignmentType: document.getElementById('gradeAssignmentType').value,
        score: parseFloat(document.getElementById('gradeScore').value),
        maxScore: parseFloat(document.getElementById('gradeMaxScore').value),
        comments: document.getElementById('gradeComments').value
    };
    
    const result = await apiCall('/api/grades', 'POST', gradeData);
    displayMessage('gradesData', result, 'success');
    hideAddGradeForm();
    loadGrades();
}