/* 토글 사이드 바 버튼 관련 */
$(".toggle-side-bar-btn").click(function () {
    console.log("토글 사이드바 버튼클릭");

    var $clicked = $(this);

    /* 만약 클릭된 버튼에 active 클래스가 있다면 */
    if ($clicked.hasClass("active")) {
        /* 사이드바를 없앤다 */
        hideLeftSideBar();
        showSelectBox();
    } else {
        /* active 클래스가 없으면 나타나게 한다 */
        showLeftSideBar();
        hideSelectBox();
    }

    /* 아이콘에 active 클래스가 없으면 active 클래스를 넣어주고 있으면 빼줌 */
    $clicked.toggleClass("active");
});

/* 왼쪽 사이드바 함수 */
function showLeftSideBar() {
    $(".left-side-bar > .menu-1 ul > li").addClass("active");
    $(".left-side-bar-box").addClass("active");
}
function hideLeftSideBar() {
    $(".left-side-bar-box").removeClass("active");
}

/* 좌측 사이드바 배경을 클릭했을때 */
$(".left-side-bar-box").click(function () {

    /* 토글 사이드바 버튼을 클릭한 효과를 만듬 */
    $(".toggle-side-bar-btn").click();
});

/* 사이드바를 클릭할때 상위요소인 배경이 같이 클릭되어서 사이드바가 들어가버리기 때문에 그것을 막음 */
$(".left-side-bar").click(function (e) {
    e.stopPropagation();
});

// 사이드바 열릴 때 select 박스 숨기기
function hideSelectBox() {
    $(".select-box").css("visibility", "hidden");
}

// 사이드바 닫힐 때 select 박스 보이기
function showSelectBox() {
    $(".select-box").css("visibility", "visible");
}

// 새그룹 모달 열기 함수
function newWorkoutGroupModalOpen() {
    var modal = document.getElementById("new-workout-group-modal");
    var modalBackground = document.getElementById("new-workout-group-background");

    modal.style.display = "block";
    modalBackground.style.display = "flex";
}

// 새그룹 모달 닫기 함수
function newWorkoutGroupModalClose() {
    var modal = document.getElementById("new-workout-group-modal");
    var modalBackground = document.getElementById("new-workout-group-background");

    modal.style.display = "none";
    modalBackground.style.display = "none";
}

// 운동부위 컬러 MAP
var partColorMap;

// 운동부위 컬러 MAP 생성
function createPartColorMap() {
    partColorMap = new Map([
        ['가슴', '#c0ed70'],
        ['등', 'moccasin'],
        ['하체', 'lightskyblue'],
        ['삼두', 'hotpink'],
        ['이두', '#c0ed70']
    ])
    return partColorMap;
}

// 뒤로가기
function goBack() {
    window.history.back();
}

/**
 * SIDEBAR EVENT) 사이드바 내 그룹 렌더링
 * */
function renderSideGrps(grpListDTO) {
    grpListDTO.grpDTOList.forEach(function(grpDTO, idx) {
        var d1_ul = document.getElementById("my-grps");

        var d2_li = document.createElement("li");

        var d3_a = document.createElement("a");
        d3_a.setAttribute("href", "#");
        d3_a.setAttribute("style", "padding-left:20px");
        d3_a.innerText = "> " + grpDTO.grpNm;

        var d3_ul = document.createElement("ul");

        var d4_li1 = document.createElement("li");

        var d5_a1 = document.createElement("a");
        d5_a1.setAttribute("href", "/grp/info?grpId="+grpDTO.grpId);
        d5_a1.setAttribute("style", "padding-left:50px");
        d5_a1.innerText = "그룹 페이지";

        var d4_li2 = document.createElement("li");
        var d5_a2 = document.createElement("a");
        d5_a2.setAttribute("href", "/grp/grpCalendar?grpId="+grpDTO.grpId);
        d5_a2.setAttribute("style", "padding-left:50px");
        d5_a2.innerText = "운동 캘린더";

        var d4_li3 = document.createElement("li");
        var d5_a3 = document.createElement("a");
        d5_a3.setAttribute("href", "/stat/grp?grpId="+grpDTO.grpId);
        d5_a3.setAttribute("style", "padding-left:50px");
        d5_a3.innerText = "운동 통계";

        d4_li1.appendChild(d5_a1);
        d4_li2.appendChild(d5_a2);
        d4_li3.appendChild(d5_a3);

        d3_ul.appendChild(d4_li1);
        d3_ul.appendChild(d4_li2);
        d3_ul.appendChild(d4_li3);

        d2_li.appendChild(d3_a);
        d2_li.appendChild(d3_ul);

        d1_ul.appendChild(d2_li);

    });
}

/**
 * SIDEBAR EVENT) 신규 그룹 생성
 * - param = [그룹명]
 * - response = [신규그룹ID]
 * - redirect = [그룹정보페이지]
 */
$(document).on("click", "#newGrpBtn", function() {
    var newGrpNm = document.getElementById("newGrpNm").value;

    if (newGrpNm == null) {
        alert("그룹명을 입력해주세요");
        return ;
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/grp/register",
        dataType:'json',
        data: {
            grpNm : newGrpNm
        },
        success: function (response) {
            alert("새 그룹이 생성되었습니다.");
            location.href="/grp/info?grpId="+response;
        },
        error: function (request, error) {
            console.log("그룹 생성 도중 오류가 발생하였습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    })
});