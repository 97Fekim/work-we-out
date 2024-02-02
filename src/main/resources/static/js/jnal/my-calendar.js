document.write("<script src='/js/common.js'></script>");

/**
 * # 초기화면로드
 * */
$(document).ready(function(){

    partColorMap = createPartColorMap();

    // (1-1) 내 그룹 모두 조회하여 사이드바에 렌더링
    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/grp/my-all",
        dataType:'json',
        data: {},
        xhrFields: {
            withCredentials: true
        },
        success: function (response) {
            renderSideGrps(response);
        },
        error: function (request, error) {
            console.log("Grps Loading Fail..");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-2) 현재MM 메인캘린더 렌더링
    var today = new Date();
    renderCalendar(today);

});

/**
 * CALENDAR EVENT1) MM +-1 월 이동처리
 *  - param = [현재 MM 으로부터 이동하려는 range]
 * */
function renderNewMMCalendar(range) {
    console.log("renderNewMMCalendar called..");

    // (1) 당월 날짜
    var calendarYYYYMM = document.getElementById("YYYYMM");
    var curDate = new Date(calendarYYYYMM.getAttribute("date"));

    // (2) 당월+-1 월 날짜
    var newDate = new Date(
        curDate.getFullYear(),
        curDate.getMonth() + range,
        15
    );

    console.log("range = " + range);
    console.log("newDate = " + newDate);

    // (3) 이전 캘린더 삭제
    var prevCalendarContent = document.getElementById("calendar-content");
    while (prevCalendarContent.hasChildNodes()) {
        prevCalendarContent.removeChild(prevCalendarContent.firstChild);
    }

    // (4) MM월 메인캘린더 렌더링 호출
    renderCalendar(newDate);
}

/**
 * CALENDAR Event2) MM 월 메인캘린더 렌더링
 *  - param = [Date]
 * */
function renderCalendar(date) {
    // 헤더렌더링
    var calendarYYYYMM = document.getElementById("YYYYMM");
    calendarYYYYMM.setAttribute("date", date);
    calendarYYYYMM.innerText = date.getFullYear() + "년 " + (date.getMonth()+1) + "월";

    // 컨텐츠렌더링
    var year = date.getFullYear();
    var month = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var curYyyyMm = year + month;

    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/jnal/calendar",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : curYyyyMm
        },
        success: function (oneMonthCalendarDTO) {
            console.log("Calendar Load Success");
            renderCalendarContents(oneMonthCalendarDTO);
        },
        error: function (request, error) {
            console.log("캘린더 로딩 중 오류가 발생하였습니다..\n");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    })
}

/**
 * CALENDAR Event3) MM 월 메인캘린더 컨텐츠 렌더링
 *  - param = [OneMonthCalendarDTO]
 * */
function renderCalendarContents(oneMonthCalendarDTO) {

    console.log(oneMonthCalendarDTO);

    var curMM = new Date(document.getElementById("YYYYMM").getAttribute("date")).getMonth()+1;

    // Depth-1
    var d1_calendarContent = document.getElementById("calendar-content");

    oneMonthCalendarDTO.oneDayCalendarDTOList.forEach(function(oneDayCalendarDTO, idx) {

        // Depth-2
        var d2_dayComponent = document.createElement("div");

        d2_dayComponent.setAttribute("id", "day");
        d2_dayComponent.setAttribute("yyyyMmDd", oneDayCalendarDTO.yyyy+oneDayCalendarDTO.mm+oneDayCalendarDTO.dd);
        d2_dayComponent.setAttribute("onclick", "mainListModalOpen(this);")

        if (parseInt(oneDayCalendarDTO.mm) !== curMM) {
            d2_dayComponent.setAttribute("class", "border border-white-600 rounded text-left text-top");
        } else if (oneDayCalendarDTO.dayOfWeekClsfCd === "SUN") {
            d2_dayComponent.setAttribute("class", "border border-red-600 rounded text-left");
        } else if (oneDayCalendarDTO.dayOfWeekClsfCd === "SAT") {
            d2_dayComponent.setAttribute("class", "border border-blue-600 rounded text-left");
        } else {
            d2_dayComponent.setAttribute("class", "border border-gray-600 rounded text-left");
        }

        // Depth-3
        var d3_ddComponent = document.createElement("span");
        d3_ddComponent.innerText = parseInt(oneDayCalendarDTO.dd).toString();
        d3_ddComponent.setAttribute("class", "text-sm");
        if (oneDayCalendarDTO.dayOfWeekClsfCd === "SUN") {
            d3_ddComponent.setAttribute("style", "padding-left:5px; color:red");
        } else if (oneDayCalendarDTO.dayOfWeekClsfCd === "SAT") {
            d3_ddComponent.setAttribute("style", "padding-left:5px; color:blue");
        } else {
            d3_ddComponent.setAttribute("style", "padding-left:5px");
        }

        var d3_partsComponent = document.createElement("div");

        // Depth-3
        d3_partsComponent.setAttribute("style", "padding-left:3px;");
        oneDayCalendarDTO.parts.forEach(function(part, idx) {
            // Depth-4
            var d4_part = document.createElement("span");
            d4_part.innerText = part;
            d4_part.setAttribute("class", "workout-part-box");
            d4_part.setAttribute("style", "margin-right:1px; background-color:"+partColorMap.get(part)); // TODO:색조절

            d3_partsComponent.appendChild(d4_part);
        });

        // MERGE
        d2_dayComponent.appendChild(d3_ddComponent);
        d2_dayComponent.appendChild(d3_partsComponent);
        d1_calendarContent.appendChild(d2_dayComponent);
    });

}

/**
 * CALENDAR Event4) DD 일 운동일지 리스트 모달 OPEN
 *  - param = [YYYYMMDD]
 * */
function mainListModalOpen(dayComponent) {
    // (1) 모달 레이아웃 OPEN
    var modal = document.getElementById("main-list-modal");
    var modalBackground = document.getElementById("main-list-modal-background");
    modal.style.display = "block";
    modalBackground.style.display = "flex";

    // (2) 운동일지 렌더링
    var curYyyyMmDd = dayComponent.getAttribute("yyyyMmDd");
    var yyyy = curYyyyMmDd.substring(0, 4);
    var mm = curYyyyMmDd.substring(4, 6);
    var dd = curYyyyMmDd.substring(6, 8);

    //  (2-1) 모달 헤더 렌더링
    var mainListModalYYYYMMDD = document.getElementById("main-list-modal-YYYYMMDD");
    mainListModalYYYYMMDD.innerText = yyyy + "년 " + mm + "월 " + dd + "일";

    // (2-2) 모달 컨텐츠 렌더링
    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/jnal/one-day-jnals",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmDd : curYyyyMmDd
        },
        success: function (oneDayJnalsDTO) {
            console.log("oneDayJnals Load Success");

            renderMainListModalContent(oneDayJnalsDTO);
        },
        error: function (request, error) {
            console.log("운동일지 로딩 중 오류가 발생하였습니다..\n");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    })

}

/**
 * CALENDAR Event5) DD 일 운동일지 리스트 모달 컨텐츠 렌더링
 *  - param = [OneDayJnals]
 * */
function renderMainListModalContent(oneDayJnalsDTO) {
    var mainListModalContent = document.getElementById("main-list-modal-content");

    oneDayJnalsDTO.wkoutJnalDTO
        .forEach(function(wkoutJnal, idx) {

            var d1_frame = document.createElement("div");
            d1_frame.setAttribute("class", "extra-btn bg-sky-700 p-4 rounded-lg flex items-center justify-between");
            d1_frame.setAttribute("style", "margin:3px; display:block; background-color:lemonchiffon");
            d1_frame.setAttribute("onclick", "location.href='/jnal/read-one-day?yyyyMmDd="+wkoutJnal.yyyy+wkoutJnal.mm+wkoutJnal.dd+"'");

            var d2_userInfo = document.createElement("div");
            d2_userInfo.setAttribute("class", "flex items-center");
            d2_userInfo.setAttribute("style", "margin:3px");

            var d3_userImg = document.createElement("img");
            d3_userImg.setAttribute("src", "../img/" + wkoutJnal.profImgPath);
            d3_userImg.setAttribute("alt", "");
            d3_userImg.setAttribute("class", "rounded-full mr-3");
            d3_userImg.setAttribute("style", "width:25px; height:25px");
            var d3_userNm = document.createElement("span");
            d3_userNm.innerText = wkoutJnal.mbrNm;

            var d2_partsFrame = document.createElement("div");
            d2_partsFrame.setAttribute("class", "flex items-center");
            d2_partsFrame.setAttribute("style", "margin:3px");
            wkoutJnal.wkoutJnalMethodDTOList
                .forEach(function(method){
                    var d3_part = document.createElement("div");
                    d3_part.setAttribute("class", "part bg-green-500 text-white px-3 py-1 rounded-lg mr-2");
                    d3_part.setAttribute("style", "margin:7px; "+
                        "background-color:"+partColorMap.get(method.targetPart)+";");
                    d3_part.innerText = method.targetPart;
                    d2_partsFrame.appendChild(d3_part);
                })

            // MERGE
            d2_userInfo.appendChild(d3_userImg);
            d2_userInfo.appendChild(d3_userNm);
            d1_frame.appendChild(d2_userInfo);
            d1_frame.appendChild(d2_partsFrame);
            mainListModalContent.appendChild(d1_frame);
        });
}

/**
 * CALENDAR Event6) DD 일 운동일지 리스트 모달 CLOSE
 *  - param = []
 * */
function mainListModalClose() {
    // (2) 이전 컨텐츠 삭제
    var prevMainListModalContent = document.getElementById("main-list-modal-content");
    while (prevMainListModalContent.hasChildNodes()) {
        prevMainListModalContent.removeChild(prevMainListModalContent.firstChild);
    }

    // (2) 모달 닫기
    var modal = document.getElementById("main-list-modal");
    var modalBackground = document.getElementById("main-list-modal-background");
    modal.style.display = "none";
    modalBackground.style.display = "none";
}