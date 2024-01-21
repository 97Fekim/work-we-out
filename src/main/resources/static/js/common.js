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
    /* 메뉴바가 나올때 안에 펼쳐져 있는 메뉴들을 다 접기위해 엑티브를 없앤다 */
    $(".left-side-bar > .menu-1 ul > li.active").removeClass("active");
    $(".left-side-bar-box").addClass("active");
}
function hideLeftSideBar() {
    $(".left-side-bar-box").removeClass("active");
}

/* 메뉴 접히고 펼치기 */
$(".left-side-bar > .menu-1 ul > li").click(function (e) {

    /* 만약 클릭된 메뉴에 엑티브 클래스가 있으면 */
    if ($(this).hasClass("active")) {
        /* 클릭된 메뉴의 엑티브를 없앤다 */
        $(this).removeClass("active");
    } else {
        /* 클릭된 메뉴(지역)의 엑티브를 없앤다 */
        $(this).find(".active").removeClass("active");

        /* 클릭된 메뉴의 엑티브를 만든다 */
        $(this).addClass("active");
    }

    /* 클릭된 메뉴 안에 다른 메뉴를 클릭하면 위에있는 메뉴가 같이 클릭되는데 그것을 막아준다 */
    e.stopPropagation();
});
  
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