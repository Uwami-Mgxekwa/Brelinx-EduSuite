// Branding and customization functionality for Brelinx EduSuite

// Branding functions
async function loadBranding() {
    const data = await apiCall('/api/branding');
    displayData('brandingData', data, 'School Branding');
}

function showBrandingForm() {
    document.getElementById('brandingForm').style.display = 'block';
}

function hideBrandingForm() {
    document.getElementById('brandingForm').style.display = 'none';
}

async function saveBranding() {
    const brandingData = {
        schoolName: document.getElementById('brandingSchoolName').value,
        logoUrl: document.getElementById('brandingLogoUrl').value,
        primaryColor: document.getElementById('brandingPrimaryColor').value,
        secondaryColor: document.getElementById('brandingSecondaryColor').value,
        welcomeMessage: document.getElementById('brandingWelcomeMessage').value,
        contactEmail: document.getElementById('brandingContactEmail').value,
        contactPhone: document.getElementById('brandingContactPhone').value,
        website: document.getElementById('brandingWebsite').value
    };
    
    const result = await apiCall('/api/branding', 'POST', brandingData);
    displayMessage('brandingData', result, 'success');
    hideBrandingForm();
    loadBranding();
}

function previewBranding() {
    const primaryColor = document.getElementById('brandingPrimaryColor').value;
    const secondaryColor = document.getElementById('brandingSecondaryColor').value;
    const schoolName = document.getElementById('brandingSchoolName').value;
    
    // Apply preview changes
    document.documentElement.style.setProperty('--primary-color', primaryColor);
    document.documentElement.style.setProperty('--secondary-color', secondaryColor);
    
    if (schoolName) {
        document.querySelector('.header h1').textContent = `🎓 ${schoolName}`;
    }
    
    alert('Preview applied! Check the header and colors.');
}