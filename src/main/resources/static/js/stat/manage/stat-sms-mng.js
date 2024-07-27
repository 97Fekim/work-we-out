document.write("<script src='/js/common.js'></script>");

Chart.defaults.font.family = "'Stylish', sans-serif";
Chart.defaults.font.weight = "bold";

/*
* '01' : 주간 (default)
* '02' : 월간
* */
var weekMonthClfCd;

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
        url: domain+":"+port_API+"/jnal/all-method",
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

    // (1-2) 주/월간 구분코드 주간으로 초기화
    weekMonthClfCd = "01";

    // (1-3) 오늘의 N주차 조회
    var date = new Date();
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var dd = date.getDate().toString().length === 1
        ? "0"+date.getDate().toString() : date.getDate().toString();
    var curYyyyMmDd = yyyy + mm + dd;

    // (1-4) 처리현황 렌더링
    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/date/cuof-week",
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
            // 주간처리현황 렌더링 호출
            renderWeeklySuccessFailCnt(yyyyMmW);
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
        url: domain+":"+port_API+"/date/cuof-week",
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
            // (1-4) 주간처리현황 렌더링한다.
            renderWeeklySuccessFailCnt(yyyyMmW);
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
        port: "8080",
        url: domain+":"+port_API+"/date/cuof-month",
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
            // 월간 처리현황 렌더링한다.
            renderMonthlySuccessFailCnt(yyyyMm);
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
 * Event2) 좌우 화면 렌더링
 * - ◀ / ▶ 버튼이 눌리면, 현재 주/월간 체크
 * - 주/월간에 따라 YYYYMM or YYYYMMWW 를 구한다
 * - YYYYMM or YYYYMMWW를 Event3 OR Event4 에 던진다
 * */
function renderNewSuccessFailCnt(range) {
    if (weekMonthClfCd === "01") {
        var newYyyyMmW = document.getElementById("curTime").getAttribute("cuofDate").toString();
        var newRange = parseInt(range);

        $.ajax({
            type: "GET",
            url: domain+":"+port_API+"/date/new-week",
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

                // 신규 주차정보로 주간처리현황 렌더링
                renderWeeklySuccessFailCnt(newYyyyMmW);
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
            url: domain+":"+port_API+"/date/new-month",
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

                // 신규 월차정보로 월간처리현황 렌더링
                renderMonthlySuccessFailCnt(newYyyyMm);
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
 * Event3) 주간 처리현황 렌더링
 *  - YYYYMMWW -> ajax -> 처리현황 렌더링 함수 호출
 * */
function renderWeeklySuccessFailCnt(yyyyMmW) {
    // (0) 헤더 세팅
    var yy = yyyyMmW.toString().substring(2, 4);
    var mm = parseInt(yyyyMmW.toString().substring(4, 6)).toString();
    var w = yyyyMmW.toString().substring(6, 7);
    var curTime = document.getElementById("curTime");
    curTime.setAttribute("cuofDate", yyyyMmW);
    curTime.innerText = yy+"년 " + mm + "월 " + w + "주";

    // (1) ajax -> 처리현황 렌더링 함수 호출
    var param = yyyyMmW.toString();

    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/stat/manage/weekly-stat-sms-cnt",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param
        },
        success: function (successFailCnt) {
            console.log("주간 처리현황 로딩 성공");

            renderSuccessFailCnt(successFailCnt);

        },
        error: function (request, error) {
            console.log("주간 처리현황 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

}

/**
 * Event4) 월간 처리현황 렌더링
 *  - YYYYMM -> ajax -> 처리현황 렌더링 함수 호출
 * */
function renderMonthlySuccessFailCnt(yyyyMm) {
    // (0) 헤더 세팅
    var yy = yyyyMm.toString().substring(2, 4);
    var mm = parseInt(yyyyMm.toString().substring(4, 6)).toString();
    var curTime = document.getElementById("curTime");
    curTime.setAttribute("cuofDate", yyyyMm);
    curTime.innerText = yy+"년 " + mm + "월";

    // (1) ajax -> 1,2,3 통계렌더링 함수 호출
    var param = yyyyMm.toString();

    // (1-1) 렌더링
    $.ajax({
        type: "GET",
        url: domain+":"+port_API+"/stat/manage/monthly-stat-sms-cnt",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param
        },
        success: function (successFailCnt) {
            console.log("월간 처리현황 로딩 성공");
            // [1.멤버별 평균 운동일수] 렌더링
            renderSuccessFailCnt(successFailCnt);
        },
        error: function (request, error) {
            console.log("월간 처리현황 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

}

/**
 * Event5) DTO -> 처리현황 렌더링
 * */
function renderSuccessFailCnt(successFailCnt) {
    console.log(successFailCnt);

    var cnts = new Array();
    cnts.push(successFailCnt.successCnt);
    cnts.push(successFailCnt.failCnt);

    const sendMsgStsChart = $("#send-msg-sts-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(sendMsgStsChart)) {
        Chart.getChart(sendMsgStsChart)?.destroy();
    }

    new Chart(sendMsgStsChart, {
        type: "bar",
        data: {
            labels: ["성공", "실패"],
            datasets: [
                {
                    label: "처리건수",
                    data: cnts,
                    backgroundColor: ["blue", "red"], //배경색상
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
 * Event6) 문자발송 실패건 재처리
 * */
function reSendFailedSms() {

    if (weekMonthClfCd === "01") {
        var curYyyyMmW = document.getElementById("curTime").getAttribute("cuofDate").toString();

        // (1-1) 렌더링
        $.ajax({
            type: "GET",
            url: domain_batch+":"+port_API+"/stat/manage/weekly-re-send-failed-sms",
            dataType:'jsonp',
            xhrFields: {
                withCredentials: true
            },
            data: {
                yyyyMmW : curYyyyMmW
            },
            success: function (rsltCd) {
                console.log("재처리 성공. 재처리 결과 코드 : " + rsltCd);
            },
            error: function (request, error) {
                if(request.status === 200) {
                    alert("재처리 되었습니다.");
                    location.reload();
                } else {
                    console.log("재처리 중 오류가 발생했습니다.");
                    alert("code:"+request.status+"\n"
                        +"message:"+request.responseText+"\n"
                        +"error:"+error)
                }
            }
        });

    } else if (weekMonthClfCd === "02") {
        var curYyyyMm = document.getElementById("curTime").getAttribute("cuofDate").toString();

        // (1-1) 렌더링
        $.ajax({
            type: "GET",
            url: domain_batch+":"+port_API+"/stat/manage/monthly-re-send-failed-sms",
            dataType:'jsonp',
            data: {
                yyyyMm : curYyyyMm
            },
            success: function (rsltCd) {
                console.log("재처리 성공. 재처리 결과 코드 : " + rsltCd);
            },
            error: function (request, error) {
                if(request.status === 200) {
                    alert("재처리 되었습니다.");
                    location.reload();
                } else {
                    console.log("재처리 중 오류가 발생했습니다.");
                    alert("code:"+request.status+"\n"
                        +"message:"+request.responseText+"\n"
                        +"error:"+error)
                }
            }
        });

    } else {
        alert("오류가 발생했습니다. 주간/월간 건 처리만 가능합니다.");
        return ;
    }
}