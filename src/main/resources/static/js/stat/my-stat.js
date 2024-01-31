document.write("<script src='/js/common.js'></script>");

/*
* '01' : 주간 (default)
* '02' : 월간
* */
var weekMonthClfCd;
var curMethodMaxWeis;

/*
* Chart Configuration
* */
Chart.defaults.font.family = "'Stylish', sans-serif";
Chart.defaults.font.weight = "bold";

/**
 * Event1) 초기 렌더링.
 *  - 주/월간을 주간으로 초기화.
 *  - 오늘날짜를 서버로 던져서 YYYYMMWW 가져온다.
 *  - 가져온 YYYYMMWW를 Event3)에 던져서 화면을 렌더링한다.
 * */
$(document).ready(function(){

    // 부위별 색상 초기화
    partColorMap = createPartColorMap();

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

    // (1-1) [1.운동부위별 총 세트수] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-weekly-total-sets",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param
        },
        success: function (targetPartTotalSets) {
            console.log("[1. 운동부위별 총 세트수] 로딩 성공");
            // [1.운동부위별 총 세트수] 렌더링
            renderTargetPartTotalSets(targetPartTotalSets);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-2) [2.운동 종목별 중량 증감] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-weekly-method-wei-incs",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param
        },
        success: function (methodWeiIncs) {
            console.log("[2.운동 종목별 중량 증감] 로딩 성공");
            // [2.운동 종목별 중량 증감]
            renderMethodWeiIncs(methodWeiIncs);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-3) [3.운동 종목별 중량 상승 추이] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-weekly-method-week-max-weis",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmW : param
        },
        success: function (methodMaxWeis) {
            console.log("[3.운동 종목별 중량 상승 추이] 로딩 성공");
            // [3.운동 종목별 중량 상승 추이]
            renderMethodMaxWeisSelect(methodMaxWeis, weekMonthClfCd);

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

    // (1-1) [1.운동부위별 총 세트수] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-monthly-total-sets",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param
        },
        success: function (targetPartTotalSets) {
            console.log("[1. 운동부위별 총 세트수] 로딩 성공");
            // [1.운동부위별 총 세트수] 렌더링
            renderTargetPartTotalSets(targetPartTotalSets);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-2) [2.운동 종목별 중량 증감] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-monthly-method-wei-incs",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param
        },
        success: function (methodWeiIncs) {
            console.log("[2.운동 종목별 중량 증감] 로딩 성공");
            // [2.운동 종목별 중량 증감]
            renderMethodWeiIncs(methodWeiIncs);

        },
        error: function (request, error) {
            console.log("통계자료 로딩중 오류가 발생했습니다.");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });

    // (1-3) [3.운동 종목별 중량 상승 추이] 렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/stat/my-monthly-method-week-max-weis",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMm : param
        },
        success: function (methodMaxWeis) {
            console.log("[3.운동 종목별 중량 상승 추이] 로딩 성공");
            // [3.운동 종목별 중량 상승 추이]
            renderMethodMaxWeisSelect(methodMaxWeis, weekMonthClfCd);

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
function renderTargetPartTotalSets(targetPartTotalSets) {
    console.log(targetPartTotalSets);

    var labels = new Array();
    var sets = new Array();
    var backgroundColors = new Array();

    targetPartTotalSets.targetPartTotalSetDTOList.forEach(function(partTotalSet) {
        labels.push(partTotalSet.targetPart.toString());
        sets.push(parseInt(partTotalSet.totalSets === null ? "0" : partTotalSet.totalSets));
        backgroundColors.push(partColorMap.get(partTotalSet.targetPart.toString()));
    })

    const totalSetChart = $("#total-set-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(totalSetChart)) {
        Chart.getChart(totalSetChart)?.destroy();
    }

    const createTotalSetChart = new Chart(totalSetChart, {
        type: "bar",
        data: {
            labels: labels,
            datasets: [
                {
                    label: "세트수",
                    data: sets,
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
function renderMethodWeiIncs(methodWeiIncs) {
    console.log(methodWeiIncs);

    var methodNms = new Array();
    var weiIncreases = new Array();
    var backgroudColors = new Array();

    methodWeiIncs.methodWeiIncDTOList.forEach(function(methodWeiInc) {

        methodNms.push(methodWeiInc.methodNm.toString());
        weiIncreases.push(parseInt(methodWeiInc.weiIcrease));
        backgroudColors.push(parseInt(methodWeiInc.weiIcrease) >= 0 ? "red" : "blue");

    })

    const workoutPartWeiIncChart = $("#workout-part-wei-inc-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(workoutPartWeiIncChart)) {
        Chart.getChart(workoutPartWeiIncChart)?.destroy();
    }

    const createWorkoutPartWeiIncChart = new Chart(workoutPartWeiIncChart, {
        type: "bar",
        data: {
            labels: methodNms,
            datasets: [
                {
                    label: "kg",
                    data: weiIncreases,
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
            indexAxis: "y", //수평차트 만들기
        },
    });
}

/**
 * Event7) DTO -> 3.통계 中 select-option 만 렌더링
 * */
function renderMethodMaxWeisSelect(methodMaxWeis, clsfCd) {
    console.log(methodMaxWeis);

    /* 글로벌 변수에 저장 */
    curMethodMaxWeis =methodMaxWeis;

    /* select-option 초기화 한다. */
    var selectMethod = document.getElementById("select-method");

    while (selectMethod.hasChildNodes()) {
        selectMethod.removeChild(selectMethod.firstChild);
    }

    var firstOption = document.createElement("option");
    firstOption.innerText = "운동종목 선택";
    firstOption.setAttribute("methodId", "null");
    selectMethod.appendChild(firstOption);

    if (clsfCd === "01") {
        methodMaxWeis.methodWeekMaxWeiDTOList.forEach(function(methodWei) {
            var option = document.createElement("option");

            option.setAttribute("methodId", methodWei.methodId);
            option.innerText = methodWei.methodNm;

            selectMethod.appendChild(option);
        });
    } else if (clsfCd === "02") {
        methodMaxWeis.methodMonthMaxWeiDTOList.forEach(function(methodWei) {
            var option = document.createElement("option");

            option.setAttribute("methodId", methodWei.methodId);
            option.innerText = methodWei.methodNm;

            selectMethod.appendChild(option);
        });
    } else {
        alert("오류가 발생했습니다. 주간/월간 통계만 조회 가능합니다.");
        return ;
    }

    /* 빈 차트 공간을 생성한다. */
    const workoutPartWeiIncTrendChart = $("#workout-part-wei-inc-trend-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(workoutPartWeiIncTrendChart)) {
        Chart.getChart(workoutPartWeiIncTrendChart)?.destroy();
    }
    const createWorkoutPartWeiIncTrendChart = new Chart(
        workoutPartWeiIncTrendChart,
        {
            type: "bar",
            data: {
                labels: [],
                datasets: [
                    {
                        label: "kg",
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
                indexAxis: "x", //수평차트 만들기
            },
        }
    );

}

/**
 * Event7-1) 3.통계 option 선택시, 통계 컨텐츠 렌더링
 *  - option선택됨
 *  - 선택된 option의 methodId로 4번의 반복하며 3.통계 렌더링
 * */
function renderMethodWeekMaxWeisSelectStat() {

    var selectMethod = document.getElementById("select-method");

    var curMethodId = selectMethod.options[selectMethod.selectedIndex].getAttribute("methodId");

    if (curMethodId === "null") {
        return ;
    }

    var curMaxWei;
    var dates = new Array();
    var weis = new Array();
    var backgroundColors = new Array();

    if (weekMonthClfCd === "01") {          /* 현재 주간 상태일 경우 */
        curMethodMaxWeis.methodWeekMaxWeiDTOList.forEach(function(weekMaxWei){
            if (weekMaxWei.methodId === parseInt(curMethodId)) {
                curMaxWei = weekMaxWei;
            }
        });

        curMaxWei.weekMaxWeiDTOList.forEach(function(el){
            dates.push(parseInt(el.coufMm) + "월" + el.cuofWeek + "주") ;
            weis.push(el.maxWeight === null ? 0 : parseInt(el.maxWeight));
            backgroundColors.push("lightskyblue");
        })
    } else if (weekMonthClfCd === "02") {   /* 현재 월간 상태일 경우 */
        curMethodMaxWeis.methodMonthMaxWeiDTOList.forEach(function(monthMaxWei){
            if (monthMaxWei.methodId === parseInt(curMethodId)) {
                curMaxWei = monthMaxWei;
            }
        });

        curMaxWei.monthMaxWeiDTOList.forEach(function(el){
            dates.push(parseInt(el.coufMm) + "월") ;
            weis.push(el.maxWeight === null ? 0 : parseInt(el.maxWeight));
            backgroundColors.push("lightskyblue");
        })
    } else {
        alert("오류가 발생했습니다. 주간/월간 통계만 조회 가능합니다.");
        return ;
    }

    const workoutPartWeiIncTrendChart = $("#workout-part-wei-inc-trend-chart");

    // 이미 차트가 존재한다면 제거한다.
    if (Chart.getChart(workoutPartWeiIncTrendChart)) {
        Chart.getChart(workoutPartWeiIncTrendChart)?.destroy();
    }
    const createWorkoutPartWeiIncTrendChart = new Chart(
        workoutPartWeiIncTrendChart,
        {
            type: "bar",
            data: {
                labels: dates,
                datasets: [
                    {
                        label: "kg",
                        data: weis,
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
                indexAxis: "x", //수평차트 만들기
            },
        }
    );

}
