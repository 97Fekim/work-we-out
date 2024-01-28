document.write("<script src='/js/common.js'></script>");

/**
 * # 초기화면로드
 * */
$(document).ready(function(){
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

    // (1-2) Model로 넘어온 YYYYMMDD 운동일지 렌더링
    var curYyyyMmDd = $('input[name=curYyyyMmDd]').val();
    renderJnals(curYyyyMmDd);

});

/**
 * JNALS Event1) YYYYMMDD 일 운동일지 리스트 렌더링
 *  - param = [YYYYMMDD]
 * */
function renderJnals(curYyyyMmDd) {
    // (1) 헤더렌더링
    var yyyy = curYyyyMmDd.substring(0, 4);
    var mm = parseInt(curYyyyMmDd.substring(4, 6)).toString();
    var dd = parseInt(curYyyyMmDd.substring(6, 8)).toString();

    var jnalsYYYYMMDD = document.getElementById("YYYYMMDD");
    jnalsYYYYMMDD.setAttribute("yyyyMmDd", curYyyyMmDd);
    jnalsYYYYMMDD.innerText = yyyy+"년 "+mm+"월 "+dd+"일";

    // (1) 컨텐츠렌더링
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/jnal/one-day-jnals",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            yyyyMmDd : curYyyyMmDd
        },
        success: function (oneDayJnalsDTO) {
            console.log("oneDayJnals Load Success");

            renderJnalsContent(oneDayJnalsDTO);
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
 * JNALS Event2) YYYYMMDD 일 운동일지 컨텐츠 렌더링
 *  - param = [OneMonthCalendarDTO]
 * */
function renderJnalsContent(oneDayJnalsDTO) {
    console.log(oneDayJnalsDTO);

    var d1_jnalsContent = document.getElementById("jnals-content");

    oneDayJnalsDTO.wkoutJnalDTO.forEach(function(wkoutJnal, idx) {

        var d2_jnalComponent = document.createElement("div");
        d2_jnalComponent.setAttribute("class", "block justify-between items-center p-1");
        d2_jnalComponent.setAttribute("style", "border-color: lightgrey; border-width: 7px; border-radius: 7px");

        var d3_commentLabel = document.createElement("div");
        d3_commentLabel.setAttribute("class", "flex justify-between items-center p-2");
        var d4_commentLabelText = document.createElement("span");
        d4_commentLabelText.setAttribute("class", "text-1xl font-semibold");
        d4_commentLabelText.innerText = "▷ 운동 한줄평";
        d3_commentLabel.appendChild(d4_commentLabelText);  // d3 운동한줄평 Label 완료

        var d3_commentContent = document.createElement("div");
        d3_commentContent.setAttribute("class", "flex justify-between p-2");
        d3_commentContent.setAttribute("style", "padding-left: 35px; padding-right: 35px");
        var d4_commentContentText = document.createElement("input");
        d4_commentContentText.setAttribute("type", "text");
        d4_commentContentText.setAttribute("readonly", "true");
        d4_commentContentText.setAttribute("class", "bottom-border");
        d4_commentContentText.setAttribute("style", "width: 100%");
        d4_commentContentText.setAttribute("value", wkoutJnal.comments);
        d3_commentContent.appendChild(d4_commentContentText);  // d3 운동한줄평 Content 완료

        var d3_wkoutsLabel = document.createElement("div");
        d3_wkoutsLabel.setAttribute("class", "flex justify-between items-center p-2");
        var d4_wkoutsLabelText = document.createElement("span");
        d4_wkoutsLabelText.setAttribute("class", "text-1xl font-semibold");
        d4_wkoutsLabelText.innerText = "▷ 오늘의 운동";
        d3_wkoutsLabel.appendChild(d4_wkoutsLabelText);  // d3 오늘의 운동 Label 완료

        // 1차 MERGE
        d2_jnalComponent.appendChild(d3_commentLabel);
        d2_jnalComponent.appendChild(d3_commentContent);
        d2_jnalComponent.appendChild(d3_wkoutsLabel);

        wkoutJnal.wkoutJnalMethodDTOList.forEach(function(method, idx){

            var d3_method = document.createElement("div");
            d3_method.setAttribute("class", "wkout-method block justify-between items-center p-1");
            d3_method.setAttribute("seq", idx);

            var d4_methodCombo = document.createElement("div");
            d4_methodCombo.setAttribute("class", "select-box flex justify-between items-center p-1");
            d4_methodCombo.setAttribute("style", "padding-left: 25px");
            var d5_methodCombo = document.createElement("div");
            d5_methodCombo.setAttribute("class", "inline-flex relative");
            var d6_methodCombo = document.createElement("span");
            d6_methodCombo.setAttribute("class", "appearance-none bg-white border border-gray-300 rounded-md px-3 py-1 pr-6 focus:outline-none focus:border-blue-500");
            var d7_methodCombo = document.createElement("option");
            d7_methodCombo.innerText = method.methodNm;

            d6_methodCombo.appendChild(d7_methodCombo);
            d5_methodCombo.appendChild(d6_methodCombo);
            d4_methodCombo.appendChild(d5_methodCombo);

            var d4_wkoutsDetail = document.createElement("div");
            d4_wkoutsDetail.setAttribute("class", "flex justify-between p-1");
            d4_wkoutsDetail.setAttribute("style", "padding-left: 35px");
            var d5_wkoutsDetail = document.createElement("div");
            var d6_wkoutsDetailWei = document.createElement("input");
            d6_wkoutsDetailWei.setAttribute("type", "number");
            d6_wkoutsDetailWei.setAttribute("readonly", "true");
            d6_wkoutsDetailWei.setAttribute("class", "bottom-border");
            d6_wkoutsDetailWei.setAttribute("style", "width: 8%; text-align: center");
            d6_wkoutsDetailWei.setAttribute("value", method.weight);
            var d6_wkoutsDetailWeiTail = document.createElement("span");
            d6_wkoutsDetailWeiTail.innerText = "kg";

            var d6_wkoutsDetailSet = document.createElement("input");
            d6_wkoutsDetailSet.setAttribute("type", "number");
            d6_wkoutsDetailSet.setAttribute("readonly", "true");
            d6_wkoutsDetailSet.setAttribute("class", "bottom-border");
            d6_wkoutsDetailSet.setAttribute("style", "width: 8%; text-align: center");
            d6_wkoutsDetailSet.setAttribute("value", method.sets);
            var d6_wkoutsDetailSetTail = document.createElement("span");
            d6_wkoutsDetailSetTail.innerText = "set";

            var d6_wkoutsDetailRep = document.createElement("input");
            d6_wkoutsDetailRep.setAttribute("type", "number");
            d6_wkoutsDetailRep.setAttribute("readonly", "true");
            d6_wkoutsDetailRep.setAttribute("class", "bottom-border");
            d6_wkoutsDetailRep.setAttribute("style", "width: 8%; text-align: center");
            d6_wkoutsDetailRep.setAttribute("value", method.reps);
            var d6_wkoutsDetailRepTail = document.createElement("span");
            d6_wkoutsDetailRepTail.innerText = "reps";

            var d6_wkoutsDetailRest = document.createElement("span");
            d6_wkoutsDetailRest.setAttribute("style", "padding-left: 30px; padding-right: 15px");
            var d7_wkoutsDetailRestLabel = document.createElement("span");
            d7_wkoutsDetailRestLabel.innerText = "[휴식]";
            var d7_wkoutsDetailRestMin = document.createElement("input");
            d7_wkoutsDetailRestMin.setAttribute("type", "number");
            d7_wkoutsDetailRestMin.setAttribute("readonly", "true");
            d7_wkoutsDetailRestMin.setAttribute("class", "bottom-border");
            d7_wkoutsDetailRestMin.setAttribute("style", "width: 6%; text-align: center");
            d7_wkoutsDetailRestMin.setAttribute("value", method.restTime / 60);
            var d7_wkoutsDetailRestMinTail = document.createElement("span");
            d7_wkoutsDetailRestMinTail.innerText = "분";
            var d7_wkoutsDetailRestSec = document.createElement("input");
            d7_wkoutsDetailRestSec.setAttribute("type", "number");
            d7_wkoutsDetailRestSec.setAttribute("readonly", "true");
            d7_wkoutsDetailRestSec.setAttribute("class", "bottom-border");
            d7_wkoutsDetailRestSec.setAttribute("style", "width: 6%; text-align: center");
            d7_wkoutsDetailRestSec.setAttribute("value", method.restTime % 60);
            var d7_wkoutsDetailRestSecTail = document.createElement("span");
            d7_wkoutsDetailRestSecTail.innerText = "초";

            d6_wkoutsDetailRest.appendChild(d7_wkoutsDetailRestLabel);
            d6_wkoutsDetailRest.appendChild(d7_wkoutsDetailRestMin);
            d6_wkoutsDetailRest.appendChild(d7_wkoutsDetailRestMinTail);
            d6_wkoutsDetailRest.appendChild(d7_wkoutsDetailRestSec);
            d6_wkoutsDetailRest.appendChild(d7_wkoutsDetailRestSecTail);

            d5_wkoutsDetail.appendChild(d6_wkoutsDetailWei);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailWeiTail);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailSet);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailSetTail);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailRep);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailRepTail);
            d5_wkoutsDetail.appendChild(d6_wkoutsDetailRest);
            d4_wkoutsDetail.appendChild(d5_wkoutsDetail);  // 오늘의 운동 Content 완료

            d3_method.appendChild(d4_methodCombo);
            d3_method.appendChild(d4_wkoutsDetail)

            // 2차 MERGE
            d2_jnalComponent.appendChild(d3_method);
        })

        var d3_buttonGroup = document.createElement("div");
        d3_buttonGroup.setAttribute("class", "flex justify-end items-center p-1");
        d3_buttonGroup.setAttribute("style", "padding-top: 40px");
        var d4_btnShare = document.createElement("button");
        d4_btnShare.setAttribute("class", "extra-btn p-2 bg-gray-600 text-white rounded");
        d4_btnShare.setAttribute("style", "background-color: #1196c1; margin-right: 10px");
        // TODO onclick 이벤트
        d4_btnShare.innerText = "공유";
        var d4_btnModify = document.createElement("button");
        d4_btnModify.setAttribute("class", "extra-btn p-2 bg-gray-600 text-white rounded");
        d4_btnModify.setAttribute("style", "background-color: palegoldenrod; margin-right: 10px");
        d4_btnModify.setAttribute("onclick", "location.href='/jnal/modify?jnalId="+wkoutJnal.jnalId+"'");
        d4_btnModify.innerText = "편집";
        var d4_btnDelete = document.createElement("button");
        d4_btnDelete.setAttribute("class", "extra-btn p-2 bg-gray-600 text-white rounded");
        d4_btnDelete.setAttribute("style", "background-color: #b2b2b2; margin-right: 10px");
        d4_btnDelete.setAttribute("onclick", "removeJnal("+wkoutJnal.jnalId+")");
        d4_btnDelete.innerText = "삭제";

        // 3차 MERGE
        d3_buttonGroup.appendChild(d4_btnShare);
        d3_buttonGroup.appendChild(d4_btnModify);
        d3_buttonGroup.appendChild(d4_btnDelete);
        d2_jnalComponent.appendChild(d3_buttonGroup);

        d1_jnalsContent.appendChild(d2_jnalComponent);
    });

}

/**
 * JNALS EVENT3) YYYYMMDD +-1 일 이동처리
 *  - param = [현재 YYYYMMDD 으로부터 이동하려는 range]
 * */
function renderNewJnals(range) {
    console.log("renderNewJnals called..");

    // (1) 당일 날짜
    var curYyyyMmDd = document.getElementById("YYYYMMDD").getAttribute("yyyyMmDd");
    var curYyyy = parseInt(curYyyyMmDd.substring(0, 4));
    var curMm = parseInt(curYyyyMmDd.substring(4, 6));
    var curDd = parseInt(curYyyyMmDd.substring(6, 8));

    // (2) 당일 +-1 일 날짜
    var newDate = new Date(curYyyy, curMm-1, curDd);
    newDate.setDate(newDate.getDate() + range);

    var newYyyy = newDate.getFullYear();
    var newMm = (newDate.getMonth()+1).toString().length === 1
        ? "0"+(newDate.getMonth()+1).toString() : (newDate.getMonth()+1).toString();
    var newDd = newDate.getDate().toString().length === 1
        ? "0"+newDate.getDate().toString() : newDate.getDate().toString();

    var newYyyyMmDd = newYyyy + newMm + newDd;

    // (3) 이전 운동일지 화면에서 삭제
    var prevJnalsContent = document.getElementById("jnals-content");
    while (prevJnalsContent.hasChildNodes()) {
        prevJnalsContent.removeChild(prevJnalsContent.firstChild);
    }

    // (4) YYYYMMDD일 운동일지 렌더링 호출
    renderJnals(newYyyyMmDd);
}

/**
 * JNALS Event) 운동일지 삭제
 *  - param = [jnalId]
 * */
function removeJnal(jnalId) {

    if (!confirm("삭제 하시겠습니까?")) {
        return ;
    }

    var param = jnalId;

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/jnal/remove",
        //dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            jnalId : param
        },
        success: function (data) {
            alert("삭제 되었습니다.")
            location.href = "/jnal/read-one-day?yyyyMmDd="+document.getElementById("YYYYMMDD").getAttribute("yyyyMmDd");
        },
        error: function (request, error) {
            console.log("운동일지 삭제 중 오류가 발생하였습니다..\n");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    })

}
