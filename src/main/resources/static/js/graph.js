// ===================0. Configuration===================
Chart.defaults.font.family = "'Stylish', sans-serif";
Chart.defaults.font.weight = "bold";




// ===================3. 문자 발송 처리 현황===================
const sendMsgStsChart = $("#send-msg-sts-chart");
const createSendMsgStsChart = new Chart(sendMsgStsChart, {
  type: "bar",
  data: {
    labels: ["성공", "실패"],
    datasets: [
      {
        label: "처리건수",
        data: [19, 2],
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