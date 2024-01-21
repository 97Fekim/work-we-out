// ===================0. Configuration===================
Chart.defaults.font.family = "'Stylish', sans-serif";
Chart.defaults.font.weight = "bold";

// ===================1. 개인 운동 통계===================
const totalSetChart = $("#total-set-chart");
const createTotalSetChart = new Chart(totalSetChart, {
    type: "bar",
    data: {
    labels: ["등", "가슴", "어깨", "하체"],
    datasets: [
        {
        label: "세트수",
        data: [10, 8, 6, 5],
        backgroundColor: ["lightskyblue", "#c0ed70", "moccasin", "#c0ed70"], //배경색상
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

const workoutPartWeiIncChart = $("#workout-part-wei-inc-chart");
const createWorkoutPartWeiIncChart = new Chart(workoutPartWeiIncChart, {
    type: "bar",
    data: {
    labels: ["벤치프레스", "데드리프트", "바벨컬", "랫풀다운"],
    datasets: [
        {
        label: "kg",
        data: [30, -25, 10, -15],
        backgroundColor: ["red", "blue", "red", "blue"], //배경색상
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

const workoutPartWeiIncTrendChart = $("#workout-part-wei-inc-trend-chart");
const createWorkoutPartWeiIncTrendChart = new Chart(
    workoutPartWeiIncTrendChart,
    {
    type: "bar",
    data: {
        labels: ["11월 2주차", "11월 3주차", "11월 4주차", "12월 1주차"],
        datasets: [
        {
            label: "kg",
            data: [45, 50, 65, 60],
            backgroundColor: [
            "lightskyblue",
            "lightskyblue",
            "lightskyblue",
            "lightskyblue",
            ], //배경색상
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


// ===================2. 그룹 운동 통계===================
const avgWorkoutDaysChart = $("#avg-workout-days-chart");
const createAvgWorkoutDaysChart = new Chart(avgWorkoutDaysChart, {
  type: "bar",
  data: {
    labels: ["Fekim", "이너타이 중독자", "피티5만원", "Fekim1"],
    datasets: [
      {
        label: "일수",
        data: [1, 4, 2, 1],
        backgroundColor: ["lightskyblue", "#c0ed70", "moccasin", "lightskyblue"], //배경색상
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

const dstbWorkoutPart = $("#dstb-workout-part-chart");
const createDstbWorkoutPart = new Chart(dstbWorkoutPart, {
  type: "pie",
  data: {
    labels: ["등", "가슴", "어깨"],
    datasets: [
      {
        data: [1, 4, 2],
        backgroundColor: ["lightskyblue", "#c0ed70", "moccasin", "#c0ed70"], //배경색상
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

const dstbWorkoutPartByMemberChart = $("#dstb-workout-part-by-member-chart");
const createDstbWorkoutPartByMemberChart = new Chart(dstbWorkoutPartByMemberChart, {
  type: "pie",
  data: {
    labels: ["등", "가슴", "어깨"],
    datasets: [
      {
        data: [1, 4, 2],
        backgroundColor: ["lightskyblue", "#c0ed70", "moccasin", "#c0ed70"], //배경색상
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