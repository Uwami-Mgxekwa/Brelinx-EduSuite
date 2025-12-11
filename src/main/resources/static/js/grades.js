// Grade management functions
async function loadGrades() {
    const data = await apiCall('/api/grades');
    displayData('gradesData', data, 'Grades');
}

function showAddGradeForm() {
    document.getElementById('addGradeForm').style.display = 'block';
}

function hideAddGradeForm() {
    document.getElementById('addGradeForm').style.display = 'none';
    clearGradeForm();
}

function clearGradeForm() {
    document.getElementById('gradeAssignmentName').value = '';
    document.getElementById('gradeAssignmentType').value = 'EXAM';
    document.getElementById('gradeScore').value = '';
    document.getElementById('gradeMaxScore').value = '';
    document.getElementById('gradeComments').value = '';
}

async function addGrade() {
    const gradeData = {
        assignmentName: document.getElementById('gradeAssignmentName').value,
        assignmentType: document.getElementById('gradeAssignmentType').value,
        score: parseFloat(document.getElementById('gradeScore').value),
        maxScore: parseFloat(document.getElementById('gradeMaxScore').value),
        comments: document.getElementById('gradeComments').value
    };
    
    if (!gradeData.assignmentName || isNaN(gradeData.score) || isNaN(gradeData.maxScore)) {
        displayMessage('gradesData', 'Please fill in required fields (Assignment Name, Score, Max Score)', 'error');
        return;
    }
    
    if (gradeData.score > gradeData.maxScore) {
        displayMessage('gradesData', 'Score cannot be greater than max score', 'error');
        return;
    }
    
    const result = await apiCall('/api/grades', 'POST', gradeData);
    if (result.error) {
        displayMessage('gradesData', result.error, 'error');
    } else {
        displayMessage('gradesData', 'Grade added successfully!', 'success');
        hideAddGradeForm();
        loadGrades();
    }
}