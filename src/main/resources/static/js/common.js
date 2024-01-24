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


// 메인 리스트 모달 열기 함수
function mainListModalOpen() {
    var modal = document.getElementById("main-list-modal");
    var modalBackground = document.getElementById("main-list-modal-background");

    modal.style.display = "block";
    modalBackground.style.display = "flex";
}

// 메인 리스트 모달 닫기 함수
function mainListModalClose() {
    var modal = document.getElementById("main-list-modal");
    var modalBackground = document.getElementById("main-list-modal-background");

    modal.style.display = "none";
    modalBackground.style.display = "none";
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
