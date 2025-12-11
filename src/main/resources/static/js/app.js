// Main Application JavaScript for Brelinx EduSuite

// Tab switching
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

// API functions
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

// Utility functions
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

// MongoDB Info function
function showMongoInfo() {
    alert('Connected to MongoDB Atlas Cloud Database\nDatabase: brelinx_edusuite\nData is persistent and secure!');
}

// Load initial data when page loads
window.addEventListener('load', function() {
    loadDashboardData();
});