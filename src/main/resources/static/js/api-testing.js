// API Testing functions
async function testEndpoint(endpoint, method) {
    const result = await apiCall(endpoint, method);
    displayData('apiResults', result, `${method} ${endpoint}`);
}

// Additional API testing utilities
async function testAllEndpoints() {
    const endpoints = [
        { url: '/api/schools', method: 'GET' },
        { url: '/api/students', method: 'GET' },
        { url: '/api/teachers', method: 'GET' },
        { url: '/api/courses', method: 'GET' },
        { url: '/api/grades', method: 'GET' },
        { url: '/api/attendance', method: 'GET' },
        { url: '/api/dashboard/stats', method: 'GET' },
        { url: '/api/dashboard/analytics', method: 'GET' }
    ];
    
    const results = {};
    
    for (const endpoint of endpoints) {
        try {
            const result = await apiCall(endpoint.url, endpoint.method);
            results[`${endpoint.method} ${endpoint.url}`] = result;
        } catch (error) {
            results[`${endpoint.method} ${endpoint.url}`] = { error: error.message };
        }
    }
    
    displayData('apiResults', results, 'All Endpoints Test Results');
}

async function testSystemHealth() {
    const healthChecks = {
        'API Status': await apiCall('/api/status'),
        'Dashboard Stats': await apiCall('/api/dashboard/stats'),
        'Schools Count': await apiCall('/api/schools'),
        'Students Count': await apiCall('/api/students'),
        'Teachers Count': await apiCall('/api/teachers'),
        'Courses Count': await apiCall('/api/courses')
    };
    
    displayData('apiResults', healthChecks, 'System Health Check');
}