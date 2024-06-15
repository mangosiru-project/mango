function readURL(input){
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
}

function validateForm() {
    var fileInput = document.getElementById('fileInput');
    var dateInput = document.getElementById('dateInput');
    var timeInput = document.getElementById('timeInput');
    var pickup1 = document.getElementById('pickup1');
    var pickup2 = document.getElementById('pickup2');
    var descript = document.getElementById('descript');

    if (!fileInput.files.length || !dateInput.value || !timeInput.value || (!pickup1.checked && !pickup2.checked) || !descript.value.trim()) {
        alert('모두 작성해주세요!');
    } else {
        location.href = '/orderCheck';
    }
}
