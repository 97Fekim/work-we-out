document.write("<script src='/js/common.js'></script>");

var allMethods;

/**
 * # 초기화면로드
 * */
$(document).ready(function(){
    // datepicker 초기화
    $(function(){
        $("#date-picker-input").datepicker({
            dateFormat: 'yy년 mm월 dd일'
        });
    })

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

    // (1-2) Model로 넘어온 jnalId로 운동일지 렌더링
    var jnalId = $('input[name=jnalId]').val();
    renderJnal(jnalId);

});

/**
 * JNAL Event1) 운동일지 렌더링
 *  - param = [jnalId]
 * */
function renderJnal(jnalId) {

    var param = jnalId;

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/jnal/read-one",
        dataType:'json',
        xhrFields: {
            withCredentials: true
        },
        data: {
            jnalId : param
        },
        success: function (wkoutJnalDTO) {
            console.log("Jnal Load Success. data = " + wkoutJnalDTO);

            // (1) 헤더 렌더링
            var curYyyy = parseInt(wkoutJnalDTO.yyyy);
            var curMm = parseInt(wkoutJnalDTO.mm);
            var curDd = parseInt(wkoutJnalDTO.dd)
            var curDate = new Date(curYyyy, curMm-1, curDd);
            $("#date-picker-input").datepicker("setDate", curDate);

            // (2) 컨텐츠 렌더링
            renderJnalContent(wkoutJnalDTO);
        }
        ,
        error: function (request, error) {
            console.log("운동일지 로딩 중 오류가 발생하였습니다..\n");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });
}

/**
 * JNAL Event2) 운동일지 컨텐츠 렌더링
 *  - param = [WkoutJnalDTO]
 * */
function renderJnalContent(wkoutJnalDTO) {

    var d1_jnalsContent = document.getElementById("jnals-content");

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
    d4_commentContentText.setAttribute("id", "jnal-comments");
    d4_commentContentText.setAttribute("type", "text");
    d4_commentContentText.setAttribute("class", "bottom-border");
    d4_commentContentText.setAttribute("style", "width: 100%");
    d4_commentContentText.setAttribute("value", wkoutJnalDTO.comments === "" ? "" : wkoutJnalDTO.comments);
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

    var d3_methodsFrame = document.createElement("div");
    d3_methodsFrame.setAttribute("id", "methods-frame");
    d3_methodsFrame.setAttribute("class", "block justify-between items-center p-1");


    wkoutJnalDTO.wkoutJnalMethodDTOList.forEach(function(method, idx){
        var d4_method = document.createElement("div");
        d4_method.setAttribute("class", "wkout-method block justify-between items-center p-2");
        d4_method.setAttribute("seq", idx);

        var d5_methodCombo = document.createElement("div");
        d5_methodCombo.setAttribute("class", "d5_methodCombo select-box flex justify-between items-center p-1");
        d5_methodCombo.setAttribute("style", "padding-left: 25px");
        var d6_methodCombo = document.createElement("div");
        d6_methodCombo.setAttribute("class", "d6_methodCombo inline-flex relative");
        var d7_methodCombo = document.createElement("select");
        d7_methodCombo.setAttribute("class", "d7_methodCombo appearance-none bg-white border border-gray-300 rounded-md px-3 py-1 pr-6 focus:outline-none focus:border-blue-500");

        // SELECT OPTION 에 운동종목 로딩
        var d8_methodCombo = document.createElement("option");
        d8_methodCombo.innerText = method.methodNm;
        d8_methodCombo.setAttribute("methodId", method.methodId);
        d7_methodCombo.appendChild(d8_methodCombo);

        allMethods.wkoutMethodDTOList.forEach(function(m){
            var d8_methodCombo = document.createElement("option");
            d8_methodCombo.innerText = m.methodNm;
            d8_methodCombo.setAttribute("methodId", m.methodId);

            d7_methodCombo.appendChild(d8_methodCombo);
        });

        // SELECT OPTION 화살표 이미지
        var d7_methodComboArrow = document.createElement("div");
        d7_methodComboArrow.setAttribute("class", "absolute inset-y-0 right-0 flex items-center px-2 pointer-events-none");
        var d8_methodComboArrowSvg = document.createElement("span");
        d8_methodComboArrowSvg.setAttribute("class", "heroicons-solid--chevron-down");
        d7_methodComboArrow.appendChild(d8_methodComboArrowSvg);

        // 운동종목 제거 버튼
        var d6_removeMethodBtn = document.createElement("div");
        d6_removeMethodBtn.setAttribute("class", "p-1 cursor-pointer rounded");
        d6_removeMethodBtn.setAttribute("style", "padding-right: 130px; color:gray");
        d6_removeMethodBtn.setAttribute("onclick", "removeMethod("+idx+")");
        d6_removeMethodBtn.innerText = "x";

        // 무게 / 세트 / 랩스 / 휴식시간
        var d5_wkoutsDetail = document.createElement("div");
        d5_wkoutsDetail.setAttribute("class", "d5_wkoutsDetail flex justify-between p-1");
        d5_wkoutsDetail.setAttribute("style", "padding-left: 35px");
        var d6_wkoutsDetail = document.createElement("div");
        d6_wkoutsDetail.setAttribute("class", "d6_wkoutsDetail");
        var d7_wkoutsDetailWei = document.createElement("input");
        d7_wkoutsDetailWei.setAttribute("type", "number");
        d7_wkoutsDetailWei.setAttribute("class", "d7_wkoutsDetailWei bottom-border");
        d7_wkoutsDetailWei.setAttribute("style", "width: 8%; text-align: center");
        d7_wkoutsDetailWei.setAttribute("value", method.weight);
        var d7_wkoutsDetailWeiTail = document.createElement("span");
        d7_wkoutsDetailWeiTail.innerText = "kg";

        var d7_wkoutsDetailSet = document.createElement("input");
        d7_wkoutsDetailSet.setAttribute("type", "number");
        d7_wkoutsDetailSet.setAttribute("class", "d7_wkoutsDetailSet bottom-border");
        d7_wkoutsDetailSet.setAttribute("style", "width: 8%; text-align: center");
        d7_wkoutsDetailSet.setAttribute("value", method.sets);
        var d7_wkoutsDetailSetTail = document.createElement("span");
        d7_wkoutsDetailSetTail.innerText = "set";

        var d7_wkoutsDetailRep = document.createElement("input");
        d7_wkoutsDetailRep.setAttribute("type", "number");
        d7_wkoutsDetailRep.setAttribute("class", "d7_wkoutsDetailRep bottom-border");
        d7_wkoutsDetailRep.setAttribute("style", "width: 8%; text-align: center");
        d7_wkoutsDetailRep.setAttribute("value", method.reps);
        var d7_wkoutsDetailRepTail = document.createElement("span");
        d7_wkoutsDetailRepTail.innerText = "reps";

        var d7_wkoutsDetailRest = document.createElement("span");
        d7_wkoutsDetailRest.setAttribute("class", "d7_wkoutsDetailRest");
        d7_wkoutsDetailRest.setAttribute("style", "padding-left: 30px; padding-right: 15px");
        var d8_wkoutsDetailRestLabel = document.createElement("span");
        d8_wkoutsDetailRestLabel.innerText = "[휴식]";
        var d8_wkoutsDetailRestMin = document.createElement("input");
        d8_wkoutsDetailRestMin.setAttribute("type", "number");
        d8_wkoutsDetailRestMin.setAttribute("class", "d8_wkoutsDetailRestMin bottom-border");
        d8_wkoutsDetailRestMin.setAttribute("style", "width: 6%; text-align: center");
        d8_wkoutsDetailRestMin.setAttribute("value", parseInt(method.restTime / 60));
        var d8_wkoutsDetailRestMinTail = document.createElement("span");
        d8_wkoutsDetailRestMinTail.innerText = "분";
        var d8_wkoutsDetailRestSec = document.createElement("input");
        d8_wkoutsDetailRestSec.setAttribute("type", "number");
        d8_wkoutsDetailRestSec.setAttribute("class", "d8_wkoutsDetailRestSec bottom-border");
        d8_wkoutsDetailRestSec.setAttribute("style", "width: 6%; text-align: center");
        d8_wkoutsDetailRestSec.setAttribute("value", parseInt(method.restTime % 60));
        var d8_wkoutsDetailRestSecTail = document.createElement("span");
        d8_wkoutsDetailRestSecTail.innerText = "초";

        d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestLabel);
        d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestMin);
        d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestMinTail);
        d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestSec);
        d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestSecTail);

        d6_wkoutsDetail.appendChild(d7_wkoutsDetailWei);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailWeiTail);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailSet);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailSetTail);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailRep);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailRepTail);
        d6_wkoutsDetail.appendChild(d7_wkoutsDetailRest);
        d5_wkoutsDetail.appendChild(d6_wkoutsDetail);  // 오늘의 운동 Content 완료


        d6_methodCombo.appendChild(d7_methodCombo);
        d6_methodCombo.appendChild(d7_methodComboArrow);

        d5_methodCombo.appendChild(d6_methodCombo);
        d5_methodCombo.appendChild(d6_removeMethodBtn);

        d4_method.appendChild(d5_methodCombo);
        d4_method.appendChild(d5_wkoutsDetail);

        d3_methodsFrame.appendChild(d4_method);
    });

    d2_jnalComponent.appendChild(d3_methodsFrame);

    // 운동종목 추가 + 버튼
    var d3_addMethodBtnDiv = document.createElement("div");
    d3_addMethodBtnDiv.setAttribute("class", "flex justify-between items-center p-2");
    d3_addMethodBtnDiv.setAttribute("style", "padding-left: 35px");
    var d4_addMethodBtn = document.createElement("button");
    d4_addMethodBtn.setAttribute("class", "extra-btn p-1 bg-gray-600 text-white rounded");
    d4_addMethodBtn.setAttribute("style", "background-color: #1196c1; font-size: 12px");
    d4_addMethodBtn.setAttribute("onclick", "addMethod()");
    d4_addMethodBtn.innerText = "운동종목 추가 +";
    d3_addMethodBtnDiv.appendChild(d4_addMethodBtn);

    d2_jnalComponent.appendChild(d3_addMethodBtnDiv);

    // 저장 / 취소 버튼
    var d3_footerBtnGroup = document.createElement("div");
    d3_footerBtnGroup.setAttribute("class", "flex justify-end items-center p-2");
    var d4_saveBtn = document.createElement("button");
    d4_saveBtn.setAttribute("class", "extra-btn p-2 bg-gray-600 text-white rounded");
    d4_saveBtn.setAttribute("style", "background-color: #1196c1; margin-right: 10px");
    d4_saveBtn.setAttribute("onclick", "modifyJnal()");
    d4_saveBtn.innerText = "저장";
    var d4_cancleBtn = document.createElement("button");
    d4_cancleBtn.setAttribute("class", "extra-btn p-2 bg-gray-600 text-white rounded");
    d4_cancleBtn.setAttribute("style", "background-color: #b2b2b2; margin-right: 10px");
    d4_cancleBtn.setAttribute("onclick", "goBack()");
    d4_cancleBtn.innerText = "취소";

    d3_footerBtnGroup.appendChild(d4_saveBtn);
    d3_footerBtnGroup.appendChild(d4_cancleBtn);

    d2_jnalComponent.appendChild(d3_footerBtnGroup);

    d1_jnalsContent.appendChild(d2_jnalComponent);

}

/**
 * JNAL Event3) 운동종목 제거
 * */
function removeMethod(seq) {
    var methods = document.querySelectorAll(".wkout-method");
    methods.forEach(function(method){
        if (parseInt(method.getAttribute("seq")) === seq) {
            method.remove();
        }
    });
}

/**
 * JNAL Event4) 새 운동종목 추가
 * */
function addMethod() {
    var methods = document.querySelectorAll(".wkout-method");
    var max = -1;
    methods.forEach(function(method){
        if (parseInt(method.getAttribute("seq")) > max) {
            max = parseInt(method.getAttribute("seq"));
        }
    })

    var seq = max + 1;

    var d3_methodsFrame = document.getElementById("methods-frame");

    var d4_method = document.createElement("div");
    d4_method.setAttribute("class", "wkout-method block justify-between items-center p-2");
    d4_method.setAttribute("seq", seq);

    var d5_methodCombo = document.createElement("div");
    d5_methodCombo.setAttribute("class", "d5_methodCombo select-box flex justify-between items-center p-1");
    d5_methodCombo.setAttribute("style", "padding-left: 25px");
    var d6_methodCombo = document.createElement("div");
    d6_methodCombo.setAttribute("class", "d6_methodCombo inline-flex relative");
    var d7_methodCombo = document.createElement("select");
    d7_methodCombo.setAttribute("class", "d7_methodCombo appearance-none bg-white border border-gray-300 rounded-md px-3 py-1 pr-6 focus:outline-none focus:border-blue-500");

    // SELECT OPTION 에 운동종목 로딩
    var d8_methodCombo = document.createElement("option");
    d8_methodCombo.innerText = "운동종목 입력";
    d8_methodCombo.setAttribute("methodId", null);
    d7_methodCombo.appendChild(d8_methodCombo);

    allMethods.wkoutMethodDTOList.forEach(function(m){
        var d8_methodCombo = document.createElement("option");
        d8_methodCombo.innerText = m.methodNm;
        d8_methodCombo.setAttribute("methodId", m.methodId);

        d7_methodCombo.appendChild(d8_methodCombo);
    });

    // SELECT OPTION 화살표 이미지
    var d7_methodComboArrow = document.createElement("div");
    d7_methodComboArrow.setAttribute("class", "absolute inset-y-0 right-0 flex items-center px-2 pointer-events-none");
    var d8_methodComboArrowSvg = document.createElement("span");
    d8_methodComboArrowSvg.setAttribute("class", "heroicons-solid--chevron-down");
    d7_methodComboArrow.appendChild(d8_methodComboArrowSvg);

    // 운동종목 제거 버튼
    var d6_removeMethodBtn = document.createElement("div");
    d6_removeMethodBtn.setAttribute("class", "p-1 cursor-pointer rounded");
    d6_removeMethodBtn.setAttribute("style", "padding-right: 130px; color:gray");
    d6_removeMethodBtn.setAttribute("onclick", "removeMethod("+seq+")");
    d6_removeMethodBtn.innerText = "x";

    // 무게 / 세트 / 랩스 / 휴식시간
    var d5_wkoutsDetail = document.createElement("div");
    d5_wkoutsDetail.setAttribute("class", "d5_wkoutsDetail flex justify-between p-1");
    d5_wkoutsDetail.setAttribute("style", "padding-left: 35px");
    var d6_wkoutsDetail = document.createElement("div");
    d6_wkoutsDetail.setAttribute("class", "d6_wkoutsDetail");
    var d7_wkoutsDetailWei = document.createElement("input");
    d7_wkoutsDetailWei.setAttribute("type", "number");
    d7_wkoutsDetailWei.setAttribute("class", "d7_wkoutsDetailWei d7_wkoutsDetailWei bottom-border");
    d7_wkoutsDetailWei.setAttribute("style", "width: 8%; text-align: center");
    var d7_wkoutsDetailWeiTail = document.createElement("span");
    d7_wkoutsDetailWeiTail.innerText = "kg";

    var d7_wkoutsDetailSet = document.createElement("input");
    d7_wkoutsDetailSet.setAttribute("type", "number");
    d7_wkoutsDetailSet.setAttribute("class", "d7_wkoutsDetailSet bottom-border");
    d7_wkoutsDetailSet.setAttribute("style", "width: 8%; text-align: center");
    var d7_wkoutsDetailSetTail = document.createElement("span");
    d7_wkoutsDetailSetTail.innerText = "set";

    var d7_wkoutsDetailRep = document.createElement("input");
    d7_wkoutsDetailRep.setAttribute("type", "number");
    d7_wkoutsDetailRep.setAttribute("class", "d7_wkoutsDetailRep bottom-border");
    d7_wkoutsDetailRep.setAttribute("style", "width: 8%; text-align: center");
    var d7_wkoutsDetailRepTail = document.createElement("span");
    d7_wkoutsDetailRepTail.innerText = "reps";

    var d7_wkoutsDetailRest = document.createElement("span");
    d7_wkoutsDetailRest.setAttribute("class", "d7_wkoutsDetailRest");
    d7_wkoutsDetailRest.setAttribute("style", "padding-left: 30px; padding-right: 15px");
    var d8_wkoutsDetailRestLabel = document.createElement("span");
    d8_wkoutsDetailRestLabel.innerText = "[휴식]";
    var d8_wkoutsDetailRestMin = document.createElement("input");
    d8_wkoutsDetailRestMin.setAttribute("type", "number");
    d8_wkoutsDetailRestMin.setAttribute("class", "d8_wkoutsDetailRestMin bottom-border");
    d8_wkoutsDetailRestMin.setAttribute("style", "width: 6%; text-align: center");
    var d8_wkoutsDetailRestMinTail = document.createElement("span");
    d8_wkoutsDetailRestMinTail.innerText = "분";
    var d8_wkoutsDetailRestSec = document.createElement("input");
    d8_wkoutsDetailRestSec.setAttribute("type", "number");
    d8_wkoutsDetailRestSec.setAttribute("class", "d8_wkoutsDetailRestSec bottom-border");
    d8_wkoutsDetailRestSec.setAttribute("style", "width: 6%; text-align: center");
    var d8_wkoutsDetailRestSecTail = document.createElement("span");
    d8_wkoutsDetailRestSecTail.innerText = "초";

    d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestLabel);
    d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestMin);
    d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestMinTail);
    d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestSec);
    d7_wkoutsDetailRest.appendChild(d8_wkoutsDetailRestSecTail);

    d6_wkoutsDetail.appendChild(d7_wkoutsDetailWei);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailWeiTail);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailSet);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailSetTail);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailRep);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailRepTail);
    d6_wkoutsDetail.appendChild(d7_wkoutsDetailRest);
    d5_wkoutsDetail.appendChild(d6_wkoutsDetail);  // 오늘의 운동 Content 완료

    d6_methodCombo.appendChild(d7_methodCombo);
    d6_methodCombo.appendChild(d7_methodComboArrow);

    d5_methodCombo.appendChild(d6_methodCombo);
    d5_methodCombo.appendChild(d6_removeMethodBtn);

    d4_method.appendChild(d5_methodCombo);
    d4_method.appendChild(d5_wkoutsDetail);

    d3_methodsFrame.appendChild(d4_method);

}

/**
 * JNAL Event5) 운동일지 수정
 * */
function modifyJnal() {

    var date = $("#date-picker-input").datepicker("getDate");
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString().length === 1
        ? "0"+(date.getMonth()+1).toString() : (date.getMonth()+1).toString();
    var dd = date.getDate().toString().length === 1
        ? "0"+date.getDate().toString() : date.getDate().toString();

    var wkoutJnalDTO = new Object();
    wkoutJnalDTO.jnalId = parseInt($('input[name=jnalId]').val());
    wkoutJnalDTO.yyyy = yyyy;
    wkoutJnalDTO.mm = mm;
    wkoutJnalDTO.dd = dd;
    wkoutJnalDTO.comments = document.getElementById("jnal-comments").value;

    var wkoutJnalMethodDTOList = new Array();

    var methods = document.querySelectorAll(".wkout-method");

    methods.forEach(function(method){

        var wkoutJnalMethodDTO = new Object();

        // methodId
        var select = method.querySelector(".d5_methodCombo")
            .querySelector(".d6_methodCombo")
            .querySelector(".d7_methodCombo");
        var methodId = select.options[select.selectedIndex].getAttribute("methodId");
        if (methodId === "null") {
            alert("운동종목은 필수입니다.")
            return ;
        }

        // weight
        var weight = method.querySelector(".d5_wkoutsDetail")
            .querySelector(".d6_wkoutsDetail")
            .querySelector(".d7_wkoutsDetailWei").value;

        // sets
        var sets = method.querySelector(".d5_wkoutsDetail")
            .querySelector(".d6_wkoutsDetail")
            .querySelector(".d7_wkoutsDetailSet").value;

        // reps
        var reps = method.querySelector(".d5_wkoutsDetail")
            .querySelector(".d6_wkoutsDetail")
            .querySelector(".d7_wkoutsDetailRep").value;

        // restTime
        var restTime = parseInt( method.querySelector(".d5_wkoutsDetail")
                .querySelector(".d6_wkoutsDetail")
                .querySelector(".d7_wkoutsDetailRest")
                .querySelector(".d8_wkoutsDetailRestMin").value * 60 )
            +
            parseInt( method.querySelector(".d5_wkoutsDetail")
                .querySelector(".d6_wkoutsDetail")
                .querySelector(".d7_wkoutsDetailRest")
                .querySelector(".d8_wkoutsDetailRestSec").value );

        // Merge
        wkoutJnalMethodDTO.methodId = parseInt(methodId);
        wkoutJnalMethodDTO.weight = parseInt(weight);
        wkoutJnalMethodDTO.sets = parseInt(sets);
        wkoutJnalMethodDTO.reps = parseInt(reps);
        wkoutJnalMethodDTO.restTime = restTime;

        wkoutJnalMethodDTOList.push(wkoutJnalMethodDTO);
    })

    wkoutJnalDTO.wkoutJnalMethodDTOList = wkoutJnalMethodDTOList;

    console.log(wkoutJnalDTO);

    // ajax post
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/jnal/modify",
        contentType:'application/json',
        xhrFields: {
            withCredentials: true
        },
        data: JSON.stringify(wkoutJnalDTO)
        ,
        success: function (yyyyMmDd) {
            console.log("jnal modify success! new YyyyMmDd = " + yyyyMmDd);
            location.href = "http://localhost:8080/jnal/read-one-day?yyyyMmDd="+yyyyMmDd;
        }
        ,
        error: function (request, error) {
            console.log("운동일지 편집 중 오류가 발생하였습니다..\n");
            alert("code:"+request.status+"\n"
                +"message:"+request.responseText+"\n"
                +"error:"+error)
        }
    });
}