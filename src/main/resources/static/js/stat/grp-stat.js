document.write("<script src='/js/common.js'></script>");

/*
* Chart Configuration
* */
Chart.defaults.font.family = "'Stylish', sans-serif";
Chart.defaults.font.weight = "bold";

/*
* '01' : 주간 (default)
* '02' : 월간
* */
var weekMonthClfCd;
var curDstbPartByMbr;
var cacheGrpId = parseInt($('input[name=grpId]').val());

/**
 * Event1) 초기 렌더링.
 *  - 주/월간을 주간으로 초기화.
 *  - 오늘날짜를 서버로 던져서 YYYYMMWW 가져온다.
 *  - 가져온 YYYYMMWW를 Event3)에 던져서 화면을 렌더링한다.
 * */
$(document).ready(function(){

    // 부위별 색상 초기화
    partColorMap = createPartColorMap();
    mbrColorMap = createMbrColorMap();

    // (1-0) 전체 운동종목 로딩
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/jnal/all-method",
        async:false,
        dataType:'json',
        data: {},
        xhrFields: {
            withCredentials: true
        },
        success: function (wkoutMethodListDTO) {
            allMethods = wkoutMethodListDTO;
            console.log(allMethods);
        },
        error: function (request, error) {
            console.log("Grps Loading Fail..");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-1) 내 그룹 모두 조회하여 사이드바에 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/grp/my-all",
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

    // (1-2) 그룹명 조회하여 페이지 제목 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/grp/one-grp",
        dataType:'json',
        data: {
            grpId : cacheGrpId
        },
        xhrFields: {
            withCredentials: true
        },
        success: function (grpDTO) {
            console.log("그룹정보 로딩완료. " + grpDTO);
            var pageTitle = document.getElementById("pageTitle");
            pageTitle.innerText = grpDTO.grpNm + " 운동통계";
            console.log("페이지 제목 렌더링 완료.");
        },
        error: function (request, error) {
            console.log("Grp Loading Fail..");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-3) 주/월간 구분코드 주간으로 초기화
    weekMonthClfCd = "01";

    // (1-4) 오늘의 N주차 조회
    var date = new Date();
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var dd = date.getDate().toString().length === 1
        ? "0"+date.getDate().toString() : date.getDate().toString();
    var curYyyyMmDd = yyyy + mm + dd;

    // (1-5) 주간통계 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/date/cuof-week",
        dataType:'json',
        async: false,
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmDd : curYyyyMmDd
        },
        success: function (yyyyMmW) {
            console.log("기준 주차 Load Success");
            // 주간통계 렌더링 호출
            renderWeeklyStats(yyyyMmW);
        },
        error: function (request, error) {
            console.log("기준 주차 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

});

/**
 * Event1-1) 주간 버튼 onclick
 * */
function changeToWeekly() {
    /* 현재 주간 상태라면 아무것도 수행하지 않는다. */
    if (weekMonthClfCd === "01") {
        return ;
    }

    // 주간 상태로 변경한다.
    weekMonthClfCd = "01";

    /* 오늘을 기준으로 주간 통계를 렌더링한다. */
    var date = new Date();
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var dd = date.getDate().toString().length === 1
        ? "0"+date.getDate().toString() : date.getDate().toString();
    var curYyyyMmDd = yyyy + mm + dd;

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/date/cuof-week",
        dataType:'json',
        async: false,
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmDd : curYyyyMmDd
        },
        success: function (yyyyMmW) {
            console.log("기준 주차 Load Success");
            // (1-4) 주간통계 렌더링한다.
            renderWeeklyStats(yyyyMmW);
        },
        error: function (request, error) {
            console.log("기준 주차 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });
}

/**
 * Event1-2) 월간 버튼 onclick
 * */
function changeToMonthly() {
    /* 현재 월간 상태라면 아무것도 수행하지 않는다. */
    if (weekMonthClfCd === "02") {
        return ;
    }

    // 월간 상태로 변경한다.
    weekMonthClfCd = "02";

    /* 오늘을 기준으로 월간 통계를 렌더링한다. */
    var date = new Date();
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var dd = date.getDate().toString().length === 1
        ? "0"+date.getDate().toString() : date.getDate().toString();
    var curYyyyMmDd = yyyy + mm + dd;

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/date/cuof-month",
        dataType:'json',
        async: false,
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmDd : curYyyyMmDd
        },
        success: function (yyyyMm) {
            console.log("기준 월차 Load Success");
            console.log(yyyyMm);
            // 월간 통계 렌더링한다.
            renderMonthlyStats(yyyyMm);
        },
        error: function (request, error) {
            console.log("기준 월차 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });


}

/**
 * Event2) 좌우 통계화면 렌더링
 * - ◀ / ▶ 버튼이 눌리면, 현재 주/월간 체크
 * - 주/월간에 따라 YYYYMM or YYYYMMWW 를 구한다
 * - YYYYMM or YYYYMMWW를 Event3 OR Event4 에 던진다
 * */
function renderNewStats(range) {
    if (weekMonthClfCd === "01") {
        var newYyyyMmW = document.getElementById("curTime").getAttribute("cuofDate").toString();
        var newRange = parseInt(range);

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/date/new-week",
            dataType:'json',
            xhrFields: {
                withCredentials: true
            },
            data: {
                yyyyMmW : newYyyyMmW,
                range : newRange
            },
            success: function (newYyyyMmW) {
                console.log("new YyyyMmW = " + newYyyyMmW);

                // 신규 주차정보로 주간통계 렌더링
                renderWeeklyStats(newYyyyMmW, weekMonthClfCd);
            },
            error: function (request, error) {
                console.log("새로운 주차 로딩중 오류가 발생했습니다.");
                alert("code:"+request.status+"\n"
                    +"message:"+request.responseText+"\n"
                    +"error:"+error)
            }
        });

    } else if (weekMonthClfCd === "02") {
        var newYyyyMm = document.getElementById("curTime").getAttribute("cuofDate").toString();
        var newRange = parseInt(range);

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/date/new-month",
            dataType:'json',
            xhrFields: {
                withCredentials: true
            },
            data: {
                yyyyMm : newYyyyMm,
                range : newRange
            },
            success: function (newYyyyMm) {
                console.log("new YyyyMm = " + newYyyyMm);

                // 신규 주차정보로 주간통계 렌더링
                renderMonthlyStats(newYyyyMm, weekMonthClfCd);
            },
            error: function (request, error) {
                console.log("새로운 월차 로딩중 오류가 발생했습니다.");
                alert("code:"+request.status+"\n"
                    +"message:"+request.responseText+"\n"
                    +"error:"+error)
            }
        });

    } else {
        alert("오류가 발생했습니다. 주간/월간 통계만 조회 가능합니다.");
        return ;
    }
}

/**
 * Event3) 주간 통계 렌더링
 *  - YYYYMMWW -> ajax -> 1.2.3 통계렌더링 함수 호출
 * */
function renderWeeklyStats(yyyyMmW) {
    // (0) 헤더 세팅
    var yy = yyyyMmW.toString().substring(2, 4);
    var mm = parseInt(yyyyMmW.toString().substring(4, 6)).toString();
    var w = yyyyMmW.toString().substring(6, 7);
    var curTime = document.getElementById("curTime");
    curTime.setAttribute("cuofDate", yyyyMmW);
    curTime.innerText = yy+"년 " + mm + "월 " + w + "주";

    // (1) ajax -> 1,2,3 통계렌더링 함수 호출
    var param = yyyyMmW.toString();

    // (1-1) [1.멤버별 평균 운동일수] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-weekly-avg-days",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param,
            grpId : cacheGrpId
        },
        success: function (avgDays) {
            console.log("[1. 멤버별 평균 운동일수] 로딩 성공");
            // [1.멤버별 평균 운동일수] 렌더링
            renderAvgDays(avgDays);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-2) [2.우리 그룹의 운동부위 분포] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-weekly-dstb-part",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param,
            grpId : cacheGrpId
        },
        success: function (dstbPart) {
            console.log("[2.우리 그룹의 운동부위 분포] 로딩 성공");
            // [2.우리 그룹의 운동부위 분포]
            renderDstbPart(dstbPart);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-3) [3.멤버별 운동부위 분포] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-weekly-dstb-part-by-mbr",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param,
            grpId : cacheGrpId
        },
        success: function (dstbPartByMbr) {
            console.log("[3.멤버별 운동부위 분포] 로딩 성공");
            // [3.멤버별 운동부위 분포]
            renderDstbPartByMbr(dstbPartByMbr);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

}

/**
 * Event4) 월간 통계 렌더링
 *  - YYYYMM -> ajax -> 1.2.3 통계렌더링 함수 호출
 * */
function renderMonthlyStats(yyyyMm) {
    // (0) 헤더 세팅
    var yy = yyyyMm.toString().substring(2, 4);
    var mm = parseInt(yyyyMm.toString().substring(4, 6)).toString();
    var curTime = document.getElementById("curTime");
    curTime.setAttribute("cuofDate", yyyyMm);
    curTime.innerText = yy+"년 " + mm + "월";

    // (1) ajax -> 1,2,3 통계렌더링 함수 호출
    var param = yyyyMm.toString();

    // (1-1) [1.멤버별 평균 운동일수] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-monthly-avg-days",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param,
            grpId : cacheGrpId
        },
        success: function (avgDays) {
            console.log("[1. 멤버별 평균 운동일수] 로딩 성공");
            // [1.멤버별 평균 운동일수] 렌더링
            renderAvgDays(avgDays);
        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-2) [2.우리 그룹의 운동부위 분포] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-monthly-dstb-part",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param,
            grpId : cacheGrpId
        },
        success: function (dstbPart) {
            console.log("[2.우리 그룹의 운동부위 분포] 로딩 성공");
            // [2.우리 그룹의 운동부위 분포]
            renderDstbPart(dstbPart);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-3) [3.멤버별 운동부위 분포] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/grp-monthly-dstb-part-by-mbr",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param,
            grpId : cacheGrpId
        },
        success: function (dstbPartByMbr) {
            console.log("[3.멤버별 운동부위 분포] 로딩 성공");
            // [3.멤버별 운동부위 분포]
            renderDstbPartByMbr(dstbPartByMbr);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });
}

/**
 * Event5) DTO -> 1.통계 렌더링
 * */
function renderAvgDays(avgDays) {
    console.log(avgDays);

    var mbrNms = new Array();
    var days = new Array();
    var backgroundColors = new Array();

    avgDays.mbrWkoutDaysCntDTOList.forEach(function(mbrCnt) {
        mbrNms.push(mbrCnt.mbrNm.toString());
        days.push(parseInt(mbrCnt.wkoutDaysCnt === null ? "0" : mbrCnt.wkoutDaysCnt));
        backgroundColors.push(mbrColorMap.get(mbrCnt.mbrId));
    })

    const avgDaysChart = $("#avg-days-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(avgDaysChart)) {
        Chart.getChart(avgDaysChart)?.destroy();
    }

    const createAvgDaysChart = new Chart(avgDaysChart, {
        type: "bar",
        data: {
            labels: mbrNms,
            datasets: [
                {
                    label: "일수",
                    data: days,
                    backgroundColor: backgroundColors, //배경색상
                    hoverBackgroundColor: ["#CBCE91"], //hover할때 색상변경
                },
            ],
        },
        options: {
            maintainAspectRatio: false, //그래프의 비율 유지
            plugins: {
                legend: {
                    display: true,
                },
                label: {
                    font: {},
                },
            },
            indexAxis: "y", //수평차트 만들기
        },
    });
}

/**
 * Event6) DTO -> 2.통계 렌더링
 * */
function renderDstbPart(dstbPartDTO) {
    console.log(dstbPartDTO);

    var parNms = new Array();
    var totalSetss = new Array();
    var backgroudColors = new Array();

    dstbPartDTO.targetPartTotalSetDTOList.forEach(function(partSets) {

        parNms.push(partSets.targetPart.toString());
        totalSetss.push(parseInt(partSets.totalSets));
        backgroudColors.push(partColorMap.get(partSets.targetPart));

    })

    const dstbPart = $("#dstb-part-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(dstbPart)) {
        Chart.getChart(dstbPart)?.destroy();
    }

    const createDstbPart = new Chart(dstbPart, {
        type: "pie",
        data: {
            labels: parNms,
            datasets: [
                {
                    data: totalSetss,
                    backgroundColor: backgroudColors, //배경색상
                    hoverBackgroundColor: ["#CBCE91"], //hover할때 색상변경
                },
            ],
        },
        options: {
            maintainAspectRatio: false, //그래프의 비율 유지
            plugins: {
                legend: {
                    display: true,
                },
                label: {
                    font: {},
                },
            },
        },
    });
}

/**
 * Event7) DTO -> 3.통계 中 select-option 만 렌더링
 * */
function renderDstbPartByMbr(dstbPartByMbr) {
    console.log(dstbPartByMbr);

    /* 글로벌 변수에 저장 */
    curDstbPartByMbr =dstbPartByMbr;

    /* select-option 초기화 한다. */
    var selectMethod = document.getElementById("select-method");

    while (selectMethod.hasChildNodes()) {
        selectMethod.removeChild(selectMethod.firstChild);
    }

    var firstOption = document.createElement("option");
    firstOption.innerText = "멤버 선택";
    firstOption.setAttribute("methodId", "null");
    selectMethod.appendChild(firstOption);

    dstbPartByMbr.mbrTargetPartTotalSetsDTOList.forEach(function(mbrDTO) {
        var option = document.createElement("option");

        option.setAttribute("mbrId", mbrDTO.mbrId);
        option.innerText = mbrDTO.mbrNm;

        selectMethod.appendChild(option);
    });


    /* 빈 차트 공간을 생성한다. */
    const dstbPartByMbrChart = $("#dstb-part-by-mbr-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(dstbPartByMbrChart)) {
        Chart.getChart(dstbPartByMbrChart)?.destroy();
    }
    const createDstbPartByMbrChart = new Chart(dstbPartByMbrChart, {
        type: "pie",
        data: {
            labels: [],
            datasets: [
                {
                    data: [],
                    backgroundColor: [], //배경색상
                    hoverBackgroundColor: ["#CBCE91"], //hover할때 색상변경
                },
            ],
        },
        options: {
            maintainAspectRatio: false, //그래프의 비율 유지
            plugins: {
                legend: {
                    display: true,
                },
                label: {
                    font: {},
                },
            },
        },
    });

}

/**
 * Event7-1) 3.통계 option 선택시, 통계 컨텐츠 렌더링
 *  - option선택됨
 * */
function renderDstbPartByMbrSelectStat() {

    var selectMethod = document.getElementById("select-method");

    var curMbrId = selectMethod.options[selectMethod.selectedIndex].getAttribute("mbrId");

    if (curMbrId === "null") {
        return ;
    }

    var curMbrDTO;
    var partNms = new Array();
    var totalSetss = new Array();
    var backgroundColors = new Array();

    curDstbPartByMbr.mbrTargetPartTotalSetsDTOList.forEach(function(mbrDTO){
        if (mbrDTO.mbrId === parseInt(curMbrId)) {
            curMbrDTO = mbrDTO;
        }
    });

    curMbrDTO.targetPartTotalSetsDTO.targetPartTotalSetDTOList.forEach(function(el) {
        partNms.push(el.targetPart.toString());
        totalSetss.push(el.totalSets);
        backgroundColors.push(partColorMap.get(el.targetPart));
    })

    const dstbPartByMbrChart = $("#dstb-part-by-mbr-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(dstbPartByMbrChart)) {
        Chart.getChart(dstbPartByMbrChart)?.destroy();
    }
    const createDstbPartByMbrChart = new Chart(dstbPartByMbrChart, {
        type: "pie",
        data: {
            labels: partNms,
            datasets: [
                {
                    data: totalSetss,
                    backgroundColor: backgroundColors, //배경색상
                    hoverBackgroundColor: ["#CBCE91"], //hover할때 색상변경
                },
            ],
        },
        options: {
            maintainAspectRatio: false, //그래프의 비율 유지
            plugins: {
                legend: {
                    display: true,
                },
                label: {
                    font: {},
                },
            },
        },
    });

}
