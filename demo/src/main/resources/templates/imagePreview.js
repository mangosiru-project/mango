//이미지 한 개

(function imageView(att_zone, btn) {
    var attZone = document.getElementById(att_zone);
    var btnAtt = document.getElementById(btn);
    var sel_files = [];

    // 이미지와 체크 박스를 감싸고 있는 div 속성
    var div_style = 'display:inline-block; position:relative;'
        + 'width:200px; height:150px; margin:5px';

    // 미리보기 이미지 속성
    var img_style = 'width:100%; height:100%';

    // 이미지안에 표시되는 체크박스의 속성
    var chk_style = 'width:25px; height:25px; position:absolute; font-size:18px;'
        + 'right:0px; background-color:rgba(255,255,255,0.1)';

    btnAtt.onchange = function(e) {
        var files = e.target.files;
        sel_files = []; // 기존 파일 배열 초기화
        attZone.innerHTML = ""; // 기존 미리보기 초기화
        if (files.length > 0) {
            imageLoader(files[0]); // 첫 번째 파일만 로드
        }
    }

    // 탐색기에서 드래그앤 드롭 사용
    attZone.addEventListener('dragenter', function(e) {
        e.preventDefault();
        e.stopPropagation();
    }, false)

    attZone.addEventListener('dragover', function(e) {
        e.preventDefault();
        e.stopPropagation();
    }, false)

    attZone.addEventListener('drop', function(e) {
        var files = {};
        e.preventDefault();
        e.stopPropagation();
        var dt = e.dataTransfer;
        files = dt.files;
        if (files.length > 0) {
            sel_files = []; // 기존 파일 배열 초기화
            attZone.innerHTML = ""; // 기존 미리보기 초기화
            imageLoader(files[0]); // 첫 번째 파일만 로드
        }
    }, false)

    // 첨부된 이미지를 배열에 넣고 미리보기
    function imageLoader(file) {
        sel_files.push(file);
        var reader = new FileReader();
        reader.onload = function(e) {
            let img = document.createElement('img');
            img.setAttribute('style', img_style);
            img.src = e.target.result;
            attZone.appendChild(makeDiv(img, file));
        }
        reader.readAsDataURL(file);
    }

    // 첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환
    function makeDiv(img, file) {
        var div = document.createElement('div');
        div.setAttribute('style', div_style);

        var btn = document.createElement('input');
        btn.setAttribute('type', 'button');
        btn.setAttribute('value', 'x');
        btn.setAttribute('delFile', file.name);
        btn.setAttribute('style', chk_style);
        btn.onclick = function(ev) {
            var ele = ev.srcElement;
            var delFile = ele.getAttribute('delFile');
            sel_files = sel_files.filter(function(f) {
                return f.name !== delFile;
            });

            dt = new DataTransfer();
            for (var f of sel_files) {
                dt.items.add(f);
            }
            btnAtt.files = dt.files;
            var p = ele.parentNode;
            attZone.removeChild(p);
        }
        div.appendChild(img);
        div.appendChild(btn);
        return div;
    }
})('att_zone', 'btnAtt');
