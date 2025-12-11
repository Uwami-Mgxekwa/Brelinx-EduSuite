// API testing functionality for Brelinx EduSuite

// API Testing functions
async function testEndpoint(endpoint, method) {
    const result = await apiCall(endpoint, method);
    displayData('apiResults', result, `${method} ${endpoint}`);
}