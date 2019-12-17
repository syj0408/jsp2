<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/decorator/decorator3.jsp --%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="en">
<title><decorator:title/></title>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif;}
.w3-sidebar {
  z-index: 3;
  width: 250px;
  top: 30px;
  bottom: 0;
  height: inherit;
}
</style>
   <decorator:head/>
   
<script type="text/javascript"
src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
   var randomColorFactor = function() {
      return Math.round(Math.random() * 255);
   }
   var randomColor = function(opacity) {
      return "rgba(" + randomColorFactor() + ","
            + randomColorFactor() + ","
            + randomColorFactor() + ","
            + (opacity || '.3') + ")";
   }
  $(function() {
      exchangeRate(); // 문서로드가 완료된 경우
     // setInerval(exchangeRate, 1000*60)
    })
    function exchangeRate() {
       $.ajax("${path}/model2/ajax/exchange2.do",   {
           success : function(data) {  // 콜백 함수
              console.log(data)
               $("#exchange").html(data);
            },
            error : function(e) {
               alert("서버 오류 :" + e.status);
            }
         })
   }; 

   $(document).ready(function() { //문서 로드가 완료된 경우
      exchangeRate();  //환율정보 크롤링으로 로드
//      setInterval(exchangeRate, 1000*60)
      graphs();  //그래프를 위한 데이터를 ajax으로 가져오기
   })
   function graphs() {
      $.ajax("${path}/model2/ajax/graph.do",{
         success: function(data) {
            console.log(data);
            pieGraphprint(data);
            barGraphprint(data);
         },
         error: function(e) {
            alert("서버 오류:" + e.status);
         }
      })
   }
   function pieGraphprint(data) {
      //data : 서버에서 전달한 JSON 형태 데이터
      //[{"name":"홍길동","cnt":10},{"name":"김삿갓","cnt":9}]
      var rows = JSON.parse(data);
         var names = [];  //라벨 데이터
         var datas = [];  //건수 데이터
         var colors = []; //색상
         //json 파일을 분석하여 필요한 데이터로 저장
         $.each(rows, function(index,item) {
            names[index] = item.name;
            datas[index] = item.cnt;
            colors[index] = randomColor(1);
         })
         var config = {
            type: 'pie',
            data: {
               datasets: [{
                  data: datas,
                  backgroundColor: colors,
               }],
               labels: names
            },
            options: {
               responsive: true,
               legend: {
                  position: 'top'
               },
               title: {
                  display: true,
                  text: '글쓴이별 게시판 등록 건수'
               }
            }
         };
         var ctx = document.getElementById("canvas2").getContext("2d");
      new Chart(ctx, config);
   }
   function barGraphprint(data) {
      var rows = JSON.parse(data);
         var names = [];
         var datas = [];
         var colors = [];
         $.each(rows, function(index,item) {
            names[index] = item.name;
            datas[index] = item.cnt;
            colors[index] = randomColor(1);
         })
         var chartData = {
            labels: names,
            datasets: [
               {
                  type: 'line',
                  borderWidth: 2,
                  borderColor: colors,
                  label: '건수',
                  fill: false,
                  data: datas,
               }, {
                  type: 'bar',
                  label: '건수',
                  backgroundColor: colors,
                  data: datas,
                  borderWidth: 2
               }
            ]
         }
         var ctx = document.getElementById('canvas1').getContext('2d');
         new Chart(ctx, {
            type: 'bar',
            data: chartData,
            options: {
               responsive: true,
               title: {
                  display: true,
                  text: '글쓴이별 게시판 등록 건수'
               },
               legend: {
                  display: false
               },
               scales: {
                  xAxes: [{
                     display: true,
                     scaleLabel: {
                        display: true,
                        labelString: "게시물 작성자"
                     },
                     stacked: true
                  }],
                  yAxes: [{
                     display: true,
                     scaleLabel: {
                        display: true,
                        labelString: "게시물 작성 건수"
                     },
                     stacked: true
                  }]
               }
            }
         });
   }
</script>
<script type="text/javascript" src="https://www.chartjs.org/dist/2.9.3/Chart.min.js"> </script>
<!-- <script src="https://www.chartjs.org/samples/latest/utils.js"></script> -->
<body>
<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-theme w3-top w3-right-align w3-large">
   <c:choose>
      <c:when test="${!empty sessionScope.login}">
         ${sessionScope.login}님이 로그인하셨습니다 &nbsp;&nbsp;
         <a href="logout.me"> 로그아웃</a>
      </c:when>
      <c:otherwise>
         <a href="loginForm.me"> 로그인</a>
         <a href="joinForm.me"> 회원가입</a>
      </c:otherwise>
   </c:choose>
  </div>
</div>

<!-- Sidebar -->
<nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
    <i class="fa fa-remove"></i>
  </a>
  <h4 class="w3-bar-item"><b>Menu</b></h4>
  <a class="w3-bar-item w3-button w3-hover-black" href="${path}/model2/member/main.me">회원관리</a>
  <a class="w3-bar-item w3-button w3-hover-black" href="${path}/model2/board/list.do">게시판</a>
  <div class="w3-container">
       <div class="w3-clear"></div>
       <%-- ajax를 통해 얻은 환율 정보 내용 출력 --%>
       <div id="exchange"></div>
  </div>   
</nav>
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>
<div class="w3-main" style="margin-left:250px">


  <div class="w3-row w3-padding-64">
    <div class="w3-twothird w3-container">
    <div class="w3-half">
          <div class="w3-container w3-padding-16">
             <div class="w3-left"><i class="fa fa-comment w3-xxxLarge"></i>
                <div id="container" style="width: 75%;">
                <canvas id="canvas1"></canvas>
                </div>
             </div>
          </div>
       </div>
    <div class="w3-half">
          <div class="w3-container w3-padding-16">
             <div class="w3-left"><i class="fa fa-eye w3-xxxLarge"></i>
                <div id="barcontainer" style="width: 75%;">
                <canvas id="canvas2"></canvas>
                </div>
             </div>
          </div>
       </div>
     <decorator:body/>
    </div>
  </div>

  <footer id="myFooter">
    <div class="w3-container w3-theme-l2 w3-padding-32">
      <h4>구디 아카데미</h4>
    </div>

    <div class="w3-container w3-theme-l1">
      <p>Powered by <a href="https://www.goodee.co.kr" target="_blank">심윤정</a></p>
    </div>
    
  </footer>
<!-- END MAIN -->
</div>
<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>
<script>
/*
var MONTHS = ['1분기', '2분기', '3분기', '4분기'];
var ChartData = {
   labels: MONTHS,
   datasets: [{ //데이터 부분
         label: '남',
         fill : false,
         backgroundColor:"#D499B9",
         borderColor: "#9055A2",  
         borderWidth: 1,
         data: [10,95,70,25]
      }, {
         label: '여',
         fill : false,
         backgroundColor:"#0DDDF3",
         borderColor: "#A0FD4F",
         borderWidth: 1,
         data: [40,80,24,25]
      }]//dataset 설정 끝
   };
window.onload = function() {
   var ctx1 = document.getElementById('linecanvas').getContext('2d');
   var ctx2 = document.getElementById('barcanvas').getContext('2d');
   window.myLine = new Chart(ctx1, {
      type:'line',//선그래프
      data: ChartData,//데이터
      options: {
         responsive: true //반응형웹
      }
   });
   window.myBar = new Chart(ctx2, {
      type: 'bar', //막대그래프
      data: ChartData, //데이터
      options: {
         responsive: true, //반응형 웹
      }
   });
}
*/
</script>
</body>
</html>