const fileInput = document.getElementById('image');
const preview = document.getElementById('preview');

fileInput.addEventListener('change', function() {
  const file = this.files[0];
  if (file) {
      const reader = new FileReader();
      
      reader.onload = function(event) {
          const imageUrl = event.target.result;
          preview.src = imageUrl;
        };
        
        reader.readAsDataURL(file);
  } else {
    preview.innerHTML = '';
  }
});